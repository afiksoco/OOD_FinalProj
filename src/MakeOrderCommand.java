import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MakeOrderCommand implements Command {
    private Product product;
    private int previousAmount;
    private int previousProfit;

    private List<Observer> observers = new ArrayList<>();

    public static Scanner scanner = new Scanner(System.in);

    public MakeOrderCommand(Product product) {
        this.product = product;
    }


    public boolean checkIfOrderExists(String serial) { // despite the SET class checks for duplicates, // we want to stop the order info at the begginin
        Product p = product;
        for (Order o : product.getAllOrders()) {
            if (o.getSerial().equals(serial))
                return true;
        }
        return false;
    }

    @Override
    public void execute() {
        System.out.println("Enter serial ID for the order : ");
        String serial = scanner.next();

        if (checkIfOrderExists(serial)){
                System.out.println("\u001B[31mFailed to take order! serial ID already exists.\u001B[0m");
            return;
        }

        System.out.println("Enter amount : ( current available amount : " + product.getStock() + " )");
        int amount;
        do {
            amount = scanner.nextInt();
            if (amount <= 0) {
                System.out.println("Amount must be greater than 0. Please try again.");
            }
        } while (amount <= 0);

        if (amount > product.getStock()) {
            System.out.println("Not enough in storage... Exiting...");
            return;
        }

        Order newOrder;


        if (product instanceof SoldThroughWebsite) {

            ShippingMethod chosenShipmentMethod = null;
            ShippingCompany shippingCompany = null;
            chosenShipmentMethod = getShippingMethodFromUser(product);
            newOrder = new WebsiteOrder(product, new Customer("aaa", "0526410559"), amount, serial,
                    chosenShipmentMethod);
            shippingCompany = notifyObservers((WebsiteOrder) newOrder);
            ((WebsiteOrder) newOrder).setShippingCompany(shippingCompany);

        } else
            newOrder = new Order(product, new Customer("aaa", "0526410559"), amount, serial);

        previousProfit = product.getProfit();
        if (product.getAllOrders().add(newOrder)) {
            product.setProfit(Calculator.calcProductTotalProfit(product, amount));
            previousAmount = product.getStock();
            product.setStock(product.getStock() - amount);
            System.out.println("Order received!");
            product.printTableFormat(product);
            System.out.println(newOrder);
        }
    }

    @Override
    public void undo() {
        product.setStock(previousAmount);
        product.setProfit(previousProfit);
        Order lastOrder = null;
        for (Order order : product.getAllOrders()) {
            lastOrder = order;
        }
        System.out.println("Order #" + lastOrder.getSerial() + " cancelled successfully!");

        product.getAllOrders().remove(lastOrder);
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    private ShippingCompany notifyObservers(WebsiteOrder order) {
        System.out.println("Notifying shipping companies... ");
        System.out.println("Checking for best price... ");
        double minPrice = Integer.MAX_VALUE; // Initialize to the lowest possible value
        Observer bestCompany = null;

        for (Observer observer : observers) { //not entering the for!
            double price = observer.shippingPrice(order);
            if (price < minPrice) {
                minPrice = price;
                bestCompany = observer;
            }
        }
        order.getShippingMethod().setShippingFees(minPrice);
        return (ShippingCompany) bestCompany;
    }

    private ShippingMethod getShippingMethodFromUser(Product product) {

        SoldThroughWebsite p = (SoldThroughWebsite) product;

        if (p.getExpressShipping() && !p.getStandardShipping())
            return ShippingMethodFactory.createShippingMethod(ShippingMethodName.EXPRESS, p.getProfit());
        else if (!p.getExpressShipping() && p.getStandardShipping())
            return ShippingMethodFactory.createShippingMethod(ShippingMethodName.STANDARD, p.getProfit());

        System.out.println("Choose shipping method:");
        int choice;
        do {
            System.out.println("1. Express Shipping.\n2. Standard Shipping.");
            choice = scanner.nextInt();
        } while (choice != 1 && choice != 2);

        if (choice == 1)
            return ShippingMethodFactory.createShippingMethod(ShippingMethodName.EXPRESS, p.getProfit());
        else
            return ShippingMethodFactory.createShippingMethod(ShippingMethodName.STANDARD, p.getProfit());
    }

}

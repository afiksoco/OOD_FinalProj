import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MakeOrderCommand implements Command {
	private Product product;
	private int previousAmount;
	private int previousProfit;

	private List<OrderObserver> observers = new ArrayList<>();

	public static Scanner scanner = new Scanner(System.in);

	public MakeOrderCommand(Product product) {
		this.product = product;
	}

	@Override
	public void execute() {
		System.out.println("Enter serial ID for the order : ");
		String serial = scanner.next();
		System.out.println("Enter amount : (current available amount : " + product.getStock() + " )");
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
		ShippingMethod chosenShipmentMethod = null;
		ShippingCompany shippingCompany=null;

		if (product instanceof SoldThroughWebsite) {
			chosenShipmentMethod = getShippingMethodFromUser(product);
//            SoldThroughWebsite soldThroughWebsiteProduct = (SoldThroughWebsite) product;
//            System.out.println("Choose shipping method from available methods: ");
//
//            if (soldThroughWebsiteProduct.getExpressShipping()) {
//                System.out.println("1. Express Shipping");
//            }
//            if (soldThroughWebsiteProduct.getStandardShipping()) {
//                System.out.println("2. Standard Shipping");
//            }
			newOrder = new WebsiteOrder(product, new Customer("aaa", "0526410559"), amount, serial,
					chosenShipmentMethod);
			shippingCompany = notifyObservers(newOrder);
			
			System.out.println("\nThe order will shipped by " + shippingCompany + ".\nType of shipping: "
					+ chosenShipmentMethod.getClass().getName()+".\nShipping fees: ");

		} else
			newOrder = new Order(product, new Customer("aaa", "0526410559"), amount, serial);

		previousProfit = product.getProfit();
		if (product.getAllOrders().add(newOrder)) {
			product.setProfit(Calculator.calcProductTotalProfit(product, amount));

			previousAmount = product.getStock();
			product.setStock(product.getStock() - amount);
			System.out.println("Order received!");
			product.printTableFormat();
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

	public void addObserver(OrderObserver observer) {
		observers.add(observer);
	}

	public void removeObserver(OrderObserver observer) {
		observers.remove(observer);
	}

	private ShippingCompany notifyObservers(Order order) {
		System.out.println("Notifying shipping companies... ");
		System.out.println("Checking for best price... ");
		int maxPrice = Integer.MIN_VALUE; // Initialize to the lowest possible value
		ShippingCompany bestCompany = null;

		for (OrderObserver observer : observers) { //not entering the for!
			int price = observer.shippingPrice(order);
			if (price > maxPrice) {
				maxPrice = price;
				bestCompany = observer.getCompany();
			}
		}
		return bestCompany;
	}

	private ShippingMethod getShippingMethodFromUser(Product product) {
		
		SoldThroughWebsite p  = (SoldThroughWebsite) product;

		if (p.getExpressShipping() && !p.getStandardShipping()) 
			return ShippingMethodFactory.createShippingMethod(ShippingMethodName.EXPRESS,p.getProfit() ); // Only express shipping is available
		else if (!p.getExpressShipping() && p.getStandardShipping())
			return ShippingMethodFactory.createShippingMethod(ShippingMethodName.STANDARD,p.getProfit() ); // Only standard shipping is available

		System.out.println("Choose shipping method:");
		int choice;
		do {
			System.out.println("1. Express Shipping.\n2. Standard Shipping");
			choice = scanner.nextInt();
		} while (choice != 1 && choice != 2);

		if(choice == 1)
			return ShippingMethodFactory.createShippingMethod(ShippingMethodName.EXPRESS,p.getProfit() );  
		else
			return ShippingMethodFactory.createShippingMethod(ShippingMethodName.STANDARD,p.getProfit() ); // Only standard shipping is available
	}

}

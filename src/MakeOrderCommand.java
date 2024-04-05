import java.util.Scanner;

public class MakeOrderCommand implements Command {
    private Product product;
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

        Order newOrder = new Order(product, new Customer("aaa", "0526410559"), amount, serial);
        if (product.getAllOrders().add(newOrder)) {
            product.setStock(product.getStock() - amount);
            System.out.println("Order received! order details :");
            System.out.println(newOrder);
        }

    }

    @Override
    public void undo() {

    }
}

import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;


public class Store {
    public static Scanner scanner = new Scanner(System.in);
    private static Store instance;
    private Set<Product> allProducts = new TreeSet<>(); //sort by String
    private Stack<Command> stack = new Stack<>();
    private FedEx fedEx;
    private DHL DHL;

    public Store() {
        this.fedEx = new FedEx("Afik", "0526410559");
        this.DHL = new DHL("soco", " 0546468585");
    }

    public static Store getInstance() { //singleton
        if (instance == null) instance = new Store();
        return instance;
    }

    public Stack<Command> getStack() {
        return stack;
    }

    public DHL getDHL() {
        return DHL;
    }

    public FedEx getFedEx() {
        return fedEx;
    }

    public void removeProductBySerial() {
        showAllProducts(Product.class);
        System.out.println("\nEnter serial ID of the product you want to remove");
        Product p = infoForProduct();
        if (p != null) {
            if (allProducts.remove(p)) {
                System.out.println("\nProduct #" + p.getSerial() + " '" + p.getProduct_name() + "'" + " deleted successfully!");
            } else {
                System.out.println("\nFailed to delete product #" + p.getSerial() + " '" + p.getProduct_name() + "'.");
            }
        }
    }

    public <T extends Product> boolean checkIfListHasType(Class<T> productType) {
        for (Product product : allProducts) {
            if (productType.isInstance(product)) {
                return true;
            }
        }
        return false;
    }


    public void createNewOrder() {
        System.out.println("\nChoose type on product:\n1- Sold through website.\n2- Sold in store.\n3- Sold to wholesalers.");
        int choice;
        while (true) {
            choice = scanner.nextInt();

            if (choice >= 1 && choice <= 3) {
                break;
            } else {
                System.out.println("Invalid choice. Please enter a number between 1 and 3.");
            }
        }

        switch (choice) {
            case 1:
                if (checkIfListHasType(SoldThroughWebsite.class))
                    showAllProducts(SoldThroughWebsite.class);
                else {
                    System.out.println("There isn't product of this type! Exiting...");
                    return;
                }
                break;
            case 2:
                if (checkIfListHasType(SoldInStore.class))
                    showAllProducts(SoldInStore.class);
                else {
                    System.out.println("There isn't product of this type! Exiting...");
                    return;
                }
                break;
            case 3:
                if (checkIfListHasType(SoldToWholesalers.class))
                    showAllProducts(SoldToWholesalers.class);
                else {
                    System.out.println("There isn't product of this type! Exiting...");
                    return;
                }
                break;
            default:
                break;
        }

        System.out.println("\nEnter serial ID of the product you want to order!");
        Product p = infoForProduct();
        if (p != null) {
            MakeOrderCommand command = new MakeOrderCommand(p);
            command.addObserver(DHL);
            command.addObserver(fedEx);
            command.execute();
            stack.add(command);
            command.printCmdRes();
        }

    }

    public Product getProductBySerialID(String serialID) {
        for (Product temp : allProducts) {
            if (serialID.equals(temp.getSerial()))
                return temp;
        }
        return null;
    }

    public Set<Product> getAllProducts() {
        return allProducts;
    }


    public void addProductToStore() {
        if (allProducts.add(ProductFactory.createProduct()))
            System.out.println("Product added successfully");
    }

    public void showSystem() {
        showAllProducts(Product.class);
        int profit = Calculator.calcTotalProfitInILS(allProducts);
        System.out.println("\nTotal profit from all orders: " + profit + " ILS");

    }

    public <T extends Product> void showAllProducts(Class<T> productType) {
        printTableFormat();
        for (Product product : allProducts) {
            if (productType.isInstance(product)) {
                System.out.println(product);
            }
        }
    }

    public void printTableFormat() {
        System.out.printf("\n%-20s %-20s %-15s %-15s %-15s %-10s %-15s %-20s %-20s\n",
                "Product type",
                "Product name", "Serial", "Cost price", "Selling price", "Stock",
                "Destination", "Standard shipment", "Express shipment");
    }


    public Product infoForProduct() {
        String serialID;
        serialID = scanner.next();
        Product p = getProductBySerialID(serialID);
        if (p != null)
            return p;
        else {
            System.out.println("Product with serial ID '" + serialID + "' not found.");
        }
        return null;
    }

    public void updateProductStock() {
        showAllProducts(Product.class);
        System.out.println("\nEnter serial ID of the product you want to update its stock");
        Product p = infoForProduct();
        if (p != null) {
            int newAmount;
            System.out.println("\nCurrect amount " + p.getStock());
            System.out.println("Enter new amount ");
            newAmount = scanner.nextInt();
            if (newAmount < 0)
                System.out.println("Invalid amount! Exiting...");
            else {
                p.setStock(newAmount);
                System.out.println("\nProduct's stock updated to " + newAmount + "!");
            }
        }
    }

    public void undoOrder() {
        if (!stack.isEmpty()) {
            Command cmd = stack.pop();
            cmd.undo();

        } else {
            System.out.println("No previous orders.");

        }
    }

    public void showDetailedProductInfo() {
        if (allProducts.isEmpty()) {
            System.out.println("Add some products first! Exiting...");
            return;
        }
        showAllProducts(Product.class);

        System.out.println("\nEnter serial to see detailed info of a product.");
        Product p = infoForProduct();
        if (p != null) {
            printTableFormat();
            p.showDetailedInfo();
        }
    }

    public void showAllOrdersForProduct() {
        if (allProducts.isEmpty()) {
            System.out.println("Add some products first! Exiting...");
            return;
        }
        showAllProducts(Product.class);
        System.out.println("\nEnter serial to see all the orders for specific product.");
        Product p = infoForProduct();
        if (p != null) {
            if (p.getAllOrders().isEmpty()) {
                System.out.println("No current orders for this product!");
                return;
            }
            p.showAllOrders();
            System.out.println("\nStore total profit for this product is: " + p.getProfit() + " " + p.getCurrency());
        }
    }

    public Memento createMemento() {
        for (Product product : allProducts)
            product.createMemento();
        System.out.println("\nData state stored successfuly!");
        return new Memento(allProducts, stack);
    }

    public void setMemento(Memento m) {
        if (m == null) {
            System.out.println("\nNo previous states.");
            return;
        }
        allProducts = new TreeSet<>(m.getAllProducts());
        for (Product product : allProducts) {
            product.setMemento(product.getMemento());
        }
        stack = new Stack<>();
        this.stack.addAll(m.getStack());
        System.out.println("\nData from last save restored successfully!");
    }

    public static class Memento {
        private Set<Product> allProducts = new TreeSet<>();
        private Stack<Command> stack = new Stack<>();

        public Memento(Set<Product> allProducts, Stack<Command> stack) {
            this.allProducts = new TreeSet<>(allProducts);
            this.stack = new Stack<>();
        }

        public Set<Product> getAllProducts() {
            return allProducts;
        }

        public Stack<Command> getStack() {
            return stack;
        }
    }



}

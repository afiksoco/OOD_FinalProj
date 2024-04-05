import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;


public class Store {
    public static Scanner scanner = new Scanner(System.in);
    private Set<Product> allProducts = new TreeSet<>(); //sort by String
    private static Store instance;
    private static Stack<Command> stack = new Stack<>();


    public static Store getInstance() { //singleton
        if (instance == null) instance = new Store();
        return instance;
    }


    public void removeProductBySerial() {
        System.out.println("Enter serial ID of the product you want to remove!");

        Product p = infoForProduct();
        if (p != null) {
            if (allProducts.remove(p)) {
                System.out.println("Product #" + p.getSerial() + " '" + p.getProduct_name() + "'" + " deleted successfully");
            } else {
                System.out.println("Failed to delete product #" + p.getSerial() + " '" + p.getProduct_name() + "'.");
            }
        }
    }


    public void createNewOrder() {
        System.out.println("Choose type on product.\n1- Sold through website.\n2- Sold in store.\n3- Sold to wholesalers.");
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
                showAllProducts(SoldThroughWebsite.class);
                break;
            case 2:
                showAllProducts(SoldInStore.class);
                break;
            case 3:
                showAllProducts(SoldToWholesalers.class);
                break;
            default:
                break;
        }

        System.out.println("Enter serial ID of the product you want to order!");
        Product p = infoForProduct();
        if (p != null) {
            MakeOrderCommand command = new MakeOrderCommand(p);
            command.execute();
            stack.add(command);
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

    public void setAllProducts(Set<Product> allProducts) {
        this.allProducts = allProducts;
    }

    public void addProductToStore() {
        if (allProducts.add(ProductFactory.createProduct()))
            System.out.println("Product added succ");
    }

    /* public void showAllProducts() {// productTYPE: 20 , pName : 20, serial 15, cost 9, sell 9
         System.out.printf("%-20s %-20s %-15s %-15s %-15s %-10s %-15s %-20s %-20s\n",
                 "Product type",
                 "Product name", "Serial", "Cost price", "Selling price", "Stock",
                 "Destination", "Standard shipment", "Express shipment");
         for (Product product : allProducts) {
             System.out.println(product);
         }
     }
 */
    public <T extends Product> void showAllProducts(T productType) {

        System.out.printf("%-20s %-20s %-15s %-15s %-15s %-10s %-15s %-20s %-20s\n",
                "Product type",
                "Product name", "Serial", "Cost price", "Selling price", "Stock",
                "Destination", "Standard shipment", "Express shipment");
        for (Product product : allProducts) {

            if (product.getClass().getSimpleName().equals(productType.getClass().getSimpleName())) {
                System.out.println(product);
            }
        }
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
        System.out.println("Enter serial ID of the product you want to update its stock!");
        Product p = infoForProduct();
        if (p != null) {
            int newAmount;
            System.out.println("Currect amount : " + p.getStock());
            System.out.println("Enter new amount : ");
            newAmount = scanner.nextInt();
            if (newAmount == 0) {
                allProducts.remove(p);
                System.out.println("Product removed from store!");
            } else if (newAmount < 0) {
                System.out.println("Invalid amount! Exiting...");

            } else {
                p.setStock(newAmount);
                System.out.println("Product's stock updated to " + newAmount + "!");
            }

        }
    }
}

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;


public class Store {
    public static Scanner scanner = new Scanner(System.in);
    private Set<Product> allProducts = new TreeSet<>(); //sort by String
    private static Store instance;


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

    public void showAllProducts() {// productTYPE: 20 , pName : 20, serial 15, cost 9, sell 9
        System.out.printf("%-20s %-20s %-15s %-15s %-15s %-10s %-15s %-20s %-20s\n",
                "Product type",
                "Product name", "Serial", "Cost price", "Selling price", "Stock",
                "Destination", "Standard shipment", "Express shipment");
        for (Product product : allProducts) {
            System.out.println(product);
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

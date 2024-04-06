import java.util.Scanner;

public class Program {
    public static Store store = Store.getInstance(); // singleton
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        String choice;

        System.out.println("Welcome to the Store!");

        do {
            System.out.println("Menu:");
            System.out.println("1. Load Hardcoded Data");
            System.out.println("2. Add Product");
            System.out.println("3. Delete Product");
            System.out.println("4. Update Product Stock");
            System.out.println("5. Make a Product Order");
            System.out.println("6. Undo Last Order");
            System.out.println("7. Show Product Details by Serial");
            System.out.println("8. Show All Available Products");
            System.out.println("9. Show All Product Orders");
            System.out.println("10. Save System");
            System.out.println("11. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.next();
            switch (choice) {
                case "1":
                    hardcoded();
                    break;
                case "2":
                    store.addProductToStore();
                    break;
                case "3":
                    store.removeProductBySerial();
                    // Implement delete product functionality
                    break;
                case "4":
                    store.updateProductStock();
                    // Implement update product stock functionality
                    break;
                case "5":
                    store.createNewOrder();
                    // Implement make product order functionality
                    break;
                case "6":
                    store.undoOrder();
                    // Implement undo last order functionality
                    break;
                case "7":
                    store.showDetailedProductInfo();
                    // Implement show product details by serial functionality
                    break;
                case "8":
                    // 'Implement show all available products functionality
                    store.showSystem();
                    break;
                case "9":
                    // Implement show all product orders functionality
                	store.showAllOrdersForProduct();
                    break;
                case "10":
                    // Implement save system functionality
                    break;
                case "e":
                case "E":
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (!choice.equals("e") && !choice.equals("E"));
        scanner.close();
    }

    private static void hardcoded() {

        // TODO Auto-generated method stub
        store.getAllProducts().add(new SoldInStore("Spoon", "popo", 300, 333 , 12));
        store.getAllProducts().add(new SoldInStore("Watch", "zzz", 4, 8 , 268));
        store.getAllProducts().add(new SoldToWholesalers("PC", "abc", 88, 99 , 34));
        store.getAllProducts().add(new SoldToWholesalers("Keyboard", "bzc", 88, 99 , 94));
        store.getAllProducts().add(new SoldThroughWebsite("Fork", "koko", 88, 100 , 32, "Israel", true, true  ));
        store.getAllProducts().add(new SoldThroughWebsite("Tami", "arba", 200, 250 , 77, "Israel", true, false  ));

    }
}
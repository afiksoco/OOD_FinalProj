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
            System.out.println("10. Save current state");
            System.out.println("11. Load state from last save");

            System.out.println("e/E - Exit");
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
                   store.saveToMemento();

                    // Implement save system functionality
                    break;
                case "11":
                    store.restoreFromMemento(store.getMemento());
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
        Customer costumer = new Customer("Afik","0251654164");
        DHL DHL = store.getDHL();
        FedEx fedEx = store.getFedEx();



        Product p1 = new SoldInStore("Vacuum cleaner", "popo", 300, 333 ,8, 12);
        Product p2 = new SoldInStore("TV", "sf3r1", 600, 1500 ,30, 50);
        Product p3 = new SoldInStore("Smart watch", "zzz", 4, 8 , 9,268);
        Product p4 = new SoldToWholesalers("Iphone18SuperPro", "abc", 88, 99 ,4, 34);
        Product p5 = new SoldToWholesalers("PC", "adfaaf2", 12, 99 ,4, 34);
        Product p6 = new SoldToWholesalers("Keyboard", "bzc", 88, 99 ,2, 94);
        Product p7 = new SoldThroughWebsite("Mouse (for PC)", "mouseforpc", 42, 100 ,2, 100, "Israel", true, true  );
        Product p8 = new SoldThroughWebsite("Mouse (animal)", "mouseforcat", 15, 16 ,8, 500, "Israel", false, false  );
        Product p9 = new SoldThroughWebsite("Tami4", "tamiarba", 200, 250 ,600, 77, "Israel", true, false  );


        store.getAllProducts().add(p1);

        MakeOrderCommand cmd1 = new MakeOrderCommand(p1, costumer,2, " asdd2");
        MakeOrderCommand cmd2 = new MakeOrderCommand(p1, costumer , 2, "asdd2");
        MakeOrderCommand cmd3 = new MakeOrderCommand(p1, costumer , 1, "wsdd2");
        cmd1.execute();
        cmd2.execute();
        cmd3.execute();

        store.getStack().add(cmd1);
        store.getStack().add(cmd2);
        store.getStack().add(cmd3);

        store.getAllProducts().add(p2);

        MakeOrderCommand cmd4 = new MakeOrderCommand(p2, costumer, 6, "asd2");
        MakeOrderCommand cmd5 = new MakeOrderCommand(p2, costumer, 1, "wqwed2");
        MakeOrderCommand cmd6 = new MakeOrderCommand(p2, costumer, 3, "wswq1qqq2");
        cmd4.execute();
        cmd5.execute();
        cmd6.execute();

        store.getStack().add(cmd4);
        store.getStack().add(cmd5);
        store.getStack().add(cmd6);

        store.getAllProducts().add(p3);

        MakeOrderCommand cmd7 = new MakeOrderCommand(p3, costumer, 1, "asdddd2");
        MakeOrderCommand cmd8 = new MakeOrderCommand(p3, costumer, 6, "wed2");
        MakeOrderCommand cmd9 = new MakeOrderCommand(p3, costumer, 4, "ss32");
        cmd7.execute();
        cmd8.execute();
        cmd9.execute();

        store.getStack().add(cmd7);
        store.getStack().add(cmd8);
        store.getStack().add(cmd9);


        store.getAllProducts().add(p4);

        MakeOrderCommand cmd10 = new MakeOrderCommand(p4, costumer, 1, "asdvbv");
        MakeOrderCommand cmd11 = new MakeOrderCommand(p4, costumer, 2, "azxcx");
        MakeOrderCommand cmd12 = new MakeOrderCommand(p4, costumer, 4, "snns32");
        cmd10.execute();
        cmd11.execute();
        cmd12.execute();

        store.getStack().add(cmd10);
        store.getStack().add(cmd11);
        store.getStack().add(cmd12);


        store.getAllProducts().add(p5);

        MakeOrderCommand cmd13 = new MakeOrderCommand(p5, costumer, 1, "asddh2");
        MakeOrderCommand cmd14 = new MakeOrderCommand(p5, costumer, 6, "wed2");
        MakeOrderCommand cmd15 = new MakeOrderCommand(p5, costumer, 4, "ss32");
        cmd13.execute();
        cmd14.execute();
        cmd15.execute();

        store.getStack().add(cmd13);
        store.getStack().add(cmd14);
        store.getStack().add(cmd15);


        store.getAllProducts().add(p6);

        MakeOrderCommand cmd16 = new MakeOrderCommand(p6, costumer, 3, "accvbv");
        MakeOrderCommand cmd17 = new MakeOrderCommand(p6, costumer, 2, "azxcx");
        MakeOrderCommand cmd18 = new MakeOrderCommand(p6, costumer, 4, "snns32");
        cmd16.execute();
        cmd17.execute();
        cmd18.execute();

        store.getStack().add(cmd16);
        store.getStack().add(cmd17);
        store.getStack().add(cmd18);


        store.getAllProducts().add(p7);

        MakeOrderCommand cmd19 = new MakeOrderCommand(p7, costumer, 5, "ddddd",new ExpressShipping(),DHL );
        MakeOrderCommand cmd20 = new MakeOrderCommand(p7, costumer, 3, "qqqq",new ExpressShipping(),DHL);
        MakeOrderCommand cmd21 = new MakeOrderCommand(p7, costumer, 7, "dsdxsd",new StandardShipping(),DHL);
        cmd19.execute();
        cmd20.execute();
        cmd21.execute();

        store.getStack().add(cmd19);
        store.getStack().add(cmd20);
        store.getStack().add(cmd21);


        store.getAllProducts().add(p8);

        MakeOrderCommand cmd22 = new MakeOrderCommand(p8, costumer, 2, "aaa", new StandardShipping(),fedEx);
        MakeOrderCommand cmd23 = new MakeOrderCommand(p8, costumer, 3, "bbb",new StandardShipping(), fedEx);
        MakeOrderCommand cmd24 = new MakeOrderCommand(p8, costumer, 4, "ccc",new StandardShipping(), DHL);
        cmd22.execute();
        cmd23.execute();
        cmd24.execute();

        store.getStack().add(cmd22);
        store.getStack().add(cmd23);
        store.getStack().add(cmd24);

        store.getAllProducts().add(p9);

        MakeOrderCommand cmd25 = new MakeOrderCommand(p9, costumer, 5, "xx",new ExpressShipping(), fedEx);
        MakeOrderCommand cmd26 = new MakeOrderCommand(p9, costumer, 1, "yy",new ExpressShipping(), fedEx);
        MakeOrderCommand cmd27 = new MakeOrderCommand(p9, costumer, 3, "zz",new ExpressShipping(), DHL);
        cmd25.execute();
        cmd26.execute();
        cmd27.execute();

        store.getStack().add(cmd25);
        store.getStack().add(cmd26);
        store.getStack().add(cmd27);
    }
}
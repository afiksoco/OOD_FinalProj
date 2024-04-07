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
        Customer costumer = new Customer("Afik","0251654164");
        // TODO Auto-generated method stub
        Product p1 = new SoldInStore("Vacuum cleaner", "popo", 300, 333 ,8, 12);
        Product p2 = new SoldInStore("TV", "sf3r1", 600, 1500 ,30, 50);
        Product p3 = new SoldInStore("Smart watch", "zzz", 4, 8 , 9,268);
        Product p4 = new SoldToWholesalers("Iphone18SuperPro", "abc", 88, 99 ,4, 34);
        Product p5 = new SoldToWholesalers("PC", "adfaaf2", 12, 99 ,4, 34);
        Product p6 = new SoldToWholesalers("Keyboard", "bzc", 88, 99 ,2, 94);
        Product p7 = new SoldThroughWebsite("Mouse (for PC)", "mouseforpc", 42, 100 ,2, 100, "Israel", true, true  );
        Product p8 = new SoldThroughWebsite("Mouse (animal)", "mouseforcat", 15, 16 ,8, 500, "Israel", false, false  );
        Product p9 = new SoldThroughWebsite("Tami4", "tamiarba", 200, 250 ,16, 77, "Israel", true, false  );


        store.getAllProducts().add(p1);
        Order o1 = new Order(p1, costumer , 2, "asdd2");
        Order o2 = new Order(p1, costumer , 1, "wsdd2");
        Order o3 = new Order(p1, costumer , 3, "warrdd2");
        p1.getAllOrders().add(o1);
        p1.getAllOrders().add(o2);
        p1.getAllOrders().add(o3);
        p1.calcTotalProfit();


        store.getAllProducts().add(p2);
        Order o4 = new Order(p2, costumer , 6, "asd2");
        Order o5 = new Order(p2, costumer , 1, "wqwed2");
        Order o6 = new Order(p2, costumer , 3, "wswq1qqq2");
        p2.getAllOrders().add(o4);
        p2.getAllOrders().add(o5);
        p2.getAllOrders().add(o6);
        p2.calcTotalProfit();


        store.getAllProducts().add(p3);
        Order o7 = new Order(p3, costumer , 1, "asdddd2");
        Order o8 = new Order(p3, costumer , 6, "wed2");
        Order o9 = new Order(p3, costumer , 4, "ss32");
        p3.getAllOrders().add(o7);
        p3.getAllOrders().add(o8);
        p3.getAllOrders().add(o9);
        p3.calcTotalProfit();


        store.getAllProducts().add(p4);
        Order o10 = new Order(p4, costumer , 1, "asdvbv");
        Order o11= new Order(p4, costumer , 2, "azxcx");
        Order o12= new Order(p4, costumer , 4, "snns32");
        p4.getAllOrders().add(o10);
        p4.getAllOrders().add(o11);
        p4.getAllOrders().add(o12);
        p4.calcTotalProfit();


        store.getAllProducts().add(p5);
        Order o13 = new Order(p5, costumer , 1, "asddh2");
        Order o14 = new Order(p5, costumer , 6, "wed2");
        Order o15 = new Order(p5, costumer , 4, "ss32");
        p5.getAllOrders().add(o13);
        p5.getAllOrders().add(o14);
        p5.getAllOrders().add(o15);
        p5.calcTotalProfit();

        // Add product p6
        store.getAllProducts().add(p6);
        Order o16 = new Order(p6, costumer , 3, "accvbv");
        p6.getAllOrders().add(o16);
        Order o17 = new Order(p6, costumer , 2, "azxcx");
        p6.getAllOrders().add(o17);
        Order o18 = new Order(p6, costumer , 4, "snns32");
        p6.getAllOrders().add(o18);
        p6.calcTotalProfit();


        store.getAllProducts().add(p7);
        Order o19 = new Order(p7, costumer , 5, "ddddd");
        p7.getAllOrders().add(o19);
        Order o20 = new Order(p7, costumer , 3, "qqqq");
        p7.getAllOrders().add(o20);
        Order o21 = new Order(p7, costumer , 7, "dsdxsd");
        p7.getAllOrders().add(o21);
        p7.calcTotalProfit();


        store.getAllProducts().add(p8);
        Order o22 = new Order(p8, costumer , 2, "aaa");
        p8.getAllOrders().add(o22);
        Order o23 = new Order(p8, costumer , 3, "bbb");
        p8.getAllOrders().add(o23);
        Order o24 = new Order(p8, costumer , 4, "ccc");
        p8.getAllOrders().add(o24);
        p8.calcTotalProfit();


        store.getAllProducts().add(p9);
        Order o25 = new Order(p9, costumer , 5, "xx");
        p9.getAllOrders().add(o25);
        Order o26 = new Order(p9, costumer , 1, "yy");
        p9.getAllOrders().add(o26);
        Order o27 = new Order(p9, costumer , 3, "zz");
        p9.getAllOrders().add(o27);
        p9.calcTotalProfit();



    }
}
import java.util.Scanner;

public class ProductFactory {
    public static Scanner scanner = new Scanner(System.in);

    public static Product createProduct() {
        String serial, product_name;
        int choice, cost_price, selling_price, stock;

        Product p = null;

        System.out.println(
                "Choose type on product.\n1- Sold through website.\n2- Sold in store.\n3- Sold to wholesalers.");
        choice = scanner.nextInt();

        System.out.println("Enter serial.");
        serial = scanner.next();

        System.out.println("Enter product name.");
        product_name = scanner.next();

        System.out.println("Enter product stock.");
        stock = scanner.nextInt();

        switch (choice) {
            case 1:
                cost_price = getCostPrice(false);

                selling_price =  getSellPrice(false);

                String destCountry;
                Boolean express, standard;
                System.out.println("Enter destination country for shipping.");
                scanner.nextLine();
                destCountry = scanner.nextLine();
                System.out.println("Does the product has express shipping? (True/False)");
                express = scanner.nextBoolean();
                scanner.nextLine();

                System.out.println("Does the product has standard shipping? (True/False)");
                standard = scanner.nextBoolean();
                p = new SoldThroughWebsite(product_name, serial, cost_price, selling_price, stock, destCountry, express, standard);
                break;

            case 2:
                cost_price = getCostPrice(true);

                selling_price =  getSellPrice(true);

                p = new SoldInStore(product_name, serial, cost_price, selling_price, stock);
                break;
            case 3:
                cost_price = getCostPrice(true);

                selling_price =  getSellPrice(true);

                p = new SoldToWholesalers(product_name, serial, cost_price, selling_price, stock);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }

        return p;

    }

    public static int getCostPrice(boolean isILS) {
        if (isILS)
            System.out.println("Enter cost price for product. (in ILS)");
        else
            System.out.println("Enter cost price for product. (in $)");

        int cost_price = scanner.nextInt();

        return cost_price;
    }

    public static int getSellPrice(boolean isILS) {
        if (isILS)
            System.out.println("Enter sell price for product. (in ILS)");
        else
            System.out.println("Enter sell price for product. (in $)");
        int selling_price = scanner.nextInt();

        return selling_price;
    }

}

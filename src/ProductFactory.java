import java.util.Scanner;

public class ProductFactory {
	public static Scanner scanner = new Scanner(System.in);

	public static Product createProduct() {
		String serial, product_name;
		int choice, stock, weight;

		Product p = null;

		System.out.println(
				"Choose type on product.\n1- Sold through website.\n2- Sold in store.\n3- Sold to wholesalers.");

		while (true) {
			choice = scanner.nextInt();

			if (choice >= 1 && choice <= 3) {
				break;
			} else {
				System.out.println("Invalid choice. Please enter a number between 1 and 3.");
			}
		}

		System.out.println("Enter serial.");
		serial = scanner.next();
		scanner.nextLine();
		System.out.println("Enter product name.");
		product_name = scanner.nextLine();

		System.out.println("Enter product stock.");
		stock = scanner.nextInt();

		System.out.println("Enter product weight.");
		weight = scanner.nextInt();

		switch (choice) {
		case 1:
			return createSoldThroughWebsite(serial, product_name, stock, weight);
		case 2:
			return createSoldInStore(serial, product_name, stock, weight);
		case 3:
			return createSoldToWholesalers(serial, product_name, stock, weight);
		default:
			System.out.println("Invalid choice. Product creation failed.");
			return null;
		}
	}

	private static SoldThroughWebsite createSoldThroughWebsite(String serial, String product_name, int stock,
			int weight) {
		int cost_price = getCostPrice(false);
		int selling_price = getSellPrice(false);

		System.out.println("Enter destination country for shipping:");
		scanner.nextLine(); // Consume newline character
		String destCountry = scanner.nextLine();

		System.out.println("Does the product have express shipping? (True/False)");
		boolean express = scanner.nextBoolean();
		System.out.println("Does the product have standard shipping? (True/False)");
		boolean standard = scanner.nextBoolean();

		return new SoldThroughWebsite(product_name, serial, cost_price, selling_price, weight, stock, destCountry,
				express, standard);
	}

	private static SoldInStore createSoldInStore(String serial, String product_name, int stock, int weight) {
		int cost_price = getCostPrice(true);
		int selling_price = getSellPrice(true);
		return new SoldInStore(product_name, serial, cost_price, selling_price, weight, stock);
	}

	private static SoldToWholesalers createSoldToWholesalers(String serial, String product_name, int stock,
			int weight) {
		int cost_price = getCostPrice(true);
		int selling_price = getSellPrice(true);
		return new SoldToWholesalers(product_name, serial, cost_price, selling_price, weight, stock);
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

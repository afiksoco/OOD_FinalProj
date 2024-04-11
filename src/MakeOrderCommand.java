import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MakeOrderCommand implements Command {
	public static Scanner scanner = new Scanner(System.in);
	private List<Observer> observers = new ArrayList<>();
	private Product product;
	private Customer customer;
	private ShippingCompany shippingCompany;
	private ShippingMethod shippingMethod;
	private String serial;
	private int amount;

	private int previousAmount;
	private int previousProfit;

	private Order newOrder;

	public MakeOrderCommand(Product product, Customer customer, int amount, String serial) {
		this.product = product;
		this.customer = customer;
		this.amount = amount;
		this.serial = serial;
	}

	public MakeOrderCommand(Product product, Customer customer, int amount, String serial,
			ShippingMethod shippingMethod, ShippingCompany shippingCompany) {
		this.product = product;
		this.customer = customer;
		this.amount = amount;
		this.serial = serial;
		this.shippingMethod = shippingMethod;
		this.shippingCompany = shippingCompany;
	}

	public MakeOrderCommand(Product product) {
		this.product = product;
		orderInfo();
	}

	public boolean checkIfOrderExists(String serial) { // despite the SET class checks for duplicates, // we want to
														// stop the order info at the beginning

		for (Order o : product.getAllOrders()) {
			if (o.getSerial().equals(serial))
				return true;
		}
		return false;
	}

	public void orderInfo() {
		String cName;
		String cNumber;
		System.out.println("Enter customer name");
		cName = scanner.next();
		System.out.println("Enter customer phone number");
		cNumber = scanner.next();
		customer = new Customer(cName, cNumber);

		System.out.println("Enter serial ID for the order");
		serial = scanner.next();

		if (checkIfOrderExists(serial)) {
			System.out.println("\u001B[31mFailed to take order! serial ID already exists.\u001B[0m");
			return;
		}

		System.out.println("Enter amount (current available amount " + product.getStock() + ")");
		do {
			amount = scanner.nextInt();
			if (amount <= 0) {
				System.out.println("Amount must be greater than 0. Please try again.");
			}
		} while (amount <= 0);

		if (amount > product.getStock()) {
			System.out.println("Not enough in storage! Exiting...");
		}

	}

	@Override
	public void execute() {
		if (amount > product.getStock()) {
			return;
		}

		if (product instanceof SoldThroughWebsite) {

			if (shippingMethod == null)
				shippingMethod = getShippingMethodFromUser(product);
			newOrder = new WebsiteOrder(product, customer, amount, serial, shippingMethod);
			if (shippingCompany == null)
				shippingCompany = notifyObservers((WebsiteOrder) newOrder);
			else {
				((WebsiteOrder) newOrder).setShippingCompany(shippingCompany);
				((WebsiteOrder) newOrder).getShippingMethod()
						.setShippingFees(Calculator.calcShippingFeesUnknown((WebsiteOrder) newOrder));
			}
			((WebsiteOrder) newOrder).setShippingCompany(shippingCompany);

		} else
			newOrder = new Order(product, customer, amount, serial);

		previousProfit = product.getProfit();
		if (product.getAllOrders().add(newOrder)) {
			product.setProfit(Calculator.calcProductTotalProfit(product, amount));
			previousAmount = product.getStock();
			product.setStock(product.getStock() - amount);
		}
	}

	public void printCmdRes() {
		int choice = 0;
		System.out.println("\nOrder received!");
		product.printTableFormat(product);
		System.out.println(newOrder);
		if (product instanceof AccountantInvoiceable || product instanceof CostumerInvoiceable) {
			if (product instanceof AccountantInvoiceable && product instanceof CostumerInvoiceable) {
				do {
					System.out.println("\nWould you like to see invoice? press -1 to skip.");
					System.out.println("\n1. Accountant invoice.\n2. Costumer invoice.\n");
					choice = scanner.nextInt();
					switch (choice) {
					case 1:
						System.out.println(newOrder.getInvoiceForAccountant());
						break;
					case 2:
						System.out.println(newOrder.getInvoiceForCostumer());
						break;
					case -1:
						break;
					default:
						System.out.println("Invalid choice. Please try again.");
					}
				} while (choice != -1);
			} else {
				System.out.println("\nWould you like to see invoice? press 1 to show or -1 to skip.");
				choice = scanner.nextInt();
				if (choice == 1)
					newOrder.showInvoice();
			}
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
		System.out.println("\nDear " + lastOrder.getCostumer() + ", your order #" + lastOrder.getSerial()
				+ " was canceled due to a problem in the warehouse.\n");

		product.getAllOrders().remove(lastOrder);
	}

	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}

	private ShippingCompany notifyObservers(WebsiteOrder order) {
		System.out.println("\nNotifying shipping companies... ");
		System.out.println("Checking for best price... ");
		double minPrice = Integer.MAX_VALUE;
		Observer bestCompany = null;

		for (Observer observer : observers) {
			double price = observer.shippingPrice(order);
			if (price < minPrice) {
				minPrice = price;
				bestCompany = observer;
			}
		}
		order.getShippingMethod().setShippingFees(minPrice);
		return (ShippingCompany) bestCompany;
	}

	private ShippingMethod getShippingMethodFromUser(Product product) {

		SoldThroughWebsite p = (SoldThroughWebsite) product;

		if (p.getExpressShipping() && !p.getStandardShipping())
			return ShippingMethodFactory.createShippingMethod(ShippingMethodName.EXPRESS);
		else if (!p.getExpressShipping() && p.getStandardShipping())
			return ShippingMethodFactory.createShippingMethod(ShippingMethodName.STANDARD);

		System.out.println("\nChoose shipping method:");
		int choice;
		do {
			System.out.println("1. Express Shipping.\n2. Standard Shipping.");
			choice = scanner.nextInt();
		} while (choice != 1 && choice != 2);

		if (choice == 1)
			return ShippingMethodFactory.createShippingMethod(ShippingMethodName.EXPRESS);
		else
			return ShippingMethodFactory.createShippingMethod(ShippingMethodName.STANDARD);
	}

}

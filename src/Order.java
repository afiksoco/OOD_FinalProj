import java.util.Objects;

public class Order {


	private Product product;
	private Customer costumer;
	private  String serial;
	private int amount;
	private  int profit;
	private  int cost;


	private FormatForCostumer invoiceForCostumer;
	private FormatForAccountant invoiceForAccountant;



	public Order(Product product, Customer costumer, int amount, String serial) {
		this.product = product;
		this.costumer = costumer;
		this.amount = amount;
		this.serial = serial;
		this.profit = Calculator.calcOrderProfit(product, amount);
		this.cost = Calculator.calcTotalOrderCost(this);
		this.invoiceForAccountant = InvoiceFactory.createAccountantInvoice(this);
		this.invoiceForCostumer = InvoiceFactory.createCostumerInvoice(this);
	}

	public void showInvoice(){
		if (product instanceof AccountantInvoiceable)
			System.out.println(invoiceForAccountant);
		if (product instanceof CostumerInvoiceable)
			System.out.println(invoiceForCostumer);
	}

	public String getSerial() {
		return serial;
	}

	public Product getProduct() {
		return product;
	}

	public int getProfit() {
		return profit;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getCost() {
		return cost;
	}

	public Customer getCostumer() {
		return costumer;
	}


	public void setCostumer(Customer costumer) {
		this.costumer = costumer;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int compareTo(Object other) {
		if (this == other)
			return 0;
		if (other == null)
			return 1;  // Consider this object greater than null
		if (!(other instanceof Order))
			throw new ClassCastException();
		Order otherOrder = (Order) other;

		// Compare based on the serial field
		return this.serial.compareTo(otherOrder.serial);
	}


	public int hashCode() {
		return Objects.hash(serial);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(serial, other.serial);
	}


	@Override
	public String toString() {
		return String.format("%-20s %-20s %-15s", serial, product.getProduct_name(), profit + " " + product.getCurrency());
	}

}


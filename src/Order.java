import java.util.Objects;

public class Order {


	private Product product;
	private Customer costumer;
	private int amount;
	private  String serial;


	public Order(Product product, Customer costumer, int amount, String serial) {
		this.product = product;
		this.costumer = costumer;
		this.amount = amount;
		this.serial = serial;
	}
	
	public Product getProduct() {
		return product;
	}
	
	
	public void setProduct(Product product) {
		this.product = product;
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
		return "Order serial ID  : " + serial;
	}
}

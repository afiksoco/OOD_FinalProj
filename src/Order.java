public class Order {


	private Product product;
	private Customer costumer;
	private int amount;

	
	public Order(Product product, Customer costumer, int amount) {
		this.product = product;
		this.costumer = costumer;
		this.amount = amount;
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
}

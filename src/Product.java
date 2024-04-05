import java.util.Objects;

public class Product implements Comparable{
	


	private String product_name;
	private String serial;
	private int cost_price; // for the store
	private int selling_price; // for costumer
	private int stock;
	private Order allOrders; // LinkedHashset / ArrayList ?
	private String currency;




	public Product(String product_name,String serial, int cost_price, int selling_price, int stock, String currency) {
		this.product_name = product_name;
		this.serial = serial;
		this.cost_price = cost_price;
		this.selling_price = selling_price;
		this.stock = stock;
		this.currency = currency;
	}
	
	public String getProduct_name() {
		return product_name;
	}
	
	
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	
	
	public int getCost_price() {
		return cost_price;
	}
	
	
	public void setCost_price(int cost_price) {
		this.cost_price = cost_price;
	}
	
	
	public int getSelling_price() {
		return selling_price;
	}
	
	
	public void setSelling_price(int selling_price) {
		this.selling_price = selling_price;
	}
	
	
	public int getStock() {
		return stock;
	}
	
	
	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
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
		Product other = (Product) obj;
		return Objects.equals(serial, other.serial);
	}


	@Override
	public int compareTo(Object other) {
		if (this == other)
			return 0;
		if (other == null)
			return 1;  // Consider this object greater than null
		if (!(other instanceof Product))
			throw new ClassCastException();
		Product otherProduct = (Product) other;

		// Compare based on the serial field
		return this.serial.compareTo(otherProduct.serial);
	}

	@Override
	public String toString() {
		String productType = getClass().getSimpleName();
		String colorCode = "";
		switch(productType) {
			case "SoldToWholesalers":
				colorCode = "\u001B[34m"; // Blue color
				break;
			case "SoldThroughWebsite":
				colorCode = "\u001B[35m"; // Purple color
				break;
			case "SoldInStore":
				colorCode = "\u001B[32m";
				break;
			// Add more cases for other product types if needed
			default:
				colorCode = "\u001B[0m"; // Default color (reset)
		}
		String resetColorCode = "\u001B[0m"; // Reset color
		return String.format("%-29s %-29s %-24s %-24s %-24s %-19s",
				colorCode + productType + resetColorCode,
				colorCode + product_name + resetColorCode,
				colorCode + serial + resetColorCode,
				colorCode + cost_price + " " + currency + resetColorCode,
				colorCode + selling_price + " " + currency + resetColorCode,
				colorCode + stock + resetColorCode);

	}




}

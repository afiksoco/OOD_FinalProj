public class SoldInStore extends Product implements Invoiceable{

	private String currency;
	
	public SoldInStore(String product_name, String serial, int cost_price, int selling_price, int stock) {
		super(product_name,serial, cost_price, selling_price, stock, "ILS");
		this.currency = "ILS";
	}

	public void showInvoice() {		
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
}

public class SoldToWholesalers extends Product implements Invoiceable{

	public SoldToWholesalers(String product_name,String serial, int cost_price, int selling_price, int stock) {
		super(product_name, serial, cost_price, selling_price, stock);
	}

	public void showInvoice() {		
	}
	
}

public class SoldInStore extends Product implements CostumerInvoiceable, AccountantInvoiceable {


	
	public SoldInStore(String product_name,String serial, int cost_price, int selling_price,int weight, int stock) {
		super(product_name, serial, cost_price, selling_price, weight, stock, "ILS");
	}

	public void showInvoice() {

	}



}

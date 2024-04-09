public class SoldToWholesalers extends Product implements AccountantInvoiceable{

	public SoldToWholesalers(String product_name,String serial, int cost_price, int selling_price,int weight, int stock) {
		super(product_name, serial, cost_price, selling_price, weight, stock, "ILS");
	}
}

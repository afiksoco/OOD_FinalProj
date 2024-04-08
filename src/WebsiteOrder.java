public class WebsiteOrder extends Order{

	public WebsiteOrder(Product product, Customer costumer, int amount, String serial) {
		super(product, costumer, amount, serial);
		// TODO Auto-generated constructor stub
	}
    ShippingCompany shippingCompany;
    ShippingMethod shippingMethod;

    public WebsiteOrder(Product product, Customer costumer, int amount, String serial,ShippingMethod shippingMethod) {
        super(product, costumer, amount, serial);
    }
}

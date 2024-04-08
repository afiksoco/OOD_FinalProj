
public class DHL extends ShippingCompany{

	public DHL(String contact, String phoneNumber) {
		super(contact, phoneNumber);
		
	}

	@Override
	public double shippingPrice(WebsiteOrder order) {
		 return Calculator.getShippingPriceForDHL(order);
	}

}

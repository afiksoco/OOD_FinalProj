public class DHL extends ShipmentCompany{

	public DHL(String contact, String phoneNumber) {
		super(contact, phoneNumber);
	}

	@Override
	public int shippingPrice(Order order) {
		return 0;
	}

}

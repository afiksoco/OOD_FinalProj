public class FedEx extends ShipmentCompany {

	public FedEx(String contact, String phoneNumber) {
		super(contact, phoneNumber);
	}

	@Override
	public int shippingPrice(Order order) {
		return 0;
	}

}

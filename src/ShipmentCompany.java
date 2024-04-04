public class ShipmentCompany{
    private String contact;
    private String phoneNumber;
	private ShippingType shippingType;

	
	public ShipmentCompany(String contact, String phoneNumber, ShippingType shippingType) {
		this.setContact(contact);
		this.setPhoneNumber(phoneNumber);
		this.shippingType = shippingType;
	}

	   public double calculateStandardShippingCost() {
	        return shippingType.calculateStandardShippingFees();
	    }

	    public double calculateExpressShippingCost() {
	        return shippingType.calculateExpressShippingFees();
	    }

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		public String getContact() {
			return contact;
		}

		public void setContact(String contact) {
			this.contact = contact;
		}
}

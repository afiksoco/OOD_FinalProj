public abstract class  ShipmentCompany implements OrderObserver{
    private String contact;
    private String phoneNumber;
	private ShippingType shippingType;

	
	public ShipmentCompany(String contact, String phoneNumber) { // removed shipping type  to check something!!
		this.setContact(contact);
		this.setPhoneNumber(phoneNumber);

	}
		public ShipmentCompany getCompany(){
		return this;
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

		@Override
		public abstract int shippingPrice(Order order);
}

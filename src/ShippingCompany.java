public abstract class  ShippingCompany implements Observer {
    private String contact;
    private String phoneNumber;

	public ShippingCompany(String contact, String phoneNumber) { // removed shipping type  to check something!!
		this.setContact(contact);
		this.setPhoneNumber(phoneNumber);

	}
		public ShippingCompany getCompany(){
		return this;
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
		public abstract double shippingPrice(WebsiteOrder order);
}

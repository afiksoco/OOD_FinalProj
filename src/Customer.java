public class Customer {
	private String customer_name;
	private String mobile;
	
	public Customer(String customer_name, String mobile) {
		this.customer_name = customer_name;
		this.mobile = mobile;
	}
	
	
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}

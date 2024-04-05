public class SoldThroughWebsite extends Product implements Shipable {


	private String destCountry;
	private ShippingType shippingType;
	private Boolean expressShipping;
    private Boolean standardShipping;

	public SoldThroughWebsite(String product_name,String serial, int cost_price, int selling_price, int stock, String destCountry,Boolean expressShipping, Boolean standardShipping ) {
		super(product_name, serial, cost_price, selling_price, stock, "$");
		this.destCountry = destCountry;
		this.expressShipping = expressShipping; 
        this.standardShipping = standardShipping;
	}

	public Boolean getExpressShipping() {
		return expressShipping;
	}

	public void setExpressShipping(Boolean expressShipping) {
		this.expressShipping = expressShipping;
	}

	public Boolean getStandardShipping() {
		return standardShipping;
	}

	public void setStandardShipping(Boolean standardShipping) {
		this.standardShipping = standardShipping;
	}

	public ShippingType getShippingType() {
		return shippingType;
	}
	
	public void setShippingType(ShippingType shippingType) {
		this.shippingType = shippingType;
	}
	
	
	public void ship(ShippingType shippingType) {
		if (shippingType != null) {
			double shippingFees = 0.0;
			if (isExpressShipping(shippingType)) {
				shippingFees = shippingType.calculateExpressShippingFees();
			} else {
				shippingFees = shippingType.calculateStandardShippingFees();
			}
			System.out.println("For shipping method " + (isExpressShipping(shippingType) ? "Express" : "Regular")
					+ " the shipping fees is: " + shippingFees);
		} else {
			System.out.println("Shipping type is not set.");
		}
	}

	public boolean isExpressShipping(ShippingType shippingType) {
		return shippingType instanceof FedExShipping || shippingType instanceof DHLShipping;
	}

	public String getDestCountry() {
		return destCountry;
	}

	public void setDestCountry(String destCountry) {
		this.destCountry = destCountry;
	}


	@Override

	public String toString() {
		String resetColorCode = "\u001B[0m";
		String colorCode = "\u001B[35m";
		return super.toString() +
				String.format("%-15s %-20s %-20s",
					colorCode +	destCountry,  colorCode + expressShipping,  colorCode +  standardShipping + resetColorCode);
	}



}

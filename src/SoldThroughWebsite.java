public class SoldThroughWebsite extends Product implements Shipable {


	private String destCountry;
	private Boolean expressShipping;
    private Boolean standardShipping;

	public SoldThroughWebsite(String product_name,String serial, int cost_price, int selling_price,int weight, int stock, String destCountry,Boolean expressShipping, Boolean standardShipping ) {
		super(product_name, serial, cost_price, selling_price,weight, stock, "$");
		this.destCountry = destCountry;
		this.expressShipping = expressShipping; 
        this.standardShipping = standardShipping;
		setShippingMethods(expressShipping,standardShipping);
	}




	public void  setShippingMethods(Boolean expressShipping, Boolean standardShipping){
		if(!expressShipping && !standardShipping)
			this.standardShipping = true;
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
				String.format("%-20s %-25s %-20s",
					colorCode +	destCountry,  colorCode + standardShipping,  colorCode +  expressShipping + resetColorCode);
	}



}

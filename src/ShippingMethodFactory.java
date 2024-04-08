
public class ShippingMethodFactory {
	
	public static ShippingMethod createShippingMethod(ShippingMethodName method,int shippingFees) {
		if(method == ShippingMethodName.EXPRESS)
			return new ExpressShipping(shippingFees);
		if(method == ShippingMethodName.STANDARD)
			return new StandardShipping(shippingFees);
		else throw new IllegalArgumentException();

	}
}

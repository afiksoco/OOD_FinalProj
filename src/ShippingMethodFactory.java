
public class ShippingMethodFactory {
	
	public static ShippingMethod createShippingMethod(ShippingMethodName method,int shippingFees) {
		if(method == ShippingMethodName.EXPRESS)
			return new ExpressShippingMethod(shippingFees);
		if(method == ShippingMethodName.STANDARD)
			return new StandardShippingMethod(shippingFees);
		else throw new IllegalArgumentException();

	}
}


public class ShippingMethodFactory {
	
	public static ShippingMethod createShippingMethod(ShippingMethodName method) {
		if(method == ShippingMethodName.EXPRESS)
			return new ExpressShipping();
		if(method == ShippingMethodName.STANDARD)
			return new StandardShipping();
		else throw new IllegalArgumentException();

	}
}

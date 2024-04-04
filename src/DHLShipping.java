public class DHLShipping extends ShippingType {

	double calculateRegularShippingFees() {
		return 0;
	}

	@Override
	double calculateStandardShippingFees() {
		return 0;
	}

	double calculateExpressShippingFees() {
		return 0;
	}

}

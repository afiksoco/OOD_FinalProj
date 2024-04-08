
public class FedEx extends ShippingCompany {

    public FedEx(String contact, String phoneNumber) {
        super(contact, phoneNumber);
    }

    @Override
    public double shippingPrice(WebsiteOrder order) {
        return Calculator.getShippingPriceForFedEx(order);
    }

}

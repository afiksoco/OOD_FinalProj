public class WebsiteOrder extends Order{

    private ShippingCompany shippingCompany;
    private ShippingMethod shippingMethod;



    public WebsiteOrder(Product product, Customer costumer, int amount, String serial,ShippingMethod shippingMethod) {
        super(product, costumer, amount, serial);
        this.shippingMethod = shippingMethod;
    }

    @Override

    public String toString() {
        String baseString = super.toString(); // calling the superclass's toString() method
        return String.format("%s %-20s %-20s %-15s %-15s ", baseString, shippingCompany.getClass().getSimpleName(), shippingMethod.getClass().getSimpleName()
                , shippingMethod.getShippingFees() + " $", getCost()+ " $");
    }

    public void setShippingCompany(ShippingCompany shippingCompany) {
        this.shippingCompany = shippingCompany;
    }

    public ShippingCompany getShippingCompany() {
        return shippingCompany;
    }

    public ShippingMethod getShippingMethod() {
        return shippingMethod;
    }

    @Override
    public Product getProduct() {
        return super.getProduct();
    }
}

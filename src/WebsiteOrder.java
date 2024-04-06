public class WebsiteOrder extends Order{
    ShipmentCompany shipmentCompany;
    public enum ShipmentMethod {
        EXPRESS,
        STANDARD
    }

    public WebsiteOrder(Product product, Customer costumer, int amount, String serial,ShipmentMethod shipmentMethod) {
        super(product, costumer, amount, serial);
    }
}

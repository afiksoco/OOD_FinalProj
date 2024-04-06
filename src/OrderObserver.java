public interface OrderObserver {
    int shippingPrice(Order order);

    ShipmentCompany getCompany();
}

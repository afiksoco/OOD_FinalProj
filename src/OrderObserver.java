public interface OrderObserver {
    int shippingPrice(Order order);

    ShippingCompany getCompany();
}

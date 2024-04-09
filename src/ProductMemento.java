import java.util.LinkedHashSet;
import java.util.Set;

public class ProductMemento {
    private Set<Order> orders;

    public ProductMemento(Set<Order> orders) {
        this.orders = new LinkedHashSet<>(orders); // Make a defensive copy
    }

    public Set<Order> getOrders() {
        return new LinkedHashSet<>(orders); // Return a defensive copy
    }
}

import java.time.LocalDateTime;

public class Invoice {
    Order order;
    LocalDateTime dateTime;


    public Invoice(Order order){
        this.order = order;
        dateTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Invoice :" +
                " order : " + order.getSerial() +
                ", amount :" + order.getAmount() +
                ", prooduct name :" + order.getProduct().getProduct_name();
    }
}

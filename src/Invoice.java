import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Invoice {
    Order order;
    LocalDateTime dateTime;

    String formattedDateTime;

    public Invoice(Order order){
        this.order = order;
        dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        formattedDateTime  = dateTime.format(formatter);

    }

    @Override
    public String toString() {
        return
                "\nOrder: " + order.getSerial() +
                "\nAmount: " + order.getAmount() +
                "\nProoduct name: " + order.getProduct().getProduct_name() +
                "\nTotal cost:  " + Calculator.calcTotalCostForCostumer(order.getProduct(), order.getAmount())+
                 " " + order.getProduct().getCurrency();
    }
}

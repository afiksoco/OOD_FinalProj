public class FormatForAccountant extends Invoice {
    public FormatForAccountant(Order order) {
        super(order);
    }

    @Override
    public String toString() {
        return super.toString() + " , profit : " + order.getProfit() + " " + order.getProduct().getCurrency() +
                ", date " + formattedDateTime;
    }
}


public class FormatForAccountant extends Invoice {
    public FormatForAccountant(Order order) {
        super(order);
    }

    @Override
    public String toString() {
        return "\u001B[4mInvoice for accountant\u001B[0m"
                +  super.toString() + "\nProfit: " + order.getProfit() + " " + order.getProduct().getCurrency() +
                "\nDate " + formattedDateTime + "\n";
    }
}


public class FormatForCostumer extends  Invoice{
    double taxes;
    public FormatForCostumer(Order order) {
        super(order);
        this.taxes = Calculator.calcTaxesForOrder(order);
    }

    @Override
    public String toString() {
        return "\u001B[4mInvoice for costumer\u001B[0m" +
                super.toString()  + "\nTaxes: " + String.format("%.2f", taxes) +" " + order.getProduct().getCurrency()+ "\nDate : " +formattedDateTime + "\n";
    }
}

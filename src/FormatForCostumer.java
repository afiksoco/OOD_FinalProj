public class FormatForCostumer extends  Invoice{
    double taxes;
    public FormatForCostumer(Order order) {
        super(order);
        this.taxes = Calculator.calcTaxesForOrder(order);
    }

    @Override
    public String toString() {
        return super.toString()  + ", taxes : " + taxes +" " + order.getProduct().getCurrency()+ ", date " +formattedDateTime;
    }
}

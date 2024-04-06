public class FormatForCostumer extends  Invoice{

    public FormatForCostumer(Order order) {
        super(order);
    }

    @Override
    public String toString() {
        return super.toString() + ", total cost :  " + Calculator.calcTotalCostForCostumer(order.getProduct(), order.getAmount())
        + " " + order.getProduct().getCurrency() + ", taxes : " + Calculator.calcTaxesForOrder(order);
    }
}

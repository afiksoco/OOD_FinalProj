public class InvoiceFactory {


    public static FormatForCostumer createCostumerInvoice(Order order){
        return  new FormatForCostumer(order);
    }
    public static FormatForAccountant createAccountantInvoice(Order order){
        return  new FormatForAccountant(order); }
}

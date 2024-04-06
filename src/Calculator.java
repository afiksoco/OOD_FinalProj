import java.util.Set;

public class Calculator {

    public static final int TAX = 17;
    public static final int $ = 4;

    public static int calcProductTotalProfit(Product product,int amount){
        int profit;
        profit = product.getProfit()+((product.getSelling_price()-product.getCost_price())*amount);
        return profit;
    }

    public static int calcOrderProfit(Product product,int amount){
        int profit;
        profit = ((product.getSelling_price()-product.getCost_price())*amount);
        return profit;
    }

    public static int calcTotalCostForCostumer(Product product, int amount){
        return  product.getSelling_price()*amount;
    }

    public static double  calcTaxesForOrder(Order order){
        double taxes = order.getCost() * (TAX / 100.0);
        return  taxes;
    }

    public static int calcTotalOrderCost(Order order) {
       return order.getProduct().getSelling_price() * order.getAmount();
    }

    public static int calcTotalProfitInILS(Set<Product> allProducts) {
        int sum = 0;
        int currency;
        for (Product product : allProducts){
            currency = 1;
            if(product.getCurrency().equals("$")){
                currency = $;
            }
            sum+=(product.getProfit()*currency);
        }
        return sum;
    }
}

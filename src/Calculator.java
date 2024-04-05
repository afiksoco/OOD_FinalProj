public class Calculator {
    public static int calcProductProfit(Product product,int amount){
        int profit;
        profit = ((product.getSelling_price()-product.getCost_price())*amount);
        return profit;
    }
}

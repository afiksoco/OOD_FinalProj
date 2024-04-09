import java.util.Set;

public class Calculator {

    public static final int TAX = 17;
    public static final int $ = 4;

    public static final int IMPORT_TAX = 20;
    public static final int DHL_EXPRESS_BASE_PRICE = 100;

    public static final double DHL_STANDARD_BASE_PERCENT = 0.1;
    public static final int FEDEX_EXPRESS_WEIGHT_COST_FOR_10_KG = 50;
    public static final int FEDEX_STANDARD_WEIGHT_COST_FOR_10_KG = 10;


    public static int calcProductTotalProfit(Product product, int amount) {
        int profit;
        profit = product.getProfit() + ((product.getSelling_price() - product.getCost_price()) * amount);
        return profit;
    }

    public static int calcOrderProfit(Product product, int amount) {
        int profit;
        profit = ((product.getSelling_price() - product.getCost_price()) * amount);
        return profit;
    }

    public static int calcTotalCostForCostumer(Product product, int amount) {
        return product.getSelling_price() * amount;
    }

    public static double calcTaxesForOrder(Order order) {
        double taxes = order.getCost() * (TAX / 100.0);
        return taxes;
    }

    public static int calcTotalOrderCost(Order order) {
        return order.getProduct().getSelling_price() * order.getAmount();
    }

    public static int calcTotalProfitInILS(Set<Product> allProducts) {
        int sum = 0;
        int currency;
        for (Product product : allProducts) {
            currency = 1;
            if (product.getCurrency().equals("$")) {
                currency = $;
            }
            sum += (product.getProfit() * currency);
        }
        return sum;
    }

    public static double getShippingPriceForDHL(WebsiteOrder websiteOrder) {
        if (websiteOrder.getShippingMethod() instanceof ExpressShipping)
            return IMPORT_TAX + DHL_EXPRESS_BASE_PRICE;
        else if (websiteOrder.getShippingMethod() instanceof StandardShipping)
            return getShippingFeesForDHLStandard(websiteOrder.getProduct());
        return 0;
    }

    public static double getShippingFeesForDHLStandard(Product product) {
        double fees = product.getSelling_price() * DHL_STANDARD_BASE_PERCENT;
        if (fees < 100)
            return fees;
        return 100;
    }

    public static double getShippingPriceForFedEx(WebsiteOrder websiteOrder) {
        if (websiteOrder.getShippingMethod() instanceof ExpressShipping)
            return getBasePriceForFedexByWeight(FEDEX_EXPRESS_WEIGHT_COST_FOR_10_KG, websiteOrder.getProduct()) + IMPORT_TAX;
        else if (websiteOrder.getShippingMethod() instanceof StandardShipping)
            return getBasePriceForFedexByWeight(FEDEX_STANDARD_WEIGHT_COST_FOR_10_KG, websiteOrder.getProduct());
        return 0;
    }

    public static int getBasePriceForFedexByWeight(int base, Product product) {
        int weight = product.weight / 10 * 10;
        return base*weight;
    }

    public static double calcShippingFeesUnknown(WebsiteOrder websiteOrder){
        if (websiteOrder.getShippingCompany() instanceof DHL)
            return getShippingPriceForDHL(websiteOrder);

        if (websiteOrder.getShippingCompany() instanceof FedEx)
            return getShippingPriceForFedEx(websiteOrder);

        return 0;
    }
}

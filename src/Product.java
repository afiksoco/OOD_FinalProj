import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public abstract class Product implements Comparable<Object> {


    private String product_name;
    private String serial;
    private int cost_price; // for the store
    private int selling_price; // for costumer
    private int stock;
    int weight;

    private LinkedHashSet<Order> allOrders = new LinkedHashSet<>();
    private String currency;
    private int totalProfit;

    private Memento memento;


    public Product(String product_name, String serial, int cost_price, int selling_price, int weight, int stock, String currency) {
        this.product_name = product_name;
        this.serial = serial;
        this.cost_price = cost_price;
        this.selling_price = selling_price;
        this.stock = stock;
        this.currency = currency;
        this.totalProfit = 0;
        this.weight = weight;

    }

    public void setAllOrders(LinkedHashSet<Order> allOrders) {
        this.allOrders = allOrders;
    }

    public void orderProduct() {
        System.out.println();
        MakeOrderCommand command = new MakeOrderCommand(this);

    }

    public String getProduct_name() {
        return product_name;
    }


    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }


    public int getCost_price() {
        return cost_price;
    }


    public void setCost_price(int cost_price) {
        this.cost_price = cost_price;
    }


    public int getSelling_price() {
        return selling_price;
    }


    public void setSelling_price(int selling_price) {
        this.selling_price = selling_price;
    }


    public int getStock() {
        return stock;
    }


    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public int hashCode() {
        return Objects.hash(serial);
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
        return Objects.equals(serial, other.serial);
    }


    @Override
    public int compareTo(Object other) {
        if (this == other)
            return 0;
        if (other == null)
            return 1;  // Consider this object greater than null
        if (!(other instanceof Product))
            throw new ClassCastException();
        Product otherProduct = (Product) other;

        // Compare based on the serial field
        return this.serial.compareTo(otherProduct.serial);
    }

    @Override
    public String toString() {
        String productType = getClass().getSimpleName();
        String colorCode = "";
        switch (productType) {
            case "SoldToWholesalers":
                colorCode = "\u001B[34m"; // Blue color
                break;
            case "SoldThroughWebsite":
                colorCode = "\u001B[35m"; // Purple color
                break;
            case "SoldInStore":
                colorCode = "\u001B[32m";
                break;
            // Add more cases for other product types if needed
            default:
                colorCode = "\u001B[0m"; // Default color (reset)
        }
        String resetColorCode = "\u001B[0m"; // Reset color
        return String.format("%-29s %-29s %-24s %-24s %-24s %-20s",
                colorCode + productType + resetColorCode,
                colorCode + product_name + resetColorCode,
                colorCode + serial + resetColorCode,
                colorCode + cost_price + " " + currency + resetColorCode,
                colorCode + selling_price + " " + currency + resetColorCode,
                colorCode + stock + resetColorCode);

    }


    public LinkedHashSet<Order> getAllOrders() {
        return allOrders;
    }

    public void showDetailedInfo() {
        System.out.println(this);
        if (allOrders.isEmpty()) {
            System.out.println("No orders for this product!");
            return;
        }
        showAllOrdersAndInvoices();
        System.out.println("Total profits from orders: " + getProfit() + " " + currency);

    }


    public void showAllOrdersAndInvoices() {
        /*printTableFormat(this);*/
        for (Order o : allOrders) {
            printTableFormat(this);
            System.out.println(o);
            o.showInvoice();
        }
    }

    public void showAllOrders() {
        printTableFormat(this);
        for (Order o : allOrders) {
            System.out.println(o);
        }
    }

    public void printTableFormat(Product product) {
        if (product instanceof SoldThroughWebsite) {
            System.out.printf("%-20s %-20s %-15s %-20s %-20s %-15s %-15s\n", "Order serial ID", "Product name", "Order profit"
                    , "Shipping company", "Shipping method", "Shipping fees", "Product cost");

        } else
            System.out.printf("%-20s %-20s %-15s\n", "Order serial ID", "Product name", "Order profit");
    }

    public void calcTotalProfit() {
        int profit;
        int total = 0;
        for (Order o : allOrders) {
            profit = (selling_price - cost_price) * o.getAmount();
            total += profit;
        }
        this.totalProfit = total;
    }

    public int getProfit() {
        return totalProfit;
    }

    public String getCurrency() {
        return currency;
    }

    public void setProfit(int profit) {
        this.totalProfit = profit;
    }


    public void createMemento() {
        this.memento = new Memento(allOrders, totalProfit, stock);
    }

    public void setMemento(Memento m) {
        if (m == null)
            return;
        allOrders = new LinkedHashSet<>(m.getAllOrders());
        totalProfit = m.totalProfit;
        stock = m.stock;
    }

    public Memento getMemento() {
        return memento;
    }

    public static class Memento {
        private LinkedHashSet<Order> allOrders;
        private int totalProfit;
        private int stock;
        public Memento(Set<Order> allOrders, int totalProfit, int stock) {
            this.allOrders = new LinkedHashSet<>(allOrders);
            this.stock = stock;
            this.totalProfit = totalProfit;
        }

        public Set<Order> getAllOrders() {
            return allOrders;
        }
    }
}


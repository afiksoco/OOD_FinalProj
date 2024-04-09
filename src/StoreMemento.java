import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class StoreMemento {
    private Set<Product> allProducts;
    private Stack<Command> stack;

    public StoreMemento(Set<Product> allProducts, Stack<Command> stack) {
        this.allProducts = new TreeSet<>();
        this.allProducts.addAll(allProducts);
        for(Product product : allProducts){
            product.saveMemento();
        }

        this.stack = new Stack<>();
        this.stack.addAll(stack);
    }

    public Set<Product> getAllProducts() {
        return allProducts;
    }

    public Stack<Command> getStack() {
        return stack;
    }
}

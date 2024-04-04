import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;


public class Store {
	public static Scanner scanner = new Scanner(System.in);
	private Set<Product> allProducts = new TreeSet<>(); //sort by String
	private static Store instance;



	public static Store getInstance() { //singleton
		if(instance == null) instance= new Store();
		return instance;
	}

	public Set<Product> getAllProducts() {
		return allProducts;
	}

	public void setAllProducts(Set<Product> allProducts) {
		this.allProducts = allProducts;
	}

	public void addProductToStore(){
		if(allProducts.add(ProductFactory.createProduct()))
			System.out.println("Product added succ");
	}

	public void showAllProducts() {
		System.out.println("All Products:");
		for (Product product : allProducts) {
			System.out.println(product);
		}
	}

}

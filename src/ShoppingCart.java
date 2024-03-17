import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private Map<Product, Integer> products;


    public ShoppingCart() {
        this.products = new HashMap<>();
        
    }

    // Add product to cart
    public void addToCart(Product product, int quantity) {
        this.products.put(product, quantity);
    }

    // Remove product from cart
    public void removeFromCart(Product product) {
        this.products.remove(product);
    }

    // Calculate total cost of all products in the cart
    public double calculateTotalCost() {
        double totalCost = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            totalCost += entry.getKey().getPrice() * entry.getValue();
        }
        return totalCost;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    
}
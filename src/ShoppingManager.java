import java.util.List;

public interface ShoppingManager {
    void addProduct(Product product);
    void removeProduct(String productId);
    void openGUI();
    void printProductList();
    void saveProductsToFile();
    void loadProductsFromFile();
}

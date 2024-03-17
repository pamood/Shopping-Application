//  Author: Pamood Jayaratne
//  IIT ID : 20220163
//  Description: 5COSC019C Object Oriented Programming – Coursework (2023/24)

public interface ShoppingManager {
    void addProduct(Product product);
    void removeProduct(String productId);
    void openGUI();
    void printProductList();
    void saveProductsToFile();
    void loadProductsFromFile();
}

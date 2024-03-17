//  Author: Pamood Jayaratne
//  IIT ID : 20220163
//  Description: 5COSC019C Object Oriented Programming – Coursework (2023/24)
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private Map<Product, Integer> products;
    public ShoppingCart() {
        this.products = new HashMap<>();
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    // Calculate total cost of all products in the cart
    public double calculateTotalCost() {
        double totalCost = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            totalCost += entry.getKey().getPrice() * entry.getValue();
        }
        return totalCost;
    }

    // Calculate three item discount for the cart
    public double calculateThreeItemsDiscount() {
        double discount = 0;
        Map<String, Integer> categoryCounts = new HashMap<>();

        for (Product product : products.keySet()) {
            String productString = product.toString();
            String category = productString.substring(0, productString.indexOf(" "));

            int currentCount = categoryCounts.getOrDefault(category, 0);
            categoryCounts.put(category, currentCount + products.get(product));

            if (categoryCounts.get(category) >= 3) {
                discount = calculateTotalCost() * 0.2;
                break;
            }
        }

        return discount;
    }


    // Calculate first purchase discount for the cart
     public double calculateFirstPurchaseDiscount() {
         if(User.isFirstPurchase()){
             return calculateTotalCost() * 0.1;
         }else{
             return 0;
         }
 }
}
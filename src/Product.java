//  Author: Pamood Jayaratne
//  IIT ID : 20220163
//  Description: 5COSC019C Object Oriented Programming – Coursework (2023/24)
import java.io.Serializable;

public abstract class Product implements Serializable {
    private String productID;
    private String productName;
    private int availableQuantity;
    private double price;

    public Product(String productID, String productName, int availableQuantity, double price) {
        this.productID = productID;
        this.productName = productName;
        this.availableQuantity = availableQuantity;
        this.price = price;
    }

    public String getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public double getPrice() {
        return price;
    }

    public abstract String getProductDetails();

    @Override
    public String toString() {
        return getProductDetails();
    }
}
	
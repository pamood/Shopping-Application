//  Author: Pamood Jayaratne
//  IIT ID : 20220163
//  Description: 5COSC019C Object Oriented Programming – Coursework (2023/24)
public class Clothing extends Product {
    private String size;
    private String color;

    public Clothing(String productID, String productName, int availableQuantity, double price, String size, String color) {
        super(productID, productName, availableQuantity, price);
        this.size = size;
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String getProductDetails() {
        return "Clothing: " +
                "product ID='" + getProductID() + '\'' +
                ", product Name='" + getProductName() + '\'' +
                ", available Quantity=" + getAvailableQuantity() +
                ", price=" + getPrice() +
                ", size='" + getSize() + '\'' +
                ", color=" + getColor();
    }

    @Override
    public String toString() {
        return getProductDetails();
    }
}

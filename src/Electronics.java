//  Author: Pamood Jayaratne
//  IIT ID : 20220163
//  Description: 5COSC019C Object Oriented Programming – Coursework (2023/24)
public class Electronics extends Product{
    private String brand;
    private int warrantyPeriod;

    public Electronics(String productID, String productName, int availableQuantity, double price, String brand, int warrantyPeriod) {
        super(productID, productName, availableQuantity, price);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    public String getBrand() {
        return brand;
    }


    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }


    @Override
    public String getProductDetails() {
        return "Electronics: " +
                "product ID='" + getProductID() + '\'' +
                ", product Name='" + getProductName() + '\'' +
                ", available Quantity=" + getAvailableQuantity() +
                ", price=" + getPrice() +
                ", brand='" + getBrand() + '\'' +
                ", warranty Period=" + getWarrantyPeriod() ;
    }
    
    @Override
    public String toString() {
        return getProductDetails();
    }
}

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

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
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

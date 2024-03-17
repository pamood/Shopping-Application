//  Author: Pamood Jayaratne
//  IIT ID : 20220163
//  Description: 5COSC019C Object Oriented Programming – Coursework (2023/24)
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager {
    private static ArrayList<Product> products;
    private static final int MAX_PRODUCTS = 50;

    public WestminsterShoppingManager() {
        this.products = new ArrayList<>();
        loadProductsFromFile();
    }

    //get products
    public static ArrayList<Product> getProducts(){
        return products;
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        int option = -1; // Initialize option to a default value

        do {
            System.out.println("---------------------------------------");
            System.out.println("-- Westminster Shopping Manager Menu --");
            System.out.println("---------------------------------------");
            System.out.println("1. Add a new product");
            System.out.println("2. Remove a product");
            System.out.println("3. Print product list");
            System.out.println("4. Load products from file");
            System.out.println("5. Save products to file");
            System.out.println("6. Open GUI");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            try {
                option = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next(); // discard the invalid input
                continue; // skip the rest of the loop and start over
            }

            switch (option) {
                case 1:
                    addProductMenu();
                    break;
                case 2:
                    removeProductMenu();
                    break;
                case 3:
                    printProductList();
                    break;
                case 4:
                    loadProductsFromFile();
                    break;
                case 5:
                    saveProductsToFile();
                    break;
                case 6:
                    openGUI();
                    break;
                case 0:
                    System.out.println("Exiting.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        } while (option != 0);
    }

    public void openGUI() {
        try {
            new LoginGUI();
        } catch (RuntimeException e) {
            System.out.println("An error occurred while opening the GUI: " + e.getMessage());
        }
    }

    @Override
    public void addProduct(Product product) {
        if (product == null) {
            System.out.println("Product cannot be null.");
            return;
        }
    
        if (products.size() == MAX_PRODUCTS) {
            System.out.println("System is full, cannot add more products.");
            return;
        }
    
        try {
            products.add(product);
            System.out.println("Product " + product.getProductName() + " successfully added.");
        } catch (NullPointerException e) {
            System.out.println("An error occurred while adding the product: " + e.getMessage());
        }
    
        saveProductsToFile();
    }

    @Override
    public void removeProduct(String productId) {
        try {
            Product productToRemove = findProduct(productId);
            if (productToRemove == null) {
                System.out.println("Product not found.");
                return;
            }
            products.remove(productToRemove);
            System.out.println("Product " + productToRemove.getProductName() + " successfully removed.");
            System.out.println("Total products remaining: " + products.size());
            saveProductsToFile();
        } catch (RuntimeException e) {
            System.out.println("An error occurred while removing the product: " + e.getMessage());
        }
    }


    public Product findProduct(String productId) {
        try {
            for (Product product : products) {
                if (product.getProductID().equals(productId)) {
                    return product;
                }
            }
        } catch (NullPointerException e) {
            System.out.println("An error occurred while finding the product: " + e.getMessage());
        }
        return null;
    }

    private void addProductMenu() {
    Scanner scanner = new Scanner(System.in);
    int type;

    System.out.println("\nAdd Product Menu:");
    System.out.println("1. Add Electronics");
    System.out.println("2. Add Clothing");

    System.out.print("Enter your choice: ");
    try {
        type = scanner.nextInt();
    } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter an integer.");
        scanner.next(); // consume the invalid input
        return;
    }

    switch (type) {
        case 1:
            addElectronicsProduct();
            break;
        case 2:
            addClothingProduct();
            break;
        default:
            System.out.println("Invalid option.");
            break;
    }
}
 private void removeProductMenu() {
    Scanner scanner = new Scanner(System.in);
    String productId;

    try {
        System.out.print("Enter the product ID of the product to remove: ");
        productId = scanner.next(); 
        removeProduct(productId);
    } catch (NoSuchElementException e) {
        System.out.println("Product with ID does not exist.");
    } catch (Exception e) {
        System.out.println("An error occurred: " + e.getMessage());
    }
}

private void addElectronicsProduct() {
    Scanner scanner = new Scanner(System.in);
    String productId, productName, brand;
    int availableItems, warrantyPeriod;
    double price;

    try {
        System.out.print("Product ID: ");
        productId = scanner.next();

        System.out.print("Product name: ");
        productName = scanner.next();

        System.out.print("Brand: ");
        brand = scanner.next();

        System.out.print("Available items: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid integer for available items.");
            scanner.next(); 
        }
        availableItems = scanner.nextInt();

        System.out.print("Price: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid double for price.");
            scanner.next(); 
        }
        price = scanner.nextDouble();

        System.out.print("Warranty period (in months): ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid integer for warranty period.");
            scanner.next(); 
        }
        warrantyPeriod = scanner.nextInt();

        Electronics electronicsProduct = new Electronics(productId, productName, availableItems, price, brand, warrantyPeriod);
        addProduct(electronicsProduct);
    } catch (Exception e) {
        System.out.println("An error occurred: " + e.getMessage());
    }
}

private void addClothingProduct() {
    Scanner scanner = new Scanner(System.in);
    String productId, productName, size, color;
    int availableItems;
    double price;

    try {
        System.out.print("Product ID: ");
        productId = scanner.next();

        System.out.print("Product name: ");
        productName = scanner.next();

        System.out.print("Size: ");
        size = scanner.next();

        System.out.print("Color: ");
        color = scanner.next();

        System.out.print("Available items: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid integer for available items.");
            scanner.next(); 
        }
        availableItems = scanner.nextInt();

        System.out.print("Price: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid double for price.");
            scanner.next(); 
        }
        price = scanner.nextDouble();

        Clothing clothingProduct = new Clothing(productId, productName, availableItems, price, size, color);
        addProduct(clothingProduct);
    } catch (Exception e) {
        System.out.println("An error occurred: " + e.getMessage());
    }
}

public void printProductList() {
    System.out.println("\nProduct List:");

    if (products.isEmpty()) {
        System.out.println("No products available.");
    } else {
        try {
            Collections.sort(products, (p1, p2) -> p1.getProductID().compareTo(p2.getProductID()));

            for (Product product : products) {
                System.out.println(product);
                System.out.println(); 
            }
        } catch (RuntimeException e) {
            System.out.println("An error occurred while printing the product list: " + e.getMessage());
        }
    }
}

public void saveProductsToFile() {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("products.txt"))) {
        oos.writeObject(products);
        System.out.println("Products saved successfully to the file.");
    } catch (IOException e) {
        System.out.println("An error occurred while saving the products to the file: " + e.getMessage());
    }
}

public void loadProductsFromFile() {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("products.txt"))) {
        Object loadedObject = ois.readObject();

        if (loadedObject instanceof List<?>) {
            List<?> loadedProducts = (List<?>) loadedObject;

            // Clear the existing products list before adding loaded products
            products.clear();

            // Add each product from the loaded list to the products list
            for (Object loadedProduct : loadedProducts) {
                if (loadedProduct instanceof Product) {
                    products.add((Product) loadedProduct);
                }
            }

            System.out.println("Products loaded successfully from the file.");
        }
    } catch (IOException e) {
        System.out.println("An error occurred while loading the products from the file: " + e.getMessage());
    } catch (ClassNotFoundException e) {
        System.out.println("The class for the objects stored in the file could not be found: " + e.getMessage());
    }
}

public static Product getProduct(String productId) {
    Product product = null;
    for (Product p : products) {
        if (p.getProductID().equals(productId)) {
            product = p;
            break;
        }
    }
    return product;
}


    
}

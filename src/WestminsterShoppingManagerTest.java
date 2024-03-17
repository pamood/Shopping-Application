//  Author: Pamood Jayaratne
//  IIT ID : 20220163
//  Description: 5COSC019C Object Oriented Programming – Coursework (2023/24)
  import org.junit.jupiter.api.Test;

  import java.io.ByteArrayOutputStream;
  import java.io.File;
  import java.io.PrintStream;
  import java.nio.file.Files;
  import java.nio.file.Paths;

  import static org.junit.Assert.*;

  public class WestminsterShoppingManagerTest {

      // Create an instance of WestminsterShoppingManager
      private WestminsterShoppingManager westminsterShoppingManager = new WestminsterShoppingManager();

      @Test
      void testAddProduct() {
          // Create a new Electronics product
          Electronics e1 = new Electronics("E1", "E1", 1, 1, "test", 1);

          // Add the product to the shopping manager
          westminsterShoppingManager.addProduct(e1);

          // Assert that the product was added successfully
          assertTrue(westminsterShoppingManager.getProducts().contains(e1));
      }

      @Test
      void removeProductTest() {
          // Remove a product from the shopping manager
          westminsterShoppingManager.removeProduct("E1");

          // Assert that the product was removed successfully
          assertFalse(westminsterShoppingManager.getProducts().contains("E1"));
      }

      @Test
      void openGUITest() {
          // Open the GUI
          westminsterShoppingManager.openGUI();
      }

      @Test
      void saveProductsToFileTest() {
          // Create a new Electronics product
          Electronics e1 = new Electronics("E1", "E1", 1, 1.0, "test", 1);

          // Add the product to the shopping manager
          westminsterShoppingManager.addProduct(e1);

          // Save the products to a file
          westminsterShoppingManager.saveProductsToFile();

          // Assert that the file was created successfully
          assertTrue(Files.exists(Paths.get("products.dat")));
      }

      @Test
      void printProductListTest() {
          // Create a new Electronics product
          Electronics e1 = new Electronics("E1", "E1", 1, 1, "test", 1);

          // Add the product to the shopping manager
          westminsterShoppingManager.addProduct(e1);

          // Redirect System.out to a ByteArrayOutputStream
          ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
          System.setOut(new PrintStream(outputStream));

          // Print the product list
          westminsterShoppingManager.printProductList();

          // Define the expected output
          String expectedOutput = "\n" +
                  "Product List:\r\n" +
                  "Electronics: product ID='E1', product Name='E1', available Quantity=1, price=1.0, brand='test', warranty Period=1\r\n\r\n";

          // Assert that the output matches the expected output
          assertEquals(expectedOutput, outputStream.toString());

          // Reset System.out to its original stream
          System.setOut(System.out);
      }

      @Test
      void loadProductsFromFileTest() {
          // Create a new Electronics product
          Electronics e1 = new Electronics("E1", "E1", 1, 1.0, "test", 1);

          // Add the product to the shopping manager
          westminsterShoppingManager.addProduct(e1);

          // Save the products to a file
          westminsterShoppingManager.saveProductsToFile();

          // Load the products from the file
          westminsterShoppingManager.loadProductsFromFile();

          // Assert that the products were loaded successfully
          assertNotNull(westminsterShoppingManager.getProducts().get(0));
          assertEquals("E1", westminsterShoppingManager.getProducts().get(0).getProductID());
      }
  }
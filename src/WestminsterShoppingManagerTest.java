import com.sun.deploy.net.MessageHeader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class WestminsterShoppingManagerTest {

    WestminsterShoppingManager westminsterShoppingManager = new WestminsterShoppingManager();


    @Test
    void testAddProduct() {
        Electronics e1 = new Electronics("E1", "E1", 1, 1, "test", 1);


        westminsterShoppingManager.addProduct(e1);
        assert (westminsterShoppingManager.getProducts().contains(e1));
    }

    @Test
    void removeProductTest() {

        westminsterShoppingManager.removeProduct("E1");
        assert (!westminsterShoppingManager.getProducts().contains("E1"));
    }

    @Test
    void openGUITest() {
        westminsterShoppingManager.openGUI();

    }

    @Test
    void saveProductsToFileTest() {
        // Add some products for testing
        Electronics e1 = new Electronics("E1", "E1", 1, 1.0, "test", 1);
        westminsterShoppingManager.addProduct(e1);

        // Call the saveProductsToFile method
        westminsterShoppingManager.saveProductsToFile();

        // Verify that the file is created
        assert(Files.exists(Paths.get("products.dat")));

    }

    @Test
    void printProductListTest() {

        Electronics e1 = new Electronics("E1", "E1", 1, 1, "test", 1);
        // Call the printProductList method
        westminsterShoppingManager.addProduct(e1);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        westminsterShoppingManager.printProductList();


        // Verify the printed content
        String expectedOutput = "\n" +
                "Product List:\r\n" +
                "Electronics: product ID='E1', product Name='E1', available Quantity=1, price=1.0, brand='test', warranty Period=1\r\n\r\n";

       assertEquals(expectedOutput, outputStream.toString());
       System.setOut(System.out);
    }

    @Test
    void loadProductsFromFileTest() {
        // Add some products for testing
        Electronics e1 = new Electronics("E1", "E1", 1, 1.0, "test", 1);
        westminsterShoppingManager.addProduct(e1);

        // Call the saveProductsToFile method
        westminsterShoppingManager.saveProductsToFile();

        // Call the loadProductsFromFile method
        westminsterShoppingManager.loadProductsFromFile();

        //Verify that the products are loaded
        assertNotNull(westminsterShoppingManager.getProducts().get(0));
        assertEquals("E1", westminsterShoppingManager.getProducts().get(0).getProductID());

    }

}


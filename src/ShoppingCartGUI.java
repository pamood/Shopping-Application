import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCartGUI extends JFrame {
    private JTable cartTable;
    private JButton checkOutButton;
    private JLabel totalLabel, firstPDiscountLabel, threeItemsDiscountLabel, finalTotalLabel;
    private JPanel finalDetails;

    private String[] columnNames = {"Product", "Quantity", "Price (€)"};
    static ShoppingCart shoppingCart = new ShoppingCart();

    public ShoppingCartGUI() {
        setTitle("Shopping Cart GUI");
        setLayout(new GridBagLayout());
        initializeComponents();
        layoutComponents();
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeComponents() {
        cartTable = new JTable(new Object[][]{}, columnNames);
        checkOutButton = new JButton("Checkout");

        totalLabel = new JLabel("Total -: ");
        firstPDiscountLabel = new JLabel("First Purchase Discount -: ");
        threeItemsDiscountLabel = new JLabel("Three Items Discount -: ");
        finalTotalLabel = new JLabel("Final total -: ");

        finalDetails = new JPanel();
        finalDetails.setLayout(new FlowLayout());
        finalDetails.add(totalLabel);
        finalDetails.add(firstPDiscountLabel);
        finalDetails.add(threeItemsDiscountLabel);
        finalDetails.add(finalTotalLabel);
        finalDetails.add(checkOutButton);
    }

    private void layoutComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.gridwidth = 0;
        constraints.gridy = 0;
        constraints.gridx = 0;

        JScrollPane scrollPane = new JScrollPane(cartTable);
        add(scrollPane, constraints);

        constraints.gridy++;
        constraints.anchor = GridBagConstraints.NORTHEAST; // Align to the top right corner

        finalDetails.setLayout(new BoxLayout(finalDetails, BoxLayout.PAGE_AXIS));
        finalDetails.add(totalLabel);
        finalDetails.add(Box.createRigidArea(new Dimension(0, 10)));
        finalDetails.add(firstPDiscountLabel);
        finalDetails.add(Box.createRigidArea(new Dimension(0, 10)));
        finalDetails.add(threeItemsDiscountLabel);
        finalDetails.add(Box.createRigidArea(new Dimension(0, 10)));
        finalDetails.add(finalTotalLabel);
        finalDetails.add(Box.createRigidArea(new Dimension(0, 10)));

        // Align the checkout button to the center and make it span the entire width
        checkOutButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, checkOutButton.getPreferredSize().height));
        checkOutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        finalDetails.add(checkOutButton);
        // Align the finalDetails panel to the right
        finalDetails.setAlignmentX(Component.RIGHT_ALIGNMENT);

        add(finalDetails, constraints);

        checkOutButton.addActionListener(e -> checkout());

        updateCartTable();
        updateFinalDetails();
    }

    public static void addToCart(Product product, int quantity) {
        // Check if the product is already in the cart
        if (shoppingCart.getProducts().containsKey(product)) {
            // If the product is already in the cart, increase its quantity
            int currentQuantity = shoppingCart.getProducts().get(product);
            shoppingCart.getProducts().put(product, currentQuantity + quantity);
        } else {
            // If the product is not in the cart, add it to the cart
            shoppingCart.getProducts().put(product, quantity);
        }
    }

    private void updateCartTable() {
        // Get the products from the shopping cart
        Map<Product, Integer> products = shoppingCart.getProducts();

        // Prepare the data for the table
        Object[][] data = new Object[products.size()][3];
        int row = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            data[row][0] = product.getProductName();
            data[row][1] = quantity;
            data[row][2] = product.getPrice() * quantity; // Calculate the total price

            row++;
        }

        // Set updated data and column names
        String[] columnNames = {"Product", "Quantity", "Total Price"};
        cartTable.setModel(new DefaultTableModel(data, columnNames));
    }

    private void updateFinalDetails() {
        double totalCost = shoppingCart.calculateTotalCost();
        totalLabel.setText("Total: " + String.format("%.2f", totalCost) + " €");

        double threeItemsDiscount = 0;
        Map<String, Integer> categoryCounts = new HashMap<>();
        for (Map.Entry<Product, Integer> entry : shoppingCart.getProducts().entrySet()) {
            String productString = entry.getKey().toString();
            String category = productString.substring(0, productString.indexOf("{productID='"));
            categoryCounts.put(category, categoryCounts.getOrDefault(category, 0) + entry.getValue());
            if (categoryCounts.getOrDefault(category, 0) >= 3) {
                threeItemsDiscount = totalCost * 0.2; // 20% discount
                break;
            }
        }

        if (threeItemsDiscount > 0) {
            threeItemsDiscountLabel.setText("Three Items Discount: " + String.format("%.2f", threeItemsDiscount) + " €");
        } else {
            threeItemsDiscountLabel.setText("Three Items Discount: 0.00 €");
        }

        finalTotalLabel.setText("Final Total: " + String.format("%.2f", totalCost - threeItemsDiscount) + " €");
        
    
    }

    private void checkout() {
        // Perform the checkout logic here
        // For example, you can clear the cart, update inventory, etc.

        shoppingCart = new ShoppingCart();
        updateCartTable();
        updateFinalDetails();

        JOptionPane.showMessageDialog(null, "Checkout completed.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

}

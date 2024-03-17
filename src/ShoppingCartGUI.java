//  Author: Pamood Jayaratne
//  IIT ID : 20220163
//  Description: 5COSC019C Object Oriented Programming – Coursework (2023/24)
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
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
        constraints.anchor = GridBagConstraints.NORTHEAST; 

        finalDetails.setLayout(new BoxLayout(finalDetails, BoxLayout.PAGE_AXIS));
        finalDetails.add(totalLabel);
        finalDetails.add(Box.createRigidArea(new Dimension(0, 10)));
        finalDetails.add(firstPDiscountLabel);
        finalDetails.add(Box.createRigidArea(new Dimension(0, 10)));
        finalDetails.add(threeItemsDiscountLabel);
        finalDetails.add(Box.createRigidArea(new Dimension(0, 10)));
        finalDetails.add(finalTotalLabel);
        finalDetails.add(Box.createRigidArea(new Dimension(0, 10)));
        finalDetails.setAlignmentX(Component.LEFT_ALIGNMENT);

        add(finalDetails, constraints);
       
        constraints.gridy++;
        constraints.anchor = GridBagConstraints.CENTER;
        add(checkOutButton, constraints);
        checkOutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
     

        checkOutButton.addActionListener(e -> checkout());
        checkOutButton.setBackground(Color.RED);
        checkOutButton.setForeground(Color.WHITE);

        updateCartTable();
        updateFinalDetails();
    }

    public static void addToCart(Product product, int quantity) {
        if (shoppingCart.getProducts().containsKey(product)) {
            int currentQuantity = shoppingCart.getProducts().get(product);
            shoppingCart.getProducts().put(product, currentQuantity + quantity);
        } else {
            shoppingCart.getProducts().put(product, quantity);
        }
    }

    private void updateCartTable() {
        Map<Product, Integer> products = shoppingCart.getProducts();
        Object[][] data = new Object[products.size()][3];
        int row = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            data[row][0] = product.getProductName();
            data[row][1] = quantity;
            data[row][2] = product.getPrice() * quantity; 

            row++;
        }

    
        String[] columnNames = {"Product", "Quantity", "Total Price"};
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
        @Override
        public boolean isCellEditable(int row, int column)//set table cells as non editable
         {

        return false;
    }
};
cartTable.setModel(model);
    }
    // Update the final details labels
    private void updateFinalDetails() {
        double totalCost = shoppingCart.calculateTotalCost();
        totalLabel.setText("Total: " + String.format("%.2f", totalCost) + " €");
        
        double firstPurchaseDiscount = shoppingCart.calculateFirstPurchaseDiscount();
        firstPDiscountLabel.setText("First Purchase Discount: " + String.format("%.2f", firstPurchaseDiscount) + " €");

         double threeItemsDiscount = shoppingCart.calculateThreeItemsDiscount();
         threeItemsDiscountLabel.setText("Three Items Discount: " + String.format("%.2f", threeItemsDiscount) + " €");

        double finalTotal = totalCost - (firstPurchaseDiscount+threeItemsDiscount);
        finalTotalLabel.setText("Final Total: " + String.format("%.2f", finalTotal) + " €");
    }

    // Checkout the cart
    private void checkout() {
        shoppingCart = new ShoppingCart();
        User.setFirstPurchase(false);

        User.saveUsersToFile();
        updateCartTable();
        updateFinalDetails();
        JOptionPane.showMessageDialog(null, "Checkout completed.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

}

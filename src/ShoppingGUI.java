//  Author: Pamood Jayaratne
//  IIT ID : 20220163
//  Description: 5COSC019C Object Oriented Programming – Coursework (2023/24)
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ShoppingGUI extends JFrame {
    // Declare all the components and variables
    private JLabel topicLabel, idLabel, catagoryLabel, nameLabel, specialLabel1, specialLabel2, quantityLabel, priceLabel;
    private JButton viewCartButton, sortButton, addToCartButton;
    private JComboBox<String> comboBox;
    private JTable table;
    private JScrollPane scrollPane;
    private JPanel detailPanel;
    private ArrayList<Product> products;

    private String[] columnNames = {"Product ID", "Product Name", "Category", "Price (€)", "Info"};

    public ShoppingGUI() {
        // Set up the frame
        setTitle("Shopping GUI");
        setLayout(new GridBagLayout());
        initializeComponents();
        layoutComponents();
        setResizable(false);
        pack();
        setLocationRelativeTo(null); // Center the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeComponents() {
        // Initialize all the components
        topicLabel = new JLabel("Welcome to the Westminster Shopping Plaza!");
        topicLabel.setFont(new Font(topicLabel.getFont().getName(), Font.BOLD, 20));
        topicLabel.setHorizontalAlignment(JLabel.CENTER);
        topicLabel.setForeground(Color.ORANGE);
        viewCartButton = new JButton("View Cart");
        viewCartButton.setBackground(Color.LIGHT_GRAY);
        viewCartButton.setForeground(Color.BLACK);
        sortButton = new JButton("Sort");
        sortButton.setBackground(Color.LIGHT_GRAY);
        sortButton.setForeground(Color.BLACK);
        String[] items = {"All", "Electronics", "Clothing"};
        comboBox = new JComboBox<>(items);
        table = new JTable();


        addToCartButton = new JButton("Add to Cart");
        addToCartButton.setBackground(Color.ORANGE);
        addToCartButton.setForeground(Color.BLACK);
        idLabel = new JLabel();
        catagoryLabel = new JLabel();
        nameLabel = new JLabel();
        specialLabel1 = new JLabel();
        specialLabel2 = new JLabel();
        quantityLabel = new JLabel();
        priceLabel = new JLabel();
        updateTable("All");

        // Set up cell renderer to change color based on quantity
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String productId = (String) table.getValueAt(row, 0);
                Product product = WestminsterShoppingManager.getProduct(productId);
                if (product != null && product.getAvailableQuantity() <= 3) {
                    component.setForeground(Color.RED);
                } else {
                    component.setForeground(Color.BLACK);
                }
                return component;
            }
        });
    }

    private void layoutComponents() {
        // Add all the components to the frame
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.weightx = 1;
        constraints.gridwidth = 0;
        constraints.gridy = 0;
        constraints.gridx = 0;
        add(topicLabel, constraints);

        constraints.gridy++;
        constraints.gridwidth = 1;
        constraints.gridx = 1;
        add(viewCartButton, constraints);
         // Add action listeners to the buttons viewcart
        viewCartButton.addActionListener(new ActionListener() {
            // Add action listeners to the button viewcart
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    ShoppingCartGUI shoppingCartGUI = new ShoppingCartGUI();
                    shoppingCartGUI.setVisible(true);
                    shoppingCartGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        constraints.gridy++;
        add(sortButton, constraints);

        detailPanel = new JPanel();
        detailPanel.setLayout(new GridBagLayout());
        GridBagConstraints innerConstraint = new GridBagConstraints();
        innerConstraint.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);
        innerConstraint.weightx = 1;
        innerConstraint.insets = new Insets(5, 0, 5, 10);
        innerConstraint.gridy = 0;
        innerConstraint.gridx = 0;

        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                products = getProductsByCategory((String) comboBox.getSelectedItem());
                Collections.sort(products, Comparator.comparing(Product::getProductID));
                Object[][] data = getTableData(products);
                DefaultTableModel model = new DefaultTableModel(data, columnNames) {
                    @Override
                    // This causes all cells to be non-editable
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                table.setModel(model);
            }
        });
        constraints.gridy++;
        add(comboBox, constraints);

        comboBox.addActionListener(new ActionListener() {
            // Add action listeners to the combobox
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCategory = (String) comboBox.getSelectedItem();
                updateTable(selectedCategory);

            }
        });

        constraints.gridy++;
        scrollPane = new JScrollPane(table);
        add(scrollPane, constraints);

        table.addMouseListener(new MouseAdapter() {
            // Add a mouse listener to the table to display product details when a row is clicked
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int modelRow = table.convertRowIndexToModel(row);
                Product selectedProduct = products.get(modelRow);

                idLabel.setText("Product ID: " + selectedProduct.getProductID());
                nameLabel.setText("Name: " + selectedProduct.getProductName());
                catagoryLabel.setText("Category: " + selectedProduct.getClass().getName());
                priceLabel.setText("Price: " + selectedProduct.getPrice());
                if (selectedProduct instanceof Electronics) {
                    specialLabel1.setText("Brand: " + ((Electronics) selectedProduct).getBrand());
                    specialLabel2.setText("Warranty Period: " + ((Electronics) selectedProduct).getWarrantyPeriod());
                } else if (selectedProduct instanceof Clothing) {
                    specialLabel1.setText("Size: " + ((Clothing) selectedProduct).getSize());
                    specialLabel2.setText("Colour: " + ((Clothing) selectedProduct).getColor());
                }
                quantityLabel.setText("Quantity Available: " + selectedProduct.getAvailableQuantity());

                detailPanel.setVisible(true);
                pack();
            }
        });

        innerConstraint.gridy++;
        detailPanel.add(catagoryLabel, innerConstraint);
        innerConstraint.gridy++;
        detailPanel.add(nameLabel, innerConstraint);
        innerConstraint.gridy++;
        detailPanel.add(specialLabel1, innerConstraint);
        innerConstraint.gridy++;
        detailPanel.add(specialLabel2, innerConstraint);
        innerConstraint.gridy++;
        detailPanel.add(quantityLabel, innerConstraint);
        innerConstraint.gridy++;
        detailPanel.add(priceLabel, innerConstraint);
        innerConstraint.gridy++;

        detailPanel.setVisible(false);

        constraints.gridy++;
        add(detailPanel, constraints);

        constraints.gridy++;
        add(addToCartButton, constraints);
         
        addToCartButton.addActionListener(new ActionListener() {
            // Add action listeners to the button addtocart
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    int modelRow = table.convertRowIndexToModel(row);
                    Product selectedProduct = products.get(modelRow);
                    if (selectedProduct.getAvailableQuantity() > 0) {
                
                        ShoppingCartGUI.addToCart(selectedProduct, 1);

                        // Update the quantity in the table
                        int currentQuantity = selectedProduct.getAvailableQuantity();
                        selectedProduct.setAvailableQuantity(currentQuantity - 1);
                        updateTable((String) comboBox.getSelectedItem());
                        quantityLabel.setText("Quantity Available: " + selectedProduct.getAvailableQuantity());

                        // If all quantities are added to the cart, remove the product from the table
                        if (selectedProduct.getAvailableQuantity() == 0) {
                            products.remove(selectedProduct);
                            ((DefaultTableModel) table.getModel()).removeRow(row);
                        }

                        // Show a confirmation message
                        JOptionPane.showMessageDialog(null, "Product added to cart.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        // print shopping cart hashmap
                        System.out.println(ShoppingCartGUI.shoppingCart.getProducts());

                        // Clear the selection
                        table.clearSelection();
                    } else {
                        JOptionPane.showMessageDialog(null, "Product out of stock.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a product to add to the cart.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // Update the table with the products of the selected category
    private void updateTable(String selectedCategory) {
        products = getProductsByCategory(selectedCategory);
        Object[][] data = getTableData(products);
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table.setModel(model);
    }

    // Get the products of the selected category
    private ArrayList<Product> getProductsByCategory(String selectedCategory) {
        ArrayList<Product> filteredProducts = new ArrayList<>();
        for (Product product : WestminsterShoppingManager.getProducts()) {
            if (selectedCategory.equals("All") ||
                    (selectedCategory.equals("Electronics") && product instanceof Electronics) ||
                    (selectedCategory.equals("Clothing") && product instanceof Clothing)) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }
    // Get the data for the table
    private Object[][] getTableData(ArrayList<Product> products) {
        Object[][] data = new Object[products.size()][5];
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            data[i][0] = product.getProductID();
            data[i][1] = product.getProductName();
            data[i][2] = product.getClass().getName();
            data[i][3] = product.getPrice();
            data[i][4] = product.getProductDetails();
        }
        return data;
    }

   
}

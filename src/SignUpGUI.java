//  Author: Pamood Jayaratne
//  IIT ID : 20220163
//  Description: 5COSC019C Object Oriented Programming – Coursework (2023/24)
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpGUI extends JFrame {
    // Declare all the components and variables
    private JTextField firstNameField,lastNameField,emailField,userIDField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton signInButton;
    private JButton backButton;
  
    public SignUpGUI() {
        // Set up the frame
        setTitle("Signup GUI");
        setLayout(new GridBagLayout());
        initializeLoginComponents();
        setResizable(false);
        layoutComponents();
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    private void initializeLoginComponents() {
        // Initialize all the components
        firstNameField = new JTextField(20);
        lastNameField = new JTextField(20);
        emailField = new JTextField(20);
        userIDField = new JTextField(20);
        passwordField = new JPasswordField(20);
        confirmPasswordField = new JPasswordField(20);
        signInButton = new JButton("Sign In");
        signInButton.setBackground(Color.ORANGE);
        signInButton.setForeground(Color.BLACK);
        backButton = new JButton("Back"); 
        backButton.setBackground(Color.GRAY);
        backButton.setForeground(Color.WHITE);
    }

    private void layoutComponents() {
        // Add all the components to the frame
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.weightx = 1;

        constraints.gridx = 0;
        constraints.gridy = 0;
        add(new JLabel("First Name:"), constraints);

        constraints.gridx = 1;
        add(firstNameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(new JLabel("Last Name:"), constraints);

        constraints.gridx = 1;
        add(lastNameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        add(new JLabel("Email:"), constraints);

        constraints.gridx = 1;
        add(emailField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        add(new JLabel("User ID:"), constraints);

        constraints.gridx = 1;
        add(userIDField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        add(new JLabel("Password:"), constraints);

        constraints.gridx = 1;
        add(passwordField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        add(new JLabel("Confirm Password:"), constraints);

        constraints.gridx = 1;
        add(confirmPasswordField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        add(backButton, constraints);
        backButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUpGUI.this.setVisible(false);
                new LoginGUI().setVisible(true); 
            }
        });

        constraints.gridx = 1;
        add(signInButton, constraints);

        signInButton.addActionListener(new ActionListener() {
            // Add an action listener to the login button
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String email = emailField.getText();
                String userID = userIDField.getText();
                String password = new String(passwordField.getPassword());
               String confirmPassword = new String(confirmPasswordField.getPassword());
                 // Validate user input
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || userID.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (!email.matches(emailRegex)) {
            JOptionPane.showMessageDialog(null, "Invalid email format.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // You can add more specific validation checks as needed

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(null, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }


        User newUser = new User(userID, password);
        User.getUsers().add(newUser);
        User.saveUsersToFile();
        JOptionPane.showMessageDialog(null, "Registration successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
        SignUpGUI.this.setVisible(false);
        LoginGUI loginGUI = new LoginGUI();
        loginGUI.setVisible(true);

                }
            });    
        }  
     }
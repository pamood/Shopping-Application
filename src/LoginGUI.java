//  Author: Pamood Jayaratne
//  IIT ID : 20220163
//  Description: 5COSC019C Object Oriented Programming – Coursework (2023/24)
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {
    // Declare all the components and variables    
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton,signUpButton;


    public LoginGUI() {
        // Set up the frame
        setTitle("Login GUI");
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
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");
        signUpButton = new JButton("Sign Up");
        
        loginButton = new JButton("Login");
        loginButton.setBackground(Color.ORANGE);
        loginButton.setForeground(Color.BLACK);
        
        signUpButton = new JButton("Sign Up");
        signUpButton.setBackground(Color.LIGHT_GRAY);
        signUpButton.setForeground(Color.BLACK);
    }

    private void layoutComponents() {
        // Add all the components to the frame
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.weightx = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(new JLabel("Username:"), constraints);

        constraints.gridx = 1;
        add(usernameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(new JLabel("Password:"), constraints);

        constraints.gridx = 1;
        add(passwordField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        add(loginButton, constraints);
        loginButton.addActionListener(new ActionListener() {
            // Add an action listener to the login button
            @Override
            public void actionPerformed(ActionEvent e) {
 
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (User.authenticateUser(username, password)) {
                    new ShoppingGUI();
                    // Close the login window
                       dispose();

                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        constraints.gridx = 0;
        add(signUpButton, constraints);

        signUpButton.addActionListener(new ActionListener() {
            // Add an action listener to the sign up button
            @Override
            public void actionPerformed(ActionEvent e) {

                new SignUpGUI();
            }
        });
    }
}

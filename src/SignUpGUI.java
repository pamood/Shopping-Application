
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.FileOutputStream;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class SignUpGUI extends JFrame {
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField userIDField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton signInButton;
    private JButton backButton;

    public SignUpGUI() {
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
        firstNameField = new JTextField(20);
        lastNameField = new JTextField(20);
        emailField = new JTextField(20);
        userIDField = new JTextField(20);
        passwordField = new JPasswordField(20);
        confirmPasswordField = new JPasswordField(20);
        signInButton = new JButton("Sign In");
        backButton = new JButton("Back");
    }

    private void layoutComponents() {
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
                SignUpGUI.this.setVisible(false); // make the sign up page not visible
                new LoginGUI().setVisible(true); // open the login page
            }
        });


        constraints.gridx = 1;
        add(signInButton, constraints);

        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String email = emailField.getText();
                String userID = userIDField.getText();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());

                if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || userID.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(null, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    User newUser = new User(userID, password, firstName, lastName, email);

                    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("users.txt", true))) {
                        oos.writeObject(newUser);
                        oos.writeObject(null); // Add null as a marker for the end of the object stream
                        JOptionPane.showMessageDialog(null, "Registration successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        SignUpGUI.this.setVisible(false);
                        new LoginGUI().setVisible(true);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
}
            }
        });
    }
}

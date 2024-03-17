//  Author: Pamood Jayaratne
//  IIT ID : 20220163
//  Description: 5COSC019C Object Oriented Programming – Coursework (2023/24)
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userID;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private static boolean firstPurchase;
    private static List<User> users = new ArrayList<>();
    
    public static List<User> getUsers() {
        return users;
    }

    public static boolean isFirstPurchase() {
        return firstPurchase;
    }

    public static void setFirstPurchase(boolean firstPurchase) {
        User.firstPurchase = firstPurchase;
    }


    public User(String userID, String password) {
        setUserID(userID);
        setPassword(password);
        firstPurchase = true;
        
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        if (userID == null || userID.trim().isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be null or empty");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be null or empty");
        }
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (!email.matches(emailRegex)) {
            throw new IllegalArgumentException("Invalid email format");
        }

        this.email = email;
    }

    static void saveUsersToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("users.txt"))) {
            oos.writeObject(users);
            System.out.println("Users saved successfully to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the users to the file: " + e.getMessage());
        }
    }

static void loadUsersFromFile() {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("users.txt"))) {
        Object loadedObject = ois.readObject();

        if (loadedObject instanceof List<?>) {
            List<?> loadedProducts = (List<?>) loadedObject;
// Clear the existing products list before adding loaded products
            users.clear();
            // Add each product from the loaded list to the products list
            for (Object use : loadedProducts) {
                if (use instanceof User) {
                    users.add((User) use);
                }
            }

            System.out.println("Users loaded successfully from the file.");
        }
    } catch (IOException e) {
        System.out.println("An error occurred while loading the User from the file: " + e.getMessage());
    } catch (ClassNotFoundException e) {
        System.out.println("The class for the user stored in the file could not be found: " + e.getMessage());
    }
}
    
    public static boolean authenticateUser(String userID, String password) {
        // Load the list of users from a file
        loadUsersFromFile();
        // Check if the list is null
        if (users == null) {
            return false;
        }
        // Iterate over each user in the list
        for (User user : users) {
            // Check if the user is null
            if (user == null) {
                continue;
            }
            // Check if the user ID and password match
            if (userID.equals(user.getUserID()) && password.equals(user.getPassword())) {

                return true;
            }
        }


        return false;
    }
  
}

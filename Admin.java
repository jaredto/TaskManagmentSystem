/*
 * Admin is a special type of User with extra priveleges.
 * Admins can view all users and add/remove them. 
 */


import java.util.HashMap;

public class Admin extends User {

    public Admin(String username, String password) {
        super(username, password);
    }

    public void viewAllUsers(HashMap<String, User> users) {
        System.out.println("Registered users:");
        for (String name : users.keySet()) {
            System.out.println("- " + name);
        }
    }

    public void addUser(HashMap<String, User> users, String username, String password) {
        if (users.containsKey(username)) {
            System.out.println("User already exists.");
        } else {
            users.put(username, new User(username, password));
            System.out.println("User added.");
        }
    }

    public void removeUser(HashMap<String, User> users, String username) {
        if (users.containsKey(username)) {
            users.remove(username);
            System.out.println("User removed.");
        } else {
            System.out.println("User not found.");
        }
    }
}

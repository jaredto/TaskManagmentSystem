import java.util.HashMap;

/**
 * The login system keeps track of all users and allows for 
 * loggin in and registering new users.
 */


public class LoginSystem {
    private HashMap<String, User> users = new HashMap<>();
    private final String adminUsername = "admin";
    private final String adminPassword = "password123";

    public LoginSystem() {
        // Register the admin
        users.put(adminUsername, new Admin(adminUsername, adminPassword));
    }

    //If username matches password return user, else null.
    public User login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.checkPassword(password)) {
            return user;
        }
        return null;
    }

    public boolean register(String username, String password) {
        if (users.containsKey(username)) {
            return false;
        }
        users.put(username, new User(username, password));
        return true;
    }

    public HashMap<String, User> getUsers() {
        return users;
    }

}

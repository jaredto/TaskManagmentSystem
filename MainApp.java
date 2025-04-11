import java.util.Scanner;

/*
 * Main app loop
 */

public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LoginSystem loginSystem = new LoginSystem();
        User currentUser = null;
        boolean running = true;

        System.out.println("Welcome to the Task Manager!");

        while (running){

            // ==== User Registration/Login Menu ====
            while (currentUser == null) {
                System.out.println("\n1. Login");
                System.out.println("2. Register");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");
                String option = scanner.nextLine();

                if (option.equals("1")) {
                    System.out.print("Username: ");
                    String username = scanner.nextLine();
                    System.out.print("Password: ");
                    String password = scanner.nextLine();

                    currentUser = loginSystem.login(username, password);
                    if (currentUser == null) {
                        System.out.println("Invalid credentials. Try again.");
                    }
                    else{
                        System.out.println("\nLogin successful as " + currentUser.getUsername());
                    }

                } else if (option.equals("2")) {
                    System.out.print("Choose a username: ");
                    String username = scanner.nextLine();
                    System.out.print("Choose a password: ");
                    String password = scanner.nextLine();

                    boolean success = loginSystem.register(username, password);
                    if (success) {
                        System.out.println("Registration successful. You can now log in.");
                    } else {
                        System.out.println("Username already taken. Try another.");
                    }
                } else if (option.equals("3")) {
                    running = false;
                    break;
                } else {
                    System.out.println("Invalid option.");
                }
            }

            if (running == false){
                break;
            }

            // ===== Main Application Menu ====
            System.out.println("\n--- MENU ---");
        
            // All user options
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Complete Task");
            System.out.println("4. Delete Task");

            System.out.println( "5. Logout");
        
            // Admin-only options
            if (currentUser instanceof Admin) {
                System.out.println("6. View All Users");
                System.out.println("7. Add User");
                System.out.println("8. Remove User");
            }
        
            System.out.print("Choose: ");
            int choice = Integer.parseInt(scanner.nextLine());
        
            //Handle all user choices
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter task description: ");
                    currentUser.addTask(scanner.nextLine());
                }
                case 2 -> currentUser.viewTasks();
                case 3 -> {
                    System.out.print("Task number to complete: ");
                    currentUser.markTaskCompleted(Integer.parseInt(scanner.nextLine()) - 1);
                }
                case 4 -> {
                    System.out.print("Task number to delete: ");
                    currentUser.deleteTask(Integer.parseInt(scanner.nextLine()) - 1);
                }
                case 5 -> {
                    System.out.println("Logging out...");
                    currentUser = null;
                }

                //Handle additional admin choices
                case 6 -> {
                    if (currentUser instanceof Admin admin) {
                        admin.viewAllUsers(loginSystem.getUsers());
                    } else {
                        System.out.println("Logging out...");
                        running = false;
                    }
                }
                case 7 -> {
                    if (currentUser instanceof Admin admin) {
                        System.out.print("New username: ");
                        String newUser = scanner.nextLine();
                        System.out.print("New password: ");
                        String newPass = scanner.nextLine();
                        admin.addUser(loginSystem.getUsers(), newUser, newPass);
                    } else {
                        System.out.println("Invalid choice.");
                    }
                }
                case 8 -> {
                    if (currentUser instanceof Admin admin) {
                        System.out.print("Username to remove: ");
                        String removeUser = scanner.nextLine();
                        admin.removeUser(loginSystem.getUsers(), removeUser);
                    } else {
                        System.out.println("Invalid choice.");
                    }
                }
                
                default -> System.out.println("Invalid choice.");
            }
        }
        
        scanner.close();
    }
}

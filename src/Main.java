import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nWelcome to the School Management System!");
            System.out.println("Please select your role to login:");

            String[][] users = {
                    {"admin", "admin123"},
                    {"principal", "principal123"},
                    {"teacher", "teacher123"},
                    {"accountant", "accountant123"},
                    {"examination", "exam123"},
                    {"coordinator", "coordinator123"}
            };

            System.out.println("1. Admin");
            System.out.println("2. Principal");
            System.out.println("3. Teacher");
            System.out.println("4. Accountant");
            System.out.println("5. Examination Department");
            System.out.println("6. Coordinator");
            System.out.println("7. Exit");

            System.out.print("Enter the number corresponding to your role: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 7) {
                System.out.println("Exiting the application. Goodbye!");
                break;
            }

            if (choice < 1 || choice > 6) {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }

            // Generate a welcome message based on the selected role
            String role = getRoleName(choice);
            System.out.println("\nWelcome, " + role + "! Please enter your credentials.");

            String username = users[choice - 1][0];
            String password = users[choice - 1][1];

            boolean loggedIn = false;

            while (!loggedIn) {
                System.out.print("Enter your username: ");
                String inputUsername = scanner.nextLine();

                System.out.print("Enter your password: ");
                String inputPassword = scanner.nextLine();

                if (inputUsername.equals(username) && inputPassword.equals(password)) {
                    System.out.println("\nLogin successful! Welcome " + role + ".");
                    loggedIn = true;

                    // Proceed to the respective menu
                    boolean isLoggedIn = true;
                    while (isLoggedIn) {
                        switch (choice) {
                            case 1:
                                isLoggedIn = adminMenu(scanner);
                                break;
                            case 2:
                                isLoggedIn = principalMenu(scanner);
                                break;
                            case 3:
                                isLoggedIn = teacherMenu(scanner);
                                break;
                            case 4:
                                isLoggedIn = accountantMenu(scanner);
                                break;
                            case 5:
                                isLoggedIn = examinationMenu(scanner);
                                break;
                            case 6:
                                isLoggedIn = coordinatorMenu(scanner);
                                break;
                        }
                    }
                } else {
                    System.out.println("Invalid credentials. Please try again.");
                    System.out.print("Would you like to try again or go back to the main menu? (try again/back): ");
                    String action = scanner.nextLine();
                    if (action.equalsIgnoreCase("back")) {
                        break; // Back to the role selection menu
                    }
                }
            }
        }

        scanner.close();
    }

    private static String getRoleName(int choice) {
        switch (choice) {
            case 1: return "Admin";
            case 2: return "Principal";
            case 3: return "Teacher";
            case 4: return "Accountant";
            case 5: return "Examination Department";
            case 6: return "Coordinator";
            default: return "Unknown";
        }
    }

    private static boolean adminMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Manage Teachers");
            System.out.println("2. Manage Students");
            System.out.println("3. Manage Staff");
            System.out.println("4. Back to Login");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Managing Teachers...");
                    break;
                case 2:
                    System.out.println("Managing Students...");
                    break;
                case 3:
                    System.out.println("Managing Staff...");
                    break;
                case 4:
                    return false; // Back to login menu
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static boolean principalMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nPrincipal Menu:");
            System.out.println("1. View Marks");
            System.out.println("2. View Teacher Assignments (Class & Subject)");
            System.out.println("3. Back to Login");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Viewing Marks...");
                    break;
                case 2:
                    System.out.println("Viewing Teacher Assignments...");
                    break;
                case 3:
                    return false;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static boolean teacherMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nTeacher Menu:");
            System.out.println("1. View Students in Class");
            System.out.println("2. View Courses");
            System.out.println("3. View Scheduling List");
            System.out.println("4. Back to Login");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Viewing Students in Class...");
                    break;
                case 2:
                    System.out.println("Viewing Courses...");
                    break;
                case 3:
                    System.out.println("Viewing Scheduling List...");
                    break;
                case 4:
                    return false;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static boolean accountantMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nAccountant Menu:");
            System.out.println("1. Manage Student Fees");
            System.out.println("2. Manage Teacher Salaries");
            System.out.println("3. Generate Reports");
            System.out.println("4. Back to Login");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Managing Student Fees...");
                    break;
                case 2:
                    System.out.println("Managing Teacher Salaries...");
                    break;
                case 3:
                    System.out.println("Generating Reports...");
                    break;
                case 4:
                    return false;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static boolean examinationMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nExamination Department Menu:");
            System.out.println("1. Upload Marks");
            System.out.println("2. View Marks");
            System.out.println("3. Edit Marks");
            System.out.println("4. Back to Login");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Uploading Marks...");
                    break;
                case 2:
                    System.out.println("Viewing Marks...");
                    break;
                case 3:
                    System.out.println("Editing Marks...");
                    break;
                case 4:
                    return false;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static boolean coordinatorMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nCoordinator Menu:");
            System.out.println("1. Assign Course to Teacher");
            System.out.println("2. Unassign Course from Teacher");
            System.out.println("3. View Teachers Teaching Classes");
            System.out.println("4. View Teachers for a Specific Class");
            System.out.println("5. Back to Login");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Assigning Course to Teacher...");
                    break;
                case 2:
                    System.out.println("Unassigning Course from Teacher...");
                    break;
                case 3:
                    System.out.println("Viewing Teachers Teaching Classes...");
                    break;
                case 4:
                    System.out.println("Viewing Teachers for a Specific Class...");
                    break;
                case 5:
                    return false; // Back to login menu
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}

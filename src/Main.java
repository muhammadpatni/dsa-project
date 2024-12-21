import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public  class Main {

    static StaffList staff =new StaffList();
    public static void main(String[] args) {
        login();

}
public static void login()
{
    Scanner scanner = new Scanner(System.in);

    while (true) {
        System.out.println("\nWelcome to the School Management System!");
        System.out.println("Please select your role to login:");

        String[][] users = {
                {"admin", "admin123"},
                {"principal", "principal123"},
                {"teacher", "teacher123"},
                {"accountant", "accountant123"},
                {"examination", "exam123"}
        };

        System.out.println("1. Admin");
        System.out.println("2. Principal");
        System.out.println("3. Teacher");
        System.out.println("4. Accountant");
        System.out.println("5. Examination Department");
        System.out.println("6. Exit");

        System.out.print("Enter the number corresponding to your role: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice == 6) {
            System.out.println("Exiting the application. Goodbye!");
            break;
        }

        if (choice < 1 || choice > 5) {
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

                // Now, proceed to the respective menu
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
                    manageEntity(scanner, "Teacher");
                    break;
                case 2:
                    manageEntity(scanner, "Student");
                    break;
                case 3:
                    manageEntity(scanner, "Staff");
                    break;
                case 4:
                    return false;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void manageEntity(Scanner scanner, String entity) {
        while (true) {
            System.out.println("\n" + entity + " Management:");
            System.out.println("1. Add " + entity);
            System.out.println("2. Delete " + entity);
            System.out.println("3. Update " + entity);
            System.out.println("4. Search " + entity);
            System.out.println("5. Back");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Adding a " + entity + "...");
                    if (entity.equals("Staff"))
                    {
                        String dob = "";
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                        System.out.print("Enter Staff Name: ");
                        String name = scanner.nextLine();
                        System.out.println("Select Gender: ");
                        System.out.println("1. Male");
                        System.out.println("2. Female");
                        String gender = "";
                        int GenderChoice= scanner.nextInt();
                        if (GenderChoice==1)
                        {
                            gender="Male";
                        }
                        else if(GenderChoice==2)
                        {
                             gender="Female";
                        }
                        while (true) {
                            scanner.nextLine();
                            System.out.print("Enter your Date of Birth (dd-MM-yyyy): ");
                            String input = scanner.nextLine();

                            try {
                                // Validate the input by parsing it into a LocalDate
                                LocalDate birthDate = LocalDate.parse(input, formatter);

                                // If parsing succeeds, store it in the dob variable
                                dob = input;
                                break; // Exit the loop
                            } catch (DateTimeParseException e) {
                                System.out.println("Invalid date format. Please enter again in 'dd-MM-yyyy' format.");
                            }
                        }
                        System.out.print("Enter Contact Number: ");
                        String contact = scanner.nextLine();
                        System.out.print("Enter Address: ");
                        String address = scanner.nextLine();
                        System.out.print("Enter Designation: ");
                        String designation = scanner.nextLine();
                        System.out.print("Enter Salary: ");
                        double salary = scanner.nextDouble();
                        scanner.nextLine(); // Consume the newline
                        System.out.print("Enter Skills: ");
                        String skills = scanner.nextLine();
                        System.out.print("Enter Experience : ");
                        String experience = scanner.nextLine();
                        System.out.print("Enter number of Certifications: ");
                        int certCount = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        // Array to store certifications
                        String[] certifications = new String[certCount];
                        for (int i = 0; i < certCount; i++) {
                            System.out.print("Enter Certification " + (i + 1) + ": ");
                            certifications[i] = scanner.nextLine();
                        }

                        staff.addStaff(name, gender, dob, contact, address, designation, salary, skills, experience, certifications, LocalDate.now().toString());
                        staff.saveToFile();
                        System.out.println("Staff added successfully.");
                    }
                    break;
                case 2:
                    System.out.println("Deleting a " + entity + "...");
                    if (entity.equals("Staff"))
                    { System.out.print("enter id for delete ");
                        int iD =scanner.nextInt();
                        staff.removeStaff(iD);
                        staff.saveToFile();
                    }
                    break;
                case 3:
                    System.out.println("Updating " + entity + " details...");
                    if (entity.equals("Staff"))
                    { System.out.print("enter id for update ");
                        int Id =scanner.nextInt();
                        staff.updateStudent(Id);
                        staff.saveToFile();
                    }
                    break;
                case 4:
                    System.out.println("Searching for a " + entity + "...");
                    if (entity.equals("Staff"))
                    {  System.out.print("enter id for search ");
                        int id =scanner.nextInt();
                        staff.searchStaff(id);
                        staff.saveToFile();
                        break;
                    }
                    break;
                case 5:
                    return;
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
}



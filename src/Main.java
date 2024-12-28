import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public  class Main {
    static Array Class1 = new Array();
    static Array Class2 = new Array();
    static Array Class3 = new Array();
    static Array Class4 = new Array();
    static Array Class5 = new Array();
    static Array Class6 = new Array();
    static Array Class7 = new Array();
    static Array Class8 = new Array();
    static Array Class9 = new Array();
    static Array Class10 = new Array();
    static String Filename;
     static TeacherList teacher=new TeacherList();
    static StaffList staff =new StaffList();
    static AssignedCoursesClass assignedCourses = new AssignedCoursesClass();
    static  StudentClass student=new StudentClass();
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
                                isLoggedIn = coordinatorMenu(scanner); // Added coordinator menu
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
            case 6: return "Coordinator"; // Added Coordinator
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

                    if (entity.equals("Teacher")) {
                        String dob = "";
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

                        System.out.print("Enter Teacher Name: ");
                        String name = scanner.nextLine();

                        System.out.println("Select Gender: ");
                        System.out.println("1. Male");
                        System.out.println("2. Female");
                        String gender = "";
                        int genderChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline

                        if (genderChoice == 1) {
                            gender = "Male";
                        } else if (genderChoice == 2) {
                            gender = "Female";
                        }

                        while (true) {
                            System.out.print("Enter your Date of Birth (dd-MM-yyyy): ");
                            String input = scanner.nextLine();
                            try {
                                LocalDate birthDate = LocalDate.parse(input, formatter);
                                dob = input;
                                break;
                            } catch (DateTimeParseException e) {
                                System.out.println("Invalid date format. Please enter again in 'dd-MM-yyyy' format.");
                            }
                        }

                        System.out.println("Select Marital Status: ");
                        System.out.println("1. Married");
                        System.out.println("2. Unmarried");
                        System.out.println("3. Widow");
                        String maritalStatus = "";
                        int maritalChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline

                        switch (maritalChoice) {
                            case 1:
                                maritalStatus = "Married";
                                break;
                            case 2:
                                maritalStatus = "Unmarried";
                                break;
                            case 3:
                                maritalStatus = "Widow";
                                break;
                        }

                        System.out.print("Enter Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Enter Specialization: ");
                        String specialization = scanner.nextLine();
                        System.out.print("Enter Highest Qualification: ");
                        String highestQualification = scanner.nextLine();
                        System.out.print("Enter Contact Number: ");
                        String contact = scanner.nextLine();
                        System.out.print("Enter Address: ");
                        String address = scanner.nextLine();

                        System.out.print("Enter Salary: ");
                        double salary = scanner.nextDouble();
                        scanner.nextLine(); // Consume the newline

                        System.out.print("Enter Skills: ");
                        String skills = scanner.nextLine();
                        System.out.print("Enter Experience: ");
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

                        teacher.addTeacher(name, gender, dob, maritalStatus, email, specialization, highestQualification, contact, address, salary,skills, experience, certifications,LocalDate.now().toString());
                        teacher.saveToFile();

                        System.out.println("Teacher added successfully.");
                    }



                    if(entity.equals("Student"))
                    { System.out.print("Enter Class For Admission: ");
                        int currentClass = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        if (student.countStudentsInClass(currentClass) >= 200) {
                            System.out.println("Student capacity is full for this class.");
                            break;
                        }

                        System.out.print("Enter Student Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Father Name: ");
                        String fatherName = scanner.nextLine();
                        System.out.print("Enter Gender: ");
                        String gender = scanner.nextLine();

                        String dob = "";
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                        while (true) {
                            System.out.print("Enter your Date of Birth (dd-MM-yyyy): ");
                            String input = scanner.nextLine();

                            try {
                                LocalDate.parse(input, formatter); // Validate format
                                dob = input;
                                break;
                            } catch (DateTimeParseException e) {
                                System.out.println("Invalid date format. Please enter again in 'dd-MM-yyyy' format.");
                            }
                        }

                        System.out.print("Enter Contact Number: ");
                        String contact = scanner.nextLine();
                        System.out.print("Enter Address: ");
                        String address = scanner.nextLine();

                        student.addStudent(currentClass, name, fatherName, gender, dob, contact, address);
                        System.out.println("Student added successfully.");
//                        student.saveToFile();

                    }
                    break;
                case 2:
                    System.out.println("Deleting a " + entity + "...");
                    if (entity.equals("Staff"))
                    { System.out.print("enter id for delete: ");
                        int iD =scanner.nextInt();
                        staff.removeStaff(iD);
                        staff.saveToFile();
                    }
                    if(entity.equals("Teacher")){
                        System.out.println("Enter Id for Delete: ");
                        int iD =scanner.nextInt();
                        teacher.removeTeacher(iD);
                        teacher.saveToFile();
                        assignedCourses.deleteRecordOfAnyTeacher(iD);
                        assignedCourses.saveToFile();
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
                    if (entity.equals("Teacher"))
                    { System.out.print("enter id for update ");
                        int Id =scanner.nextInt();
                        teacher.updateTeacher(Id);
                        teacher.saveToFile();
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
                    if (entity.equals("Teacher"))
                    {  System.out.print("enter id for search ");
                        int id =scanner.nextInt();
                        teacher.searchTeacher(id);
                        teacher.saveToFile();
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
            System.out.println("1. Generate Fee Voucher");
            System.out.println("2. View Details of Students with Unpaid Fees");
            System.out.println("3. Check Individual Student Fee Status");
            System.out.println("4. Mark Student Fee as Paid");
            System.out.println("5. Back to Login");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    if (student.isEmpty())
                    {
                        System.out.println("Student not available . .");
                        break;
                    }
                    System.out.println("Generating Fee Voucher...");
                    System.out.println("Choose the month for the fee voucher:");
                    System.out.println("1. Current Month");
                    System.out.println("2. Previous Month");
                    System.out.print("Enter your choice: ");
                    int monthChoice = scanner.nextInt(); // kamran yahan just exceptional handling karna sara nhi karna ka input 1 2 hi hoo sahi hai
                    scanner.nextLine();

                    if (monthChoice == 1) {
                        System.out.println("Generating voucher for the current month...");
                        System.out.print("Enter student Id: ");
                        int id=scanner.nextInt();
                        student.generateFeeVouchers(FeeClass.getMonthNameFromDate( LocalDate.now()),id);
                    } else if (monthChoice == 2) {
                        System.out.println("Generating voucher for the previous month...");
                        scanner.nextLine();
                        System.out.print("Enter student Id: ");
                        int id=scanner.nextInt();
                        System.out.println("1.January");
                        System.out.println("2.February");
                        System.out.println("3.March");
                        System.out.println("4.April");
                        System.out.println("5.May");
                        System.out.println("6.June");
                        System.out.println("7.July");
                        System.out.println("8.August");
                        System.out.println("9.September");
                        System.out.println("10.October");
                        System.out.println("11.November");
                        System.out.println("12.December");
                        System.out.print("Enter month no.: ");
                        int month= scanner.nextInt();
                        String MonthName="";
                        switch (month)
                        {
                            case 1:
                                MonthName= "January";
                            case 2:
                                MonthName="February";
                            case 3:
                                MonthName= "March";
                            case 4:
                                MonthName="April";
                            case 5:
                                MonthName= "May";
                            case 6:
                                MonthName= "June";
                            case 7:
                                MonthName= "July";
                            case 8:
                                MonthName= "August";
                            case 9:
                                MonthName= "September";
                            case 10:
                                MonthName= "October";
                            case 11:
                                MonthName= "November";
                            case 12:
                                MonthName= "December";
                        }

                        student.generateFeeVouchers(MonthName,id);
                    } else {
                        System.out.println("Invalid choice. Returning to the menu...");
                    }
                    break;
                case 2:
                    System.out.println("Viewing details of students with unpaid fees...");
                    student.displayAllStudentWithUnpaidFee();
                    break;
                case 3:
                    System.out.println("Checking individual student fee status...");
                    System.out.println("Enter student Id");
                    int Id =scanner.nextInt();
                    student.GenerateFeeStatusTable(Id);
                    break;
                case 4:
                    if (student.isEmpty())
                    {
                        System.out.println("Student not available . .");
                        break;
                    }
                    System.out.println("Marking student fee as paid...");
                    System.out.println("Generating Fee Voucher...");
                    System.out.println("Choose the month for the fee voucher:");
                    System.out.println("1. Current Month");
                    System.out.println("2. Previous Month");
                    System.out.print("Enter your choice: ");
                    int MonthChoice = scanner.nextInt(); // kamran yahan just exceptional handling karna sara nhi karna ka input 1 2 hi hoo sahi hai
                    scanner.nextLine();
                    if (MonthChoice == 1) {
                        System.out.println("Marking student fee for the current month...");
                        System.out.print("Enter student Id: ");
                        int id=scanner.nextInt();
                        System.out.println("Enter voucher number");
                        int VoucherNo=scanner.nextInt();
                        student.PaidFee(id,VoucherNo,FeeClass.getMonthNameFromDate(LocalDate.now()));
                    }
                    else if (MonthChoice == 2) {
                        System.out.println("Generating voucher for the previous month...");
                        scanner.nextLine();
                        System.out.print("Enter student Id: ");
                        int id=scanner.nextInt();
                        System.out.println("Enter voucher number");
                        int VoucherNo=scanner.nextInt();
                        System.out.println("1.January");
                        System.out.println("2.February");
                        System.out.println("3.March");
                        System.out.println("4.April");
                        System.out.println("5.May");
                        System.out.println("6.June");
                        System.out.println("7.July");
                        System.out.println("8.August");
                        System.out.println("9.September");
                        System.out.println("10.October");
                        System.out.println("11.November");
                        System.out.println("12.December");
                        System.out.print("Enter month no.: ");
                        int month= scanner.nextInt();
                        String MonthName="";
                        switch (month) {
                            case 1:
                                MonthName = "January";
                            case 2:
                                MonthName = "February";
                            case 3:
                                MonthName = "March";
                            case 4:
                                MonthName = "April";
                            case 5:
                                MonthName = "May";
                            case 6:
                                MonthName = "June";
                            case 7:
                                MonthName = "July";
                            case 8:
                                MonthName = "August";
                            case 9:
                                MonthName = "September";
                            case 10:
                                MonthName = "October";
                            case 11:
                                MonthName = "November";
                            case 12:
                                MonthName = "December";
                        }
                        student.PaidFee(id,VoucherNo,MonthName);
                    } else {
                        System.out.println("Invalid choice. Returning to the menu...");
                    }
                    break;
                case 5:
                    return false;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static boolean coordinatorMenu(Scanner scanner) {
         int studentClass=0;
         String Course;
         boolean isValid = false;
        while (true) {
            System.out.println("\nCoordinator Menu:");
            System.out.println("1. Assign Course to Teacher");
            System.out.println("2. Unassign Course from Teacher");
            System.out.println("3. View Teachers Teaching Specific Classes");
            System.out.println("4. View Which Teacher is Teaching Which Course in a Specific Class");
            System.out.println("5. Add Courses");
            System.out.println("6. Remove Courses");
            System.out.println("7. Back to Login");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Assigning Course to Teacher...");
                    Teacher temp;
                    if(teacher.isEmpty())
                    {
                        System.out.println("No Teacher Available");
                        break;
                    }
                    System.out.print("enter teacher id : ");
                    int id = scanner.nextInt();
                    if (teacher.validTeacherId(id)==null)
                    {
                        System.out.println("Invalid teacher Id");
                        break;
                    }
                    else{
                         temp =teacher.validTeacherId(id);
                    }
                    System.out.print("enter class :");
                    int Class=scanner.nextInt();
                    System.out.print("enter course :");
                    String course= scanner.nextLine();
                    if(Class==1)
                    {
                        if (Class1.search(course.toLowerCase()))
                        {
                            if (!assignedCourses.checkCourseIsAlreadyAssigned(id,course.toLowerCase(),Class))
                            {
                                assignedCourses.add(id,Class,course.toLowerCase(),temp.Name);
                                assignedCourses.saveToFile();
                            }
                            else {
                                System.out.println(course+" is already assigned !!! ");
                                break;
                            }
                        }
                        else{
                            System.out.println(course+" is not available in class "+Class);
                            break;
                        }
                    }
                    else if(Class==2)
                    {
                        if (Class2.search(course.toLowerCase()))
                        {
                            if (!assignedCourses.checkCourseIsAlreadyAssigned(id,course.toLowerCase(),Class))
                            {
                                assignedCourses.add(id,Class,course.toLowerCase(),temp.Name);
                                assignedCourses.saveToFile();
                            }
                            else {
                                System.out.println(course+" is already assigned !!! ");
                                break;
                            }
                        }
                        else{
                            System.out.println(course+" is not available in class "+Class);
                            break;
                        }
                    }
                   else if(Class==3)
                    { if (Class3.search(course.toLowerCase()))
                    {
                        if (!assignedCourses.checkCourseIsAlreadyAssigned(id,course.toLowerCase(),Class))
                        {
                            assignedCourses.add(id,Class,course.toLowerCase(),temp.Name);
                            assignedCourses.saveToFile();
                        }
                        else {
                            System.out.println(course+" is already assigned !!! ");
                            break;
                        }
                    }
                    else{
                        System.out.println(course+" is not available in class "+Class);
                        break;
                    }}
                    else if(Class==4)
                    { if (Class4.search(course.toLowerCase()))
                    {
                        if (!assignedCourses.checkCourseIsAlreadyAssigned(id,course.toLowerCase(),Class))
                        {
                            assignedCourses.add(id,Class,course.toLowerCase(),temp.Name);
                            assignedCourses.saveToFile();
                        }
                        else {
                            System.out.println(course+" is already assigned !!! ");
                            break;
                        }
                    }
                    else{
                        System.out.println(course+" is not available in class "+Class);
                        break;
                    }}
                    else if(Class==5)
                    { if (Class5.search(course.toLowerCase()))
                        {
                            if (!assignedCourses.checkCourseIsAlreadyAssigned(id,course.toLowerCase(),Class))
                            {
                                assignedCourses.add(id,Class,course.toLowerCase(),temp.Name);
                                assignedCourses.saveToFile();
                            }
                            else {
                                System.out.println(course+" is already assigned !!! ");
                                break;
                            }
                        }
                        else{
                            System.out.println(course+" is not available in class "+Class);
                            break;
                        }}
                    else if(Class==6)
                    { if (Class6.search(course.toLowerCase()))
                        {
                            if (!assignedCourses.checkCourseIsAlreadyAssigned(id,course.toLowerCase(),Class))
                            {
                                assignedCourses.add(id,Class,course.toLowerCase(),temp.Name);
                                assignedCourses.saveToFile();
                            }
                            else {
                                System.out.println(course+" is already assigned !!! ");
                                break;
                            }
                        }
                        else{
                            System.out.println(course+" is not available in class "+Class);
                            break;
                        }}
                    else if(Class==7)
                    { if (Class7.search(course.toLowerCase()))
                        {
                            if (!assignedCourses.checkCourseIsAlreadyAssigned(id,course.toLowerCase(),Class))
                            {
                                assignedCourses.add(id,Class,course.toLowerCase(),temp.Name);
                                assignedCourses.saveToFile();
                            }
                            else {
                                System.out.println(course+" is already assigned !!! ");
                                break;
                            }
                        }
                        else{
                            System.out.println(course+" is not available in class "+Class);
                            break;
                        }}
                    else if(Class==8)
                    {
                        if (Class8.search(course.toLowerCase()))
                        {
                            if (!assignedCourses.checkCourseIsAlreadyAssigned(id,course.toLowerCase(),Class))
                            {
                                assignedCourses.add(id,Class,course.toLowerCase(),temp.Name);
                                assignedCourses.saveToFile();
                            }
                            else {
                                System.out.println(course+" is already assigned !!! ");
                                break;
                            }
                        }
                        else{
                            System.out.println(course+" is not available in class "+Class);
                            break;
                        }}
                    else if(Class==9)
                    {
                        if (Class9.search(course.toLowerCase()))
                        {
                            if (!assignedCourses.checkCourseIsAlreadyAssigned(id,course.toLowerCase(),Class))
                            {
                                assignedCourses.add(id,Class,course.toLowerCase(),temp.Name);
                                assignedCourses.saveToFile();
                            }
                            else {
                                System.out.println(course+" is already assigned !!! ");
                                break;
                            }
                        }
                        else{
                            System.out.println(course+" is not available in class "+Class);
                            break;
                        }}
                    else
                    {
                        if (Class10.search(course.toLowerCase()))
                    {
                            if (!assignedCourses.checkCourseIsAlreadyAssigned(id,course.toLowerCase(),Class))
                            {
                                assignedCourses.add(id,Class,course.toLowerCase(),temp.Name);
                                assignedCourses.saveToFile();
                            }
                            else {
                                System.out.println(course+" is already assigned !!! ");
                                break;
                            }
                    }
                    else{
                        System.out.println(course+" is not available in class "+Class);
                        break;
                    }}

                    break;
                case 2:
                    System.out.println("Unassigning Course from Teacher...");
                    if(assignedCourses.isEmpty())
                    {
                        System.out.println("No Teacher Available");
                        break;
                    }
                    System.out.print("enter teacher id : ");
                    int Id = scanner.nextInt();
                    if (teacher.validTeacherId(Id)==null)
                    {
                        System.out.println("Invalid teacher Id");
                        break;
                    }
                    System.out.print("enter class :");
                    int ClassNumber=scanner.nextInt();
                     if (assignedCourses.search(Id))
                     {
                         System.out.println("Courses assigned to "+Id+" in class  "+ClassNumber);
                         assignedCourses.printCoursesForTeacherInSpecificClass(Id,ClassNumber);
                     }
                     else
                     {
                         System.out.println("No course assigned to "+Id+" in class  "+ClassNumber);
                         break;
                     }
                    System.out.print("enter course to unassigned");
                    String CourseName= scanner.nextLine();
                    assignedCourses.delete(Id,CourseName.toLowerCase(),ClassNumber);
                    break;
                case 3:
                    System.out.println("Viewing Teachers Teaching Specific Classes...");
                    System.out.print("enter class : ");
                    int classnumber=scanner.nextInt();
                    assignedCourses.displaySpecificClassById(classnumber);
                    break;
                case 4:
                    System.out.println("Viewing Which Teacher is Teaching Which Course in a Specific Class...");
                    if(teacher.isEmpty())
                    {
                        System.out.println("Teacher not available");
                    }
                    System.out.print("enter teacher id : ");
                    int teacherid =scanner.nextInt();
                    if (teacher.validTeacherId(teacherid)==null)
                    {
                        System.out.println("Invalid teacher Id");
                        break;
                    }
                    assignedCourses.displaySpecificTeacherById(teacherid);
                    break;
                case 5:
                    isValid = false; // Resetting the validation flag
                    while (!isValid) {
                        System.out.println("Enter Class (1-10):");
                        studentClass = scanner.nextInt();

                        if (studentClass >= 1 && studentClass <= 10) {
                            isValid = true; // Exit the loop if valid
                        } else {
                            System.out.println("Invalid Class! Please enter a value between 1 and 10.");
                        }
                    }
                    System.out.println("Input Course Name: ");
                    scanner.nextLine(); // Clear the input buffer
                    Course = scanner.nextLine();

                    // Process based on the class number
                    if (studentClass == 1) {
                        if (Class1.search(Course)) {
                            System.out.println(Course + " course is already added in class " + studentClass);
                        } else {
                            Class1.insert(Course);
                            saveCoursesToFile();
                        }
                    } else if (studentClass == 2) {
                        if (Class2.search(Course)) {
                            System.out.println(Course + " course is already added in class " + studentClass);
                        } else {
                            Class2.insert(Course);
                            saveCoursesToFile();
                        }
                    } else if (studentClass == 3) {
                        if (Class3.search(Course)) {
                            System.out.println(Course + " course is already added in class " + studentClass);
                        } else {
                            Class3.insert(Course);
                            saveCoursesToFile();
                        }
                    } else if (studentClass == 4) {
                        if (Class4.search(Course)) {
                            System.out.println(Course + " course is already added in class " + studentClass);
                        } else {
                            Class4.insert(Course);
                            saveCoursesToFile();
                        }
                    } else if (studentClass == 5) {
                        if (Class5.search(Course)) {
                            System.out.println(Course + " course is already added in class " + studentClass);
                        } else {
                            Class5.insert(Course);
                            saveCoursesToFile();
                        }
                    } else if (studentClass == 6) {
                        if (Class6.search(Course)) {
                            System.out.println(Course + " course is already added in class " + studentClass);
                        } else {
                            Class6.insert(Course);
                            saveCoursesToFile();
                        }
                    } else if (studentClass == 7) {
                        if (Class7.search(Course)) {
                            System.out.println(Course + " course is already added in class " + studentClass);
                        } else {
                            Class7.insert(Course);
                            saveCoursesToFile();
                        }
                    } else if (studentClass == 8) {
                        if (Class8.search(Course)) {
                            System.out.println(Course + " course is already added in class " + studentClass);
                        } else {
                            Class8.insert(Course);
                            saveCoursesToFile();
                        }
                    } else if (studentClass == 9) {
                        if (Class9.search(Course)) {
                            System.out.println(Course + " course is already added in class " + studentClass);
                        } else {
                            Class9.insert(Course);
                            saveCoursesToFile();
                        }
                    } else {
                        if (Class10.search(Course)) {
                            System.out.println(Course + " course is already added in class " + studentClass);
                        } else {
                            Class10.insert(Course);
                            saveCoursesToFile();
                        }
                    }
                    break;

                case 6:
                    isValid = false; // Resetting the validation flag
                    while (!isValid) {
                        System.out.println("Enter Class (1-10):");
                        studentClass = scanner.nextInt();

                        if (studentClass >= 1 && studentClass <= 10) {
                            isValid = true; // Exit the loop if valid
                        } else {
                            System.out.println("Invalid Class! Please enter a value between 1 and 10.");
                        }
                    }
                    System.out.println("Input Course Name: ");
                    scanner.nextLine(); // Clear the input buffer
                    Course = scanner.nextLine();

                    // Process based on the class number
                    if (studentClass == 1) {
                        Class1.delete(Class1.position(Course));
                        saveCoursesToFile();
                        assignedCourses.deleteRecordOfAnyCourseOfAnyClass(1,Course);
                        assignedCourses.saveToFile();
                    } else if (studentClass == 2) {
                        Class2.delete(Class2.position(Course));
                        saveCoursesToFile();
                        assignedCourses.deleteRecordOfAnyCourseOfAnyClass(2,Course);
                        assignedCourses.saveToFile();
                    } else if (studentClass == 3) {
                        Class3.delete(Class3.position(Course));
                        saveCoursesToFile();
                        assignedCourses.deleteRecordOfAnyCourseOfAnyClass(3,Course);
                        assignedCourses.saveToFile();
                    } else if (studentClass == 4) {
                        Class4.delete(Class4.position(Course));
                        saveCoursesToFile();
                        assignedCourses.deleteRecordOfAnyCourseOfAnyClass(4,Course);
                        assignedCourses.saveToFile();
                    } else if (studentClass == 5) {
                        Class5.delete(Class5.position(Course));
                        saveCoursesToFile();
                        assignedCourses.deleteRecordOfAnyCourseOfAnyClass(5,Course);
                        assignedCourses.saveToFile();
                    } else if (studentClass == 6) {
                        Class6.delete(Class6.position(Course));
                        saveCoursesToFile();
                        assignedCourses.deleteRecordOfAnyCourseOfAnyClass(6,Course);
                        assignedCourses.saveToFile();
                    } else if (studentClass == 7) {
                        Class7.delete(Class7.position(Course));
                        saveCoursesToFile();
                        assignedCourses.deleteRecordOfAnyCourseOfAnyClass(7,Course);
                        assignedCourses.saveToFile();
                    } else if (studentClass == 8) {
                        Class8.delete(Class8.position(Course));
                        saveCoursesToFile();
                        assignedCourses.deleteRecordOfAnyCourseOfAnyClass(8,Course);
                        assignedCourses.saveToFile();
                    } else if (studentClass == 9) {
                        Class9.delete(Class9.position(Course));
                        saveCoursesToFile();
                        assignedCourses.deleteRecordOfAnyCourseOfAnyClass(9,Course);
                        assignedCourses.saveToFile();
                    } else {
                        Class10.delete(Class10.position(Course));
                        saveCoursesToFile();
                        assignedCourses.deleteRecordOfAnyCourseOfAnyClass(10,Course);
                        assignedCourses.saveToFile();
                    }
                    break;
                case 7:
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
            System.out.println("4. Generate mark sheet");
            System.out.println("5. Back to Login");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Uploading Marks...");
                    StudentNode temp;
                    if (student.isEmpty()) {
                        System.out.println("Students not available");
                        break;
                    }
                    System.out.print("enter student Id :");
                    int id = scanner.nextInt();
                    if (student.uploadMarks(id) == null) {
                        System.out.println("Invalid Id");
                        break;
                    }
                    else {
                        temp = student.uploadMarks(id);
                    }
                    if (!temp.marks.isEmpty())
                    {
                        System.out.println("marks already assigned to "+id);
                        break;
                    }
                    if (temp.CurrentClass == 1) {
                        for (int i = 0; i < Class1.size(); i++) {
                            System.out.print("Enter marks of " + Class1.GetCertificate(i) + ":");
                            double marks = scanner.nextDouble();
                            temp.marks.addMarks(Class1.GetCertificate(i), marks);
                        }
                    } else if (temp.CurrentClass == 2) {
                        for (int i = 0; i < Class2.size(); i++) {
                            System.out.print("Enter marks of " + Class2.GetCertificate(i) + ":");
                            double marks = scanner.nextDouble();
                            temp.marks.addMarks(Class2.GetCertificate(i), marks);
                        }
                    } else if (temp.CurrentClass == 3) {
                        for (int i = 0; i < Class3.size(); i++) {
                            System.out.print("Enter marks of " + Class3.GetCertificate(i) + ":");
                            double marks = scanner.nextDouble();
                            temp.marks.addMarks(Class3.GetCertificate(i), marks);
                        }
                    }
                     else if (temp.CurrentClass==4) {
                          for (int i = 0; i < Class4.size(); i++) {
                                 System.out.print("Enter marks of "+Class4.GetCertificate(i)+":");
                                 double marks = scanner.nextDouble();
                                 temp.marks.addMarks(Class4.GetCertificate(i),marks);
                             }
                         }
                     else if (temp.CurrentClass==5) {
                         for (int i = 0; i < Class5.size(); i++) {
                                 System.out.print("Enter marks of "+Class5.GetCertificate(i)+":");
                                 double marks = scanner.nextDouble();
                                 temp.marks.addMarks(Class5.GetCertificate(i),marks);
                             }
                     }
                     else if (temp.CurrentClass==6) {
                             for (int i = 0; i < Class6.size(); i++) {
                                 System.out.print("Enter marks of "+Class6.GetCertificate(i)+":");
                                 double marks = scanner.nextDouble();
                                 temp.marks.addMarks(Class6.GetCertificate(i),marks);
                             }
                     }
                     else if (temp.CurrentClass==7) {
                             for (int i = 0; i < Class7.size(); i++) {
                                 System.out.print("Enter marks of "+Class7.GetCertificate(i)+":");
                                 double marks = scanner.nextDouble();
                                 temp.marks.addMarks(Class7.GetCertificate(i),marks);
                             }
                     }
                     else if (temp.CurrentClass==8) {
                             for (int i = 0; i < Class8.size(); i++) {
                                 System.out.print("Enter marks of "+Class8.GetCertificate(i)+":");
                                 double marks = scanner.nextDouble();
                                 temp.marks.addMarks(Class8.GetCertificate(i),marks);
                             }
                     }
                     else if (temp.CurrentClass==9) {
                             for (int i = 0; i < Class9.size(); i++) {
                                 System.out.print("Enter marks of "+Class9.GetCertificate(i)+":");
                                 double marks = scanner.nextDouble();
                                 temp.marks.addMarks(Class9.GetCertificate(i),marks);
                             }
                     }
                     else
                     {
                         for (int i = 0; i < Class10.size(); i++) {
                             System.out.print("Enter marks of "+Class10.GetCertificate(i)+":");
                             double marks = scanner.nextDouble();
                             temp.marks.addMarks(Class10.GetCertificate(i),marks);
                         }
                     }
                    break;
                case 2:
                    System.out.println("Viewing Marks...");
                    if (student.isEmpty()) {
                        System.out.println("Students not available");
                        break;
                    }
                    System.out.print("enter student Id :");
                    int Id = scanner.nextInt();
                    if (student.uploadMarks(Id) == null) {
                        System.out.println("Invalid Id");
                        break;
                    }
                    else {
                        temp = student.uploadMarks(Id);
                    }
                    if (!temp.marks.isEmpty())
                    {
                        System.out.println("marks is not assigned to "+Id);
                        break;
                    }
                    else
                    {
                       temp.marks.displayStudentMarks();
                    }
                    break;
                case 3:
                    System.out.println("Editing Marks...");
                    if (student.isEmpty()) {
                        System.out.println("Students not available");
                        break;
                    }
                    System.out.print("enter student Id :");
                    int ID = scanner.nextInt();
                    if (student.uploadMarks(ID) == null) {
                        System.out.println("Invalid Id");
                        break;
                    }
                    else {
                        temp = student.uploadMarks(ID);
                    }
                    if (!temp.marks.isEmpty())
                    {
                        System.out.println("marks is not assigned to "+ID);
                        break;
                    }
                    else
                    {
                        temp.marks.updateStudentMarks();
                    }
                    break;
                case 4:
                    System.out.println("Generating mark sheet...");
                    if (student.isEmpty()) {
                        System.out.println("Students not available");
                        break;
                    }
                    System.out.print("enter student Id :");
                    int iD = scanner.nextInt();
                    if (student.uploadMarks(iD) == null) {
                        System.out.println("Invalid Id");
                        break;
                    }
                    else {
                        temp = student.uploadMarks(iD);
                    }
                    if (!temp.marks.isEmpty())
                    {
                        System.out.println("marks is not assigned to "+iD);
                        break;
                    }
                    else
                    {
                        temp.marks.displayMarkSheet();
                    }
                    break;
                case 5:
                    return false;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public static void saveCoursesToFile()
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Filename))) {
            for (int i = 1; i < 11; i++) {
                writer.write("\t\t\t\t\t* * * * COURSES DETAILS * * * *\n\n");
                writer.write("class "+i+"\n\n");
                if (i==1)
                {
                    for (int j = 0; j < Class1.size(); j++) {
                        writer.write(". "+Class1.GetCertificate(j)+"\n");
                    }
                }
             else if (i==2)
                {
                    for (int j = 0; j < Class2.size(); j++) {
                        writer.write(". "+Class2.GetCertificate(j)+"\n");
                    }
                }
              else if (i==3)
                {
                    for (int j = 0; j < Class3.size(); j++) {
                        writer.write(". "+Class3.GetCertificate(j)+"\n");
                    }
                }
               else if (i==4)
                {
                    for (int j = 0; j < Class4.size(); j++) {
                        writer.write(". "+Class4.GetCertificate(j)+"\n");
                    }
                }
               else if (i==5)
                {
                    for (int j = 0; j < Class5.size(); j++) {
                        writer.write(". "+Class5.GetCertificate(j)+"\n");
                    }
                }
              else if (i==6)
                {
                    for (int j = 0; j < Class6.size(); j++) {
                        writer.write(". "+Class6.GetCertificate(j)+"\n");
                    }
                }
              else if (i==7)
                {
                    for (int j = 0; j < Class7.size(); j++) {
                        writer.write(". "+Class7.GetCertificate(j)+"\n");
                    }
                }
             else if (i==8)
                {
                    for (int j = 0; j < Class8.size(); j++) {
                        writer.write(". "+Class8.GetCertificate(j)+"\n");
                    }
                }
             else if (i==9)
                {
                    for (int j = 0; j < Class9.size(); j++) {
                        writer.write(". "+Class9.GetCertificate(j)+"\n");
                    }
                }
               else
                {
                    for (int j = 0; j < Class10.size(); j++) {
                        writer.write(". "+Class10.GetCertificate(j)+"\n");
                    }
                }
                writer.write("\n\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving teacher details to file.");
        }
    }



}

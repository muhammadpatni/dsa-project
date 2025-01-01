import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;


public class TeacherList {
    int TotalNumberOfTeachers;
    Scanner sc = new Scanner(System.in);
    Teacher head, tail;

    TeacherList() {
        head = null;
        tail = null;
        TotalNumberOfTeachers = 0;
    }

    String Filename ="C:\\Users\\HP\\Desktop\\lab 1\\DSA project\\teacher.txt";
//   String Filename ="C:\\Users\\Admin\\Desktop\\dsa-project1\\teacher.txt";

    public boolean isEmpty() {
        return head == null;
    }
    public void addTeacher(String Name, String Gender, String DateOfBirth, String MaritalStatus, String Email,
                           String Specialization, String HigestQualification, String Contact, String Address,
                           double Salary, String Skills, String Experience,
                           String[] Certificates,String DateOfJoining) {
        TotalNumberOfTeachers++;
        Teacher newNode = new Teacher();
        newNode.Certifications = new Array();
        newNode.TeacherID = TotalNumberOfTeachers;
        newNode.Name = Name.toLowerCase();
        newNode.Gender = Gender;
        newNode.DateOfBirth = DateOfBirth;
        newNode.MaritalStatus = MaritalStatus;
        newNode.Email = Email;
        newNode.Specialization = Specialization;
        newNode.HigestQualification = HigestQualification;
        newNode.Contact = Contact;
        newNode.Address = Address;
        newNode.DateOfJoining =DateOfJoining;
        newNode.Salary = Salary;
        newNode.Skills = Skills;
        newNode.Experience = Experience;
        for (String certification : Certificates) {
            newNode.Certifications.insert(certification);
        }
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode ;
        }
    }

    public int calculateAge(String dob) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate birthDate = LocalDate.parse(dob, formatter);
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(birthDate, currentDate).getYears();
        return age;
    }

    public void displayAllTeachers()
    {
        //id name experience gender contact salary age email highest Qualification Specialization
        Teacher temp=head;
        StyledConsoleOutput.printStyled("                                                 * * * TEACHER DETAILS * * *                               ",true,false,"cyan");
        System.out.println("|==========|====================|===========|=============================|===================|=============|========|========================================|=============================|=============================|");
        System.out.println("|    ID    |        NAME        |   GENDER  |        EXPERIENCE           |      CONTACT      |    SALARY   |  Age   |                   EMAIL                |    HIGHEST QUALIFICATION    |      SPECIALIZATION         |");
        System.out.println("|==========|====================|===========|=============================|===================|=============|========|========================================|=============================|=============================|");
        if (isEmpty()) {
            System.out.println("|  - - - - |       - - - -      |  - - - -  |           - - - -           |      - - - -      |   - - - -   | - - -  |               - - - -                  |            - - - -          |          - - - -            |");
            System.out.println("|----------|--------------------|-----------|-----------------------------|-------------------|-------------|--------|----------------------------------------|-----------------------------|-----------------------------|");

            return;
        }
        while(temp!=null){
            int Age=calculateAge(temp.DateOfBirth);
            System.out.printf("|%-10s|%-20s|%-11s|%-29s|%-19s|%-13s|%-8s|%-40s|%-29s|%-29s|\n",temp.TeacherID,temp.Name,temp.Gender,temp.Experience,temp.Contact,temp.Salary,Age,temp.Email,temp.HigestQualification, temp.Specialization);
            System.out.println("|----------|--------------------|-----------|-----------------------------|-------------------|-------------|--------|----------------------------------------|-----------------------------|----------------------------|");
            temp=temp.next;
        }
    }

    public void updateTeacher(int TeacherID) {
        if (isEmpty()) {
            System.out.println("no Teacher available ..");
            return;
        }
        Teacher temp = head;
        while (temp != null) {
            if (temp.TeacherID == TeacherID)
                break;
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("Invalid ID");
            return;
        }
        //dob name marital status email contact address
        while (true) {
            System.out.println("\nCurrent Details :");
            System.out.println("Name : " + temp.Name);
            System.out.println("Gender : " + temp.Gender);
            System.out.println("Contact : " + temp.Contact);
            System.out.println("Email : " + temp.Email);
            System.out.println("Dob : " + temp.DateOfBirth);
            System.out.println("Address : " + temp.Address);
            System.out.println("Marital Status : " + temp.MaritalStatus + "\n\n");

            int choice = 0;
            while (true) {
                try {
                    System.out.println("1. Name");
                    System.out.println("2. Gender");
                    System.out.println("3. Contact");
                    System.out.println("4. Email");
                    System.out.println("5. Dob");
                    System.out.println("6. Address");
                    System.out.println("7. Marital Status");
                    System.out.println("0. Save");
                    System.out.print("Select option which you want to update: ");
                    choice = sc.nextInt();
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a valid choice.");
                    sc.nextLine(); // Clear the buffer
                }
            }

            switch (choice) {
                case 1:
                    while (true) {
                        try {
                            System.out.print("Enter new name: ");
                            sc.nextLine();
                            String name = sc.nextLine();
                            temp.Name = name;
                            break;
                        } catch (Exception e) {
                            System.out.println("Invalid input. Please enter a valid name.");
                        }
                    }
                    break;

                case 2:
                    while (true) {
                        try {
                            System.out.println("Enter new gender:");
                            System.out.println("1. Male");
                            System.out.println("2. Female");
                            System.out.println("3. Other");
                            int gender = sc.nextInt();
                            sc.nextLine();
                            if (gender == 1)
                                temp.Gender = "Male";
                            else if (gender == 2)
                                temp.Gender = "Female";
                            else if (gender == 3)
                                temp.Gender = "Other";
                            else
                                System.out.println("Invalid choice!");
                            break;
                        } catch (Exception e) {
                            System.out.println("Invalid input. Please enter a valid gender choice.");
                            sc.nextLine(); // Clear the buffer
                        }
                    }
                    break;

                case 3:
                    while (true) {
                        try {
                            System.out.print("Enter new contact: ");
                            sc.nextLine();
                            String contact = sc.nextLine();
                            temp.Contact = contact;
                            break;
                        } catch (Exception e) {
                            System.out.println("Invalid input. Please enter a valid contact.");
                        }
                    }
                    break;

                case 4:
                    while (true) {
                        try {
                            System.out.print("Enter new Email: ");
                            sc.nextLine();
                            String email = sc.nextLine();
                            temp.Email = email;
                            break;
                        } catch (Exception e) {
                            System.out.println("Invalid input. Please enter a valid email.");
                        }
                    }
                    break;

                case 5:
                    String dob = "";
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    while (true) {
                        try {
                            System.out.print("Enter new DOB (dd-MM-yyyy): ");
                            sc.nextLine();
                            String input = sc.nextLine();
                            LocalDate birthDate = LocalDate.parse(input, formatter);
                            dob = input;
                            temp.DateOfBirth = dob;
                            break;
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid date format. Please enter again in 'dd-MM-yyyy' format.");
                        } catch (Exception e) {
                            System.out.println("Invalid input. Please enter a valid date.");
                        }
                    }
                    break;

                case 6:
                    while (true) {
                        try {
                            System.out.print("Enter new Address: ");
                            String Address = sc.nextLine();
                            temp.Address = Address;
                            break;
                        } catch (Exception e) {
                            System.out.println("Invalid input. Please enter a valid address.");
                        }
                    }
                    break;

                case 7:
                    while (true) {
                        try {
                            System.out.print("Enter new Marital Status: ");
                            sc.nextLine();
                            System.out.println("1. Married");
                            System.out.println("2. UnMarried");
                            System.out.println("3. Widow");
                            int status = sc.nextInt();
                            sc.nextLine();
                            if (status == 1)
                                temp.MaritalStatus = "Married";
                            else if (status == 2)
                                temp.MaritalStatus = "UnMarried";
                            else if (status == 3)
                                temp.MaritalStatus = "Widow";
                            else
                                System.out.println("Invalid choice!");
                            break;
                        } catch (Exception e) {
                            System.out.println("Invalid input. Please enter a valid marital status.");
                            sc.nextLine(); // Clear the buffer
                        }
                    }
                    break;


                case 0:
                    System.out.println("Teacher updated successfully . . . ");
                    saveToFile();
                    return;

                default:
                    System.out.println("Invalid choice, please choose again.");
            }

        }

    }

    public void removeTeacher(int TeacherID)
    {
        if(isEmpty())
        {
            System.out.println("no Teacher available ..");
            return;
        }
        Teacher temp=head;
        while(temp!=null){
            if (temp.TeacherID==TeacherID)
            {
                break;
            }
            temp=temp.next;
        }
        if (temp==null)
        {
            System.out.println("Invalid ID");
            return;
        }
        if (temp==head)
        {   head=temp.next;
            saveToFile();
            return;
        }
        if (temp==tail)
        {
            tail=temp.prev;
            tail.next=null;
            saveToFile();
            return;
        }
        temp.prev.next = temp.next;
        temp.next.prev = temp.prev;
        saveToFile();
    }

    public void searchTeacher(int TeacherID) {
        if (isEmpty()) {
            System.out.println("no teacher available ..");
            return;
        }
        Teacher temp = head;
        while (temp != null) {
            if (temp.TeacherID == TeacherID)
                break;
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("Invalid ID");
            return;
        } else {
            StyledConsoleOutput.printStyled("                                                 * * * TEACHER DETAILS * * *                               \n",true, false, "cyan");
            StyledConsoleOutput.printStyled("\uF0D8\tPersonal details : ",true,false,"green");
            System.out.println("|====================|============|===================|=============|================|=========================================|");
            System.out.println("|        NAME        |   GENDER   |       DOB         |   CONTACT   | MARITAL STATUS |                  EMAIL                  |");
            System.out.println("|====================|============|===================|=============|================|=========================================|");
            System.out.printf("|%-20s|%-12s|%-19s|%-13s|%-16s|%-41s|\n", temp.Name, temp.Gender, temp.DateOfBirth, temp.Contact, temp.MaritalStatus, temp.Email);
            System.out.println("|--------------------|------------|-------------------|-------------|----------------|-----------------------------------------|\n");

            StyledConsoleOutput.printStyled("\uF0D8\tEmployment Details: ",true,false,"green");
            System.out.println("|====================|===================|===================|=============|");
            System.out.println("|    TEACHER ID      |  SPECIALIZATION   |  DATE OF JOINING  |    SALARY   |");
            System.out.println("|====================|===================|===================|=============|");
            System.out.printf("|%-20s|%-19s|%-19s|%-13s|\n", temp.TeacherID, temp.Specialization, temp.DateOfJoining, temp.Salary);
            System.out.println("|--------------------|-------------------|-------------------|-------------|\n");

            StyledConsoleOutput.printStyled("\uF0D8\tSkills and Experience: ",true,false,"green");
            System.out.println("|===================================|===================|================================|================================|");
            System.out.println("|              SKILLS               |    EXPERIENCE     |     HIGHEST QUALIFICATION      |           CERTIFICATION        |");
            System.out.println("|===================================|===================|================================|================================|");
            System.out.printf("|%-35s|%-19s|%-32s|", temp.Skills, temp.Experience,temp.HigestQualification);
            if (temp.Certifications.noCertificate())
                System.out.printf("%-32s|\n","no certificate");
            else {
                for (int i = 0; i < temp.Certifications.size(); i++) {
                    String certificate= temp.Certifications.GetCertificate(i);
                    if (i==0)
                        System.out.printf("%-32s|\n",certificate);
                    else
                        System.out.printf("|%-35s|%-19s|%-32s|%-32s|\n","","","",certificate);
                }
            }
            System.out.println("|-----------------------------------|-------------------|--------------------------------|--------------------------------|\n");
        }
    }
    public Teacher validTeacherId(int id)
    {
        Teacher temp = head;
        while (temp != null) {
            if (temp.TeacherID == id)
            {break;}
            temp = temp.next;
        }
        if (temp==null)
            return null;
        return  temp;
    }
    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Filename))) {
            Teacher temp = head;
            writer.write("\t\t\t\t\t* * * * TEACHER DETAILS * * * *\n\n");
            while (temp != null) {
                writer.write("Teacher ID: " + temp.TeacherID + "\n");
                writer.write("Name: " + temp.Name + "\n");
                writer.write("Gender: " + temp.Gender + "\n");
                writer.write("Marital Status: "+temp.MaritalStatus+"\n");
                writer.write("DOB: " + temp.DateOfBirth + "\n");
                writer.write("Date of Joining: " + temp.DateOfJoining + "\n");
                writer.write("Contact: " + temp.Contact + "\n");
                writer.write("Address: " + temp.Address + "\n");
                writer.write("Email: "+temp.Email+"\n");
                writer.write("Salary: " + temp.Salary + "\n");
                writer.write("Skills: " + temp.Skills + "\n");
                writer.write("Experience: " + temp.Experience + "\n");
                writer.write("Highest Qualification: " + temp.HigestQualification + "\n");
                writer.write("Specialization: " + temp.Specialization + "\n");
                writer.write("Certifications:\n");

                if (temp.Certifications != null && temp.Certifications.size() > 0) {
                    for (int i = 0; i < temp.Certifications.size(); i++) {
                        String certification = temp.Certifications.GetCertificate(i);
                        writer.write("• " + certification + "\n");
                    }
                } else {
                    writer.write("No certifications available.\n");
                }

                writer.write("\n\n");
                temp = temp.next;
            }
        } catch (IOException e) {
            System.out.println("Error saving teacher details to file.");
        }
    }


    public void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(Filename))) {
            String line;

            // Variables to store teacher details
            int teacherId = 0;
            String name = "", gender = "", dob = "", contact = "", address = "",
                    specialization = "", skills = "", experience = "", dateOfJoining = "", email = "", maritalStatus = "",
                    highestQualification = "";
            double salary = 0.0;
            Array certifications = new Array();

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Teacher ID:")) {

                    // Add the previous teacher details to the list before starting a new record
                    if (teacherId != 0) {
                        TotalNumberOfTeachers = teacherId - 1; // Update count before adding a new teacher
                        addTeacher(name, gender, dob, maritalStatus, email, specialization, highestQualification,
                                contact, address, salary, skills, experience,
                                certifications.giveCurrentArray(),dateOfJoining);
                    }

                    // Start a new teacher record
                    teacherId = Integer.parseInt(line.split(":")[1].trim());
                    certifications.makeArrayEmpty(); // Reset certifications
                    name = gender = dob = contact = address = specialization = skills = experience =
                            dateOfJoining = email = maritalStatus = highestQualification = "";
                    salary = 0.0;

                } else if (line.startsWith("Name:")) {
                    name = line.split(":")[1].trim();

                } else if (line.startsWith("Gender:")) {
                    gender = line.split(":")[1].trim();

                } else if (line.startsWith("DOB:")) {
                    dob = line.split(":")[1].trim();

                } else if (line.startsWith("Date of Joining:")) {
                    dateOfJoining = line.split(":")[1].trim();

                } else if (line.startsWith("Contact:")) {
                    contact = line.split(":")[1].trim();

                } else if (line.startsWith("Address:")) {
                    address = line.split(":")[1].trim();

                }  else if (line.startsWith("Salary:")) {
                    salary = Double.parseDouble(line.split(":")[1].trim());

                } else if (line.startsWith("Skills:")) {
                    skills = line.split(":")[1].trim();

                } else if (line.startsWith("Experience:")) {
                    experience = line.split(":")[1].trim();

                } else if (line.startsWith("Specialization:")) {
                    specialization = line.split(":")[1].trim();

                } else if (line.startsWith("Email:")) {
                    email = line.split(":")[1].trim();

                } else if (line.startsWith("Marital Status:")) {
                    maritalStatus = line.split(":")[1].trim();

                } else if (line.startsWith("Highest Qualification:")) {
                    highestQualification = line.split(":")[1].trim();

                } else if (line.startsWith("\u2022 ")) { // Unicode for bullet point (•)
                    certifications.insert(line.substring(2).trim());
                }
            }

            if (teacherId != 0) {
                TotalNumberOfTeachers = teacherId - 1; // Update count before adding the last teacher
                addTeacher(name, gender, dob, maritalStatus, email, specialization, highestQualification,
                        contact, address,  salary, skills, experience,
                        certifications.giveCurrentArray(),dateOfJoining);
            }

        } catch (IOException e) {
            System.out.println("Error reading teacher details from file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred while reading teacher details: " + e.getMessage());
        }
    }
    public void radixSortByName() {
        int maxLength = findMaxLength(); // Find the maximum length of strings
        for (int exp = maxLength - 1; exp >= 0; exp--) { // Sort by each character from right to left
            countingSort(exp);
        }
    }

    private int findMaxLength() {
        int maxLength = 0;
        Teacher current = head;

        while (current != null) {
            int length = 0;

            // Calculate the length of the string manually
            for (char c : current.Name.toCharArray()) {
                length++;
            }

            // Compare and update the maximum length manually
            if (length > maxLength) {
                maxLength = length;
            }

            current = current.next;
        }

        return maxLength;
    }


    private void countingSort(int charIndex) {
        if (head == null) return;

        int size = getCount();
        Teacher[] output = new Teacher[size];
        int[] count = new int[256]; // Count array for all ASCII characters

        // Count occurrences of characters at the given index
        Teacher current = head;
        while (current != null) {
            char c = charAt(current.Name, charIndex);
            count[c]++;
            current = current.next;
        }

        // Update count array to store cumulative counts
        for (int i = 1; i < 256; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array
        current = tail;
        while (current != null) {
            char c = charAt(current.Name, charIndex);
            output[count[c] - 1] = current;
            count[c]--;
            current = current.prev;
        }

        // Reconstruct the doubly linked list
        head = output[0];
        head.prev = null;

        for (int i = 1; i < size; i++) {
            output[i - 1].next = output[i];
            if (output[i] != null) {
                output[i].prev = output[i - 1];
            }
        }

        tail = output[size - 1];
        if (tail != null) {
            tail.next = null;
        }
    }

    private char charAt(String str, int index) {
        if (index < 0 || index >= str.length()) return 0; // Null character for out-of-bound indices
        return str.charAt(index);
    }
    public void radixSortById() {
        int max = findMaxId(); // Find the maximum value to determine the number of digits
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortById(exp);
        }
    }

    private int findMaxId() {
        int max = Integer.MIN_VALUE;
        Teacher current = head;
        while (current != null) {
            if (current.TeacherID > max) {
                max = current.TeacherID;
            }
            current = current.next;
        }
        return max;
    }

    private void countingSortById(int exp) {
        if (head == null) return;

        int size = getCount();
        Teacher[] output = new Teacher[size];
        int[] count = new int[10]; // Count array for digits 0-9

        // Step 1: Count occurrences of digits
        Teacher current = head;
        while (current != null) {
            int index = (current.TeacherID / exp) % 10;
            count[index]++;
            current = current.next;
        }

        // Step 2: Update count array to store cumulative counts
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Step 3: Build the output array
        current = tail;
        while (current != null) {
            int index = (current.TeacherID / exp) % 10;
            output[count[index] - 1] = current;
            count[index]--;
            current = current.prev;
        }

        // Step 4: Reconstruct the doubly linked list
        head = output[0];
        head.prev = null;

        for (int i = 1; i < size; i++) {
            output[i - 1].next = output[i];
            if (output[i] != null) {
                output[i].prev = output[i - 1];
            }
        }

        tail = output[size - 1];
        if (tail != null) {
            tail.next = null;
        }
    }
    private int getCount() {
        int count = 0;
        Teacher current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
}

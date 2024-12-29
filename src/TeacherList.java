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

    //String Filename="C:\\Users\\HP\\Desktop\\lab 1\\DSA project\\Staff.txt";
    String Filename ="C:\\Users\\Admin\\Desktop\\dsa-project1\\Teacher.txt";

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
        newNode.Name = Name;
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
        if (isEmpty())
            head = newNode;
        else {
            tail.next = newNode;
        }
        newNode.prev = tail;
        tail = newNode;
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
        Teacher temp=head;
        System.out.println("                                                 * * * TEACHER DETAILS * * *                               ");
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

            System.out.println("1. Name");
            System.out.println("2. Gender");
            System.out.println("3. Contact");
            System.out.println("4. Email");
            System.out.println("5. Dob");
            System.out.println("6. Address");
            System.out.println("7. Marital Status");
            System.out.println("0. Save");
            System.out.print("Select option which you want to update : ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter new name: ");
                    sc.nextLine();
                    String name = sc.nextLine();
                    temp.Name = name;
                    break;

                case 2:
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

                case 3:
                    System.out.print("Enter new contact: ");
                    sc.nextLine();
                    String contact = sc.nextLine();
                    temp.Contact = contact;
                    break;

                case 4:
                    System.out.print("Enter new Email: ");
                    sc.nextLine();
                    String email = sc.nextLine();
                    temp.Email = email;
                    break;

                case 5:
                    String dob = "";
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    while (true) {
                        System.out.print("Enter new DOB (dd-MM-yyyy): ");
                        sc.nextLine();
                        String input = sc.nextLine();
                        try {
                            LocalDate birthDate = LocalDate.parse(input, formatter);
                            dob = input;
                            break;
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid date format. Please enter again in 'dd-MM-yyyy' format.");
                        }
                    }
                    temp.DateOfBirth = dob;
                    break;

                case 6:
                    System.out.print("Enter new Address: ");
                    String Address = sc.nextLine();
                    temp.Address = Address;
                    sc.nextLine();
                    break;

                case 7:
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
                        temp.MaritalStatus= "UnMarried";
                    else if (status == 3)
                        temp.MaritalStatus = "Widow";
                    else
                        System.out.println("Invalid choice!");
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
            head.prev=null;
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
            System.out.println("no staff available ..");
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
            System.out.println("                                                 * * * TEACHER DETAILS * * *                               \n");
            System.out.println("\uF0D8\tPersonal details : ");
            System.out.println("|====================|============|===================|=============|================|=========================================|");
            System.out.println("|        NAME        |   GENDER   |       DOB         |   CONTACT   | MARITAL STATUS |                  EMAIL                  |");
            System.out.println("|====================|============|===================|=============|================|=========================================|");
            System.out.printf("|%-20s|%-12s|%-19s|%-13s|%-13s|%-41s|\n", temp.Name, temp.Gender, temp.DateOfBirth, temp.Contact, temp.MaritalStatus, temp.Email);
            System.out.println("|--------------------|------------|-------------------|-------------|----------------|-----------------------------------------|\n");

            System.out.println("\uF0D8\tEmployment Details: ");
            System.out.println("|====================|===================|===================|=============|");
            System.out.println("|    TEACHER ID      |  SPECIALIZATION   |  DATE OF JOINING  |    SALARY   |");
            System.out.println("|====================|===================|===================|=============|");
            System.out.printf("|%-20s|%-19s|%-19s|%-13s|\n", temp.TeacherID, temp.Specialization, temp.DateOfJoining, temp.Salary);
            System.out.println("|--------------------|-------------------|-------------------|-------------|-------------------|\n");

            System.out.println("\uF0D8\tSkills and Experience: ");
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
                        System.out.printf("|%-35s|%-19s|%-32s|\n","","",certificate);
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
                writer.write("DOB: " + temp.DateOfBirth + "\n");
                writer.write("Date of Joining: " + temp.DateOfJoining + "\n");
                writer.write("Contact: " + temp.Contact + "\n");
                writer.write("Address: " + temp.Address + "\n");
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


    public void readFromFile(String Filename) {
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


}

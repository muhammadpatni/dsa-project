import java.io.*;
import java.time.LocalDate;
import java.util.*;


public class TeacherList {
    int TotalNumberOfTeachers;
    Scanner sc = new Scanner(System.in);
    Teacher head, tail;
//    String Filename = "C:\\Users\\HP\\Desktop\\lab 1\\muhmmad.java\\sms temp\\Teacher.txt";

    TeacherList() {
        head = null;
        tail = null;
        TotalNumberOfTeachers = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

//    public void saveToFile() {
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Filename))) {
//            Teacher temp = head;
//            writer.write("\t \t \t \t \t \t \t \t * * * * TEACHER DETAILS * * * * \n\n");
//            while (temp != null) {
//                writer.write("Teacher ID: " + temp.TeacherID + "\n");
//                writer.write("Name: " + temp.Name + "\n");
//                writer.write("Gender: " + temp.Gender + "\n");
//                writer.write("DOB: " + temp.DateOfBirth + "\n");
//                writer.write("Marital Status: " + temp.MaritalStatus + "\n");
//                writer.write("Email: " + temp.Email + "\n");
//                writer.write("Specialization: " + temp.Specialization + "\n");
//                writer.write("Highest Qualification: " + temp.HigestQualification + "\n");
//                writer.write("Contact: " + temp.Contact + "\n");
//                writer.write("Address: " + temp.Address + "\n");
//                writer.write("Designation: " + temp.Designation + "\n");
//                writer.write("Salary: " + temp.Salary + "\n");
//                writer.write("Skills: " + temp.Skills + "\n");
//                writer.write("Experience: " + temp.Experience + "\n");
//                writer.write("Certifications:\n");
//                if (!temp.Certifications.noCertificate()) {
//                    for (int i = 0; i < temp.Certifications.size(); i++) {
//                        String certification = temp.Certifications.GetCertificate(i);
//                        writer.write("\u2022 " + certification + "\n");
//                    }
//                }
//                writer.write("\n\n");
//                temp = temp.next;
//            }
//        } catch (IOException e) {
//            System.out.println("Error saving teacher details");
//        }
//    }

    // Add Teacher method with 14 parameters
    public void addTeacher(String Name, String Gender, String DateOfBirth, String MaritalStatus, String Email,
                           String Specialization, String HigestQualification, String Contact, String Address,
                           String Designation,  double Salary, String Skills, String Experience,
                           String[] Certificates) {
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
        newNode.Designation = Designation;
        newNode.DateOfJoining = LocalDate.now().toString();
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


    // Method to display all teachers
    public void displayAllTeachers() {
        if (isEmpty()) {
            System.out.println("No teachers found.");
            return;
        }
        Teacher temp = head;
        while (temp != null) {
            System.out.println("Teacher ID: " + temp.TeacherID);
            System.out.println("Name: " + temp.Name);
            System.out.println("Gender: " + temp.Gender);
            System.out.println("DOB: " + temp.DateOfBirth);
            System.out.println("Marital Status: " + temp.MaritalStatus);
            System.out.println("Email: " + temp.Email);
            System.out.println("Specialization: " + temp.Specialization);
            System.out.println("Highest Qualification: " + temp.HigestQualification);
            System.out.println("Contact: " + temp.Contact);
            System.out.println("Address: " + temp.Address);
            System.out.println("Designation: " + temp.Designation);
            System.out.println("Date of Joining: " + temp.DateOfJoining);
            System.out.println("Salary: " + temp.Salary);
            System.out.println("Skills: " + temp.Skills);
            System.out.println("Experience: " + temp.Experience);
            System.out.println("Certifications: ");
            if (!temp.Certifications.noCertificate()) {
                for (int i = 0; i < temp.Certifications.size(); i++) {
                    System.out.println("\u2022 " + temp.Certifications.GetCertificate(i));
                }
            }
            System.out.println();
            temp = temp.next;
        }
    }

    // Method to read teacher data from file (implementation missing in the original code)
//    public void readFromFile() {
//        try (BufferedReader reader = new BufferedReader(new FileReader(Filename))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                // Parsing and processing the data to create Teacher objects
//                // You need to implement this part based on the file format
//            }
//        } catch (IOException e) {
//            System.out.println("Error reading from file");
//        }
//    }

    // Method to search for a teacher by ID
    public void searchTeacher(int TeacherID) {
        if (isEmpty()) {
            System.out.println("No teacher is found");
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
            System.out.println("Teacher Details Found");
            // Print teacher details here...
        }
    }
}

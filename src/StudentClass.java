import java.io.IOException;


    import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

    public class StudentClass {
        private int totalNumberOfStudents;
        private StudentNode head, tail;
        Scanner sc = new Scanner(System.in);

        public StudentClass() {
            head = null;
            tail = null;
            totalNumberOfStudents = 0;
        }

        public boolean isEmpty() {
            return head == null;
        }

        private int calculateAge(String dob) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate birthDate = LocalDate.parse(dob, formatter);
            LocalDate currentDate = LocalDate.now();
            return Period.between(birthDate, currentDate).getYears();
        }

        public void addStudent(int currentClass, String name, String fatherName, String gender, String dob, String contact, String address) {
            totalNumberOfStudents++;
            StudentNode newNode = new StudentNode();
            newNode.StudentId = totalNumberOfStudents;
            newNode.CurrentClass = currentClass;
            newNode.Name = name;
            newNode.FatherName = fatherName;
            newNode.Gender = gender;
            newNode.DateOfBirth = dob;
            newNode.Contact = contact;
            newNode.Address = address;
            newNode.Section = assignSection(countStudentsInClass(currentClass));
            if(newNode.CurrentClass > 1){
                newNode.Previous = new PreviousAcademicBackground();
                System.out.println("Previous Institute: ");
                newNode.Previous.PreviousInstitute = sc.nextLine();
                System.out.println("Previous Class: ");
                newNode.Previous.ClassName= sc.nextInt();
                System.out.println("Previous Grade: ");
                newNode.Previous.Grade=sc.next().charAt(0);
            }else{
                newNode.Previous =null;
            }

            if (isEmpty()) {
                head = newNode;
            } else {
                tail.next = newNode;
                newNode.prev = tail;
            }
            tail = newNode;
        }
        public void updateStudent(int StudentId) {
            if (isEmpty()) {
                System.out.println("no staff is found");
                return;
            }
            StudentNode temp = head;
            while (temp != null) {
                if (temp.StudentId == StudentId)
                    break;
                temp = temp.next;
            }
            if (temp == null) {
                System.out.println("Invalid ID");
                return;
            }
            System.out.println("Current Details :");
            System.out.println("Name : " + temp.Name);
            System.out.println("Father Name : " + temp.FatherName);
            System.out.println("Contact : " + temp.Contact);
            System.out.println("Dob : " + temp.DateOfBirth);
            System.out.println("Address : " + temp.Address+"\n\n");
            while (true) {
                System.out.println("1. Name");
                System.out.println("2. Father Name");
                System.out.println("3. Contact");
                System.out.println("4. Dob");
                System.out.println("5. Address");
                System.out.println("0. Exit");
                System.out.print("Select option which you want to update : ");
                int choice = 0;
                boolean valid = false;

                while (!valid) {
                    try {
                        System.out.print("Enter your choice (1-5): ");
                        choice = sc.nextInt();
                        if (choice < 1 || choice > 6) { // Invalid choice range check
                            throw new Exception("Invalid choice range!");
                        }
                        valid = true; // Input is valid, exit loop
                    } catch (Exception e) {
                        sc.nextLine(); // Clear invalid input from scanner
                        System.out.println("Wrong input! Enter again from 1-5 options.");
                    }
                }

                System.out.println("You selected: " + choice);
                switch (choice) {
                    case 1:  // Name
                        String name = "";
                        while (true) {
                            try {
                                System.out.print("Enter new name: ");
                                name = sc.nextLine();
                                if (name.trim().isEmpty()) {
                                    throw new Exception("Name cannot be empty!");
                                }
                                temp.Name = name;
                                break;
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        break;

                    case 2:  // Father Name
                        String fathername = "";
                        while (true) {
                            try {
                                System.out.print("Enter new Father Name: ");
                                fathername = sc.nextLine();
                                if (fathername.trim().isEmpty()) {
                                    throw new Exception("Father Name cannot be empty!");
                                }
                                temp.FatherName = fathername;
                                break;
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        break;

                    case 3:  // Contact
                        String contact = "";
                        while (true) {
                            try {
                                System.out.print("Enter new contact: ");
                                contact = sc.nextLine();
                                // Example validation: Ensure the contact is numeric and 10 digits long
                                if (!contact.matches("\\d{10}")) {
                                    throw new Exception("Invalid contact number! Please enter exactly 10 digits.");
                                }
                                temp.Contact = contact;
                                break;
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        break;
                    case 4:
                        String dob = "";
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                        while (true) {
                            System.out.print("Enter new Dob (dd-MM-yyyy): ");
                            String input = sc.nextLine();
                            try {
                                LocalDate birthDate = LocalDate.parse(input, formatter);
                                dob = input;
                                break;
                            } catch (DateTimeParseException e) {
                                System.out.println("Invalid date format. Please enter again in 'dd-MM-yyyy' format.");
                            }
                        }
                        temp.DateOfBirth=dob;
                        break;
                    case 5:
                        System.out.print("Enter new address :");
                        String address=sc.nextLine();
                        temp.Address=address;
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Invalid choice , please choose correct again ");

                }
            }

        }

        public void searchStudent(int StudentId) {
            if (isEmpty()) {
                System.out.println("No Student is found");
                return;
            }
            StudentNode temp = head;
            while (temp != null) {
                if (temp.StudentId == StudentId)
                    break;
                temp = temp.next;
            }
            if (temp == null) {
                System.out.println("Invalid ID");
                return;
            } else {
                System.out.println("                                                 * * * Student DETAILS * * *                               \n");
                System.out.println("\uF0D8\tPersonal details : ");
                System.out.println("|====================|====================|============|===================|=============|=========================================|");
                System.out.println("|        NAME        |    FATHER NAME     |   GENDER   |       DOB         |   CONTACT   |                  ADDRESS                |");
                System.out.println("|====================|====================|============|===================|=============|=========================================|");
                System.out.printf("|%-20s|%-20s|%-12S|%-19s|%-13s|%-41s|\n", temp.Name,temp.FatherName, temp.Gender, temp.DateOfBirth, temp.Contact, temp.Address);
                System.out.println("|--------------------|--------------------|------------|-------------------|-------------|-----------------------------------------|\n");

                System.out.println("\uF0D8\tAcademic Details:");
                System.out.println("|====================|===================|=============|");
                System.out.println("|    STUDENT ID      |   CURRENT CLASS   |   SECTION   |");
                System.out.println("|====================|===================|=============|");
                System.out.printf("|%-20d|%-19d|%-13c|\n", temp.StudentId, temp.CurrentClass, temp.Section);
                System.out.println("|--------------------|-------------------|-------------|\n");

                if(temp.Previous != null) {
                    System.out.println("\uF0D8\tPrevious Academic Background: ");
                    System.out.println("|===================================|===================|===================|");
                    System.out.println("|      PREVIOUS INSTITUTE NAME      |    CLASS NAME     |      GRADE        |");
                    System.out.println("|===================================|===================|===================|");
                    System.out.printf("|%-35s|%-19s|%-19s|\n", temp.Previous.PreviousInstitute, temp.Previous.ClassName, temp.Previous.Grade);
                    System.out.println("|-----------------------------------|-------------------|-------------------|\n");
                }

            }
        }

        public int countStudentsInClass(int currentClass) {
            int count = 0;
            StudentNode current = head;
            while (current != null) {
                if (current.CurrentClass == currentClass) {
                    count++;
                }
                current = current.next;
            }
            return count;
        }

        private char assignSection(int count) {
            if (count < 51) {
                return 'A';
            } else if (count < 101) {
                return 'B';
            } else if (count < 151) {
                return 'C';
            } else if (count <= 200) {
                return 'D';
            } else {
                return '-';
            }
        }

        public void Displaybyclass(int currentClass) {
            if (isEmpty()) {
                System.out.println("No students found.");
                return;
            }

            boolean found = false;
            StudentNode temp = head;

            System.out.println("\nStudents in Class " + currentClass + ":");
            System.out.println("|====================|====================|===================|=============|");
            System.out.println("|    STUDENT ID      |        NAME        |   CURRENT CLASS   |   SECTION   |");
            System.out.println("|====================|====================|===================|=============|");

            while (temp != null) {
                if (temp.CurrentClass == currentClass) {
                    found = true;
                    System.out.printf("|%-20d|%-20s|%-19d|%-13c|\n",
                            temp.StudentId, temp.Name, temp.CurrentClass, temp.Section);
                    System.out.println("|--------------------|--------------------|-------------------|-------------|");
                }
                temp = temp.next;
            }

            if (!found) {
                System.out.println("|      ------        |        ----        |       -----       |     ---     |");
                System.out.println("|--------------------|--------------------|-------------------|-------------|");
                System.out.println("No students found in class " + currentClass + ".");
            }
        }


        public void removeStudent(int StudentId)
        {
            if(isEmpty())
            {
                System.out.println("No Student is found");
                return;
            }
            StudentNode temp=head;
            while(temp!=null){
                if (temp.StudentId==StudentId)
                {
                    break;
                }
                temp=temp.next;
            }
            if (temp==null)
            {
                System.out.println("Invalid found");
                return;
            }
            if (temp==head)
            {   head=temp.next;
                head.prev=null;
                return;
            }
            if (temp==tail)
            {
                tail=temp.prev;
                tail.next=null;
                return;
            }
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
        }

        public void displayAllStudents() {
            System.out.println("                                                 * * * STUDENT DETAILS * * *                               ");
            System.out.println("|==========|====================|====================|==========|========|===================|===================|==========|========================================|");
            System.out.println("|    ID    |        NAME        |   FatherName       |  Gender  |  Age   |      CONTACT      |    CurrentClass   |  Section |                 ADDRESS                |");
            System.out.println("|==========|====================|====================|==========|========|===================|===================|==========|========================================|");

            if (isEmpty()) {
                System.out.println("|  ----    |       ------       |     -------        |  ------  |  ---   |       -------     |      ------       |   ----   |                 ------                 |");
                System.out.println("|----------|--------------------|--------------------|----------|--------|-------------------|-------------------|----------|----------------------------------------|");
                return;
            }

            StudentNode temp = head;
            while (temp != null) {
                int age = calculateAge(temp.DateOfBirth);
                System.out.printf("|%-10s|%-20s|%-20s|%-10s|%-8d|%-19s|%-19d|%-10s|%-40s|\n",
                        temp.StudentId, temp.Name, temp.FatherName, temp.Gender, age, temp.Contact, temp.CurrentClass, temp.Section, temp.Address);
                System.out.println("|----------|--------------------|--------------------|----------|--------|-------------------|-------------------|----------|----------------------------------------|");

                temp = temp.next;
            }
        }
        String Filename="D:\\DSA Student Part\\file handling.txt";

        public void saveToFile() {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(Filename))) {
                StudentNode temp = head;
                writer.write("******** STUDENT DETAILS ********\n\n");

                while (temp != null) {
                    writer.write("Student Id: " + temp.StudentId + "\n");
                    writer.write("Name: " + temp.Name + "\n");
                    writer.write("Father Name: " + temp.FatherName + "\n");
                    writer.write("Gender: " + temp.Gender + "\n");
                    writer.write("Date of Birth: " + temp.DateOfBirth + "\n");
                    writer.write("Contact: " + temp.Contact + "\n");
                    writer.write("Address: " + temp.Address + "\n");
                    writer.write("Current Class: " + temp.CurrentClass + "\n");
                    writer.write("Section: " + temp.Section + "\n");

                    if (temp.Previous != null) {
                        writer.write("Previous Institute: " + temp.Previous.PreviousInstitute + "\n");
                        writer.write("Previous Class: " + temp.Previous.ClassName + "\n");
                        writer.write("Previous Grade: " + temp.Previous.Grade + "\n");
                    }

                    writer.write("\n\n");
                    temp = temp.next;
                }
            } catch (IOException e) {
                System.out.println("Error saving student details: " + e.getMessage());
            }
        }
        public  void uploadMarks(int id)
        {
            StudentNode temp=head;
            while(temp!=null){
                if (temp.StudentId==id)
                {
                    break;
                }
                temp=temp.next;
            }
            if (temp==null)
            {
                System.out.println("Invalid found");
                return;
            }

        }


    }


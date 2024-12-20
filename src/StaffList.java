import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Period;
import java.io.*;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public  class StaffList{
    int StaffIds;
    Scanner sc= new Scanner(System.in);
    Staff head,tail;
    String Filename="C:\\Users\\HP\\Desktop\\lab 1\\DSA project\\Staff.txt";
    StaffList()
    {
        head = null;
        tail = null;
        StaffIds =0;
    }
    public boolean isEmpty()
    {
        return head == null;
    }

    public void saveToFile()
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Filename))) {
            Staff temp = head;
            writer.write("\t \t \t \t \t \t \t \t * * * * STAFF DETAILS * * * * \n\n");
            while (temp != null) {
                writer.write("Staff Id :"+temp.StaffId+"\n");
                writer.write("Name :"+temp.Name+"\n");
                writer.write("Gender :"+temp.Gender +"\n");
                writer.write("Dob :"+temp.DateOfBirth+"\n");
                writer.write("Date of joining :"+temp.DateOfJoining+"\n");
                writer.write("Contact :"+temp.Contact+"\n");
                writer.write("Address :"+temp.Address+"\n");
                writer.write("Designation :"+temp.Designation+"\n");
                writer.write("Salary :"+temp.Salary+"\n");
                writer.write("Skill :"+temp.Skills +"\n");
                writer.write("Experience :"+temp.Experience+"\n");
                writer.write("Certification:\n");
                if (!temp.Certifications.noCertificate()) {
                    for (int i = 0; i < temp.Certifications.size(); i++) {
                        String certification = temp.Certifications.GetCertificate(i);
                        writer.write("• " + certification+"\n");
                    }
                }
                writer.write("\n\n");
                temp = temp.next;
            }
        } catch (IOException e) {
            System.out.println("Error saving staff details");
        }
    }
    public int calculateAge(String dob) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate birthDate = LocalDate.parse(dob, formatter);
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(birthDate, currentDate).getYears();
        return age;
    }
    public void addStaff(String Name, String Gender, String DateOfBirth, String Contact, String Address, String Designation, double Salary, String Skills,String Experience,String[] Certificates,String dateofjoining) {
        StaffIds++;
        Staff newNode = new Staff();
        newNode.Certifications= new Array();
        newNode.StaffId = StaffIds;
        newNode.Name = Name;
        newNode.Gender = Gender;
        newNode.DateOfBirth = DateOfBirth;
        newNode.Contact = Contact;
        newNode.Address = Address;
        newNode.Designation = Designation;
        newNode.DateOfJoining = dateofjoining;
        newNode.Salary = Salary;
        newNode.Skills = Skills;
        newNode.Experience = Experience;
        for (String certification : Certificates) {
            newNode.Certifications.insert(certification);
        }
        if (isEmpty())
            head = newNode;
        else {
            tail.next = newNode;}
        newNode.prev = tail;
        tail = newNode;
    }
    public void removeStaff(int StaffId)
    {
        if(isEmpty())
        {
            System.out.println("no staff available ..");
            return;
        }
        Staff temp=head;
        while(temp!=null){
            if (temp.StaffId==StaffId)
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
    public void searchStaff(int StaffId) {
        if (isEmpty()) {
            System.out.println("no staff available ..");
            return;
        }
        Staff temp = head;
        while (temp != null) {
            if (temp.StaffId == StaffId)
                break;
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("Invalid ID");
            return;
        } else {
            System.out.println("                                                 * * * STAFF DETAILS * * *                               \n");
            System.out.println("\uF0D8\tPersonal details : ");
            System.out.println("|====================|============|===================|=============|=============|=========================================|");
            System.out.println("|        NAME        |   GENDER   |       DOB         |   CONTACT   |    SALARY   |                  ADDRESS                |");
            System.out.println("|====================|============|===================|=============|=============|=========================================|");
            System.out.printf("|%-20s|%-12s|%-19s|%-13s|%-13s|%-41s|\n", temp.Name, temp.Gender, temp.DateOfBirth, temp.Contact, temp.Salary, temp.Address);
            System.out.println("|--------------------|------------|-------------------|-------------|-------------|-----------------------------------------|\n");

            System.out.println("\uF0D8\tEmployment Details:: ");
            System.out.println("|====================|===================|===================|=============|");
            System.out.println("|      STAFF ID      |    DESIGNATION    |  DATE OF JOINING  |    SALARY   |");
            System.out.println("|====================|===================|===================|=============|");
            System.out.printf("|%-20s|%-19s|%-19s|%-13s|\n", temp.StaffId, temp.Designation, temp.DateOfJoining, temp.Salary);
            System.out.println("|--------------------|-------------------|-------------------|-------------|\n");

            System.out.println("\uF0D8\tSkills and Experience: ");
            System.out.println("|===================================|===================|================================|");
            System.out.println("|              Skills               |    experience     |         Certifications         |");
            System.out.println("|===================================|===================|================================|");
            System.out.printf("|%-35s|%-19s|", temp.Skills, temp.Experience);
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
            System.out.println("|-----------------------------------|-------------------|--------------------------------|\n");
        }
    }

    public void updateStudent(int StaffId) {
        if (isEmpty()) {
            System.out.println("no staff available ..");
            return;
        }
        Staff temp = head;
        while (temp != null) {
            if (temp.StaffId == StaffId)
                break;
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("Invalid ID");
            return;
        }
        while (true) {
            System.out.println("\nCurrent Details :");
            System.out.println("Name : " + temp.Name);
            System.out.println("Gender : " + temp.Gender);
            System.out.println("Contact : " + temp.Contact);
            System.out.println("Address : " + temp.Address);
            System.out.println("Dob : " + temp.DateOfBirth);
            System.out.println("Designation : " + temp.Designation);
            System.out.println("Salary : " + temp.Salary + "\n\n");

            System.out.println("1. Name");
            System.out.println("2. Gender");
            System.out.println("3. Contact");
            System.out.println("4. Address");
            System.out.println("5. Dob");
            System.out.println("6. Salary");
            System.out.println("7. Designation");
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
                    System.out.print("Enter new address: ");
                    sc.nextLine();
                    String address = sc.nextLine();
                    temp.Address = address;
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
                    System.out.print("Enter new salary: ");
                    int salary = sc.nextInt();
                    temp.Salary = salary;
                    sc.nextLine();
                    break;

                case 7:
                    System.out.print("Enter new designation: ");
                    sc.nextLine();
                    String designation = sc.nextLine();
                    temp.Designation = designation;
                    break;

                case 0:
                    System.out.println("Staff updated successfully . . . ");
                    saveToFile();
                    return;

                default:
                    System.out.println("Invalid choice, please choose again.");
            }

        }

    }
    public void displayAllStaff()
    {
        Staff temp=head;
        System.out.println("                                                 * * * STAFF DETAILS * * *                               ");
        System.out.println("|==========|====================|===========|=============================|===================|=============|========|========================================|");
        System.out.println("|    ID    |        NAME        |   GENDER  |        DESIGNATION          |      CONTACT      |    SALARY   |  Age   |                 ADDRESS                |");
        System.out.println("|==========|====================|===========|=============================|===================|=============|========|========================================|");
        if (isEmpty()) {

            System.out.println("|  - - - - |       - - - -      |  - - - -  |           - - - -           |      - - - -      |   - - - -   | - - -  |               - - - -                  |");
            System.out.println("|----------|--------------------|-----------|-----------------------------|-------------------|-------------|--------|----------------------------------------|");

            return;
        }
        while(temp!=null){
            int Age=calculateAge(temp.DateOfBirth);
            System.out.printf("|%-10s|%-20s|%-11s|%-29s|%-19s|%-13s|%-8s|%-40s|\n",temp.StaffId,temp.Name,temp.Gender,temp.Designation,temp.Contact,temp.Salary,Age,temp.Address);
            System.out.println("|----------|--------------------|-----------|-----------------------------|-------------------|-------------|--------|----------------------------------------|");
            temp=temp.next;
        }
    }
    public void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(Filename))) {
            String line;

            // Initialize variables to store staff details directly
            int staffId = 0;
            String name = "", gender = "", dob = "", contact = "", address = "", designation = "", skills = "", experience = "",dateofjoining="";
            double salary = 0.0;
            Array certifications = new Array();

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Staff Id :")) {

                    // Add the previous staff details to the list before starting a new record
                    if (staffId != 0) {
                        this.StaffIds=staffId-1;
                        addStaff(name, gender, dob, contact, address, designation, salary, skills, experience, certifications.giveCurrentArray(),dateofjoining);

                    }

                    // Start a new staff
                    staffId = Integer.parseInt(line.split(":")[1].trim());
                    certifications.makeArrayEmpty(); // Reset certifications list using makeArrayEmpty
                    name = gender = dob = contact = address = designation = skills = experience =dateofjoining= "";
                    salary = 0.0;

                } else if (line.startsWith("Name :")) {
                    name = line.split(":")[1].trim();

                } else if (line.startsWith("Gender :")) {
                    gender = line.split(":")[1].trim();

                } else if (line.startsWith("Dob :")) {
                    dob = line.split(":")[1].trim();

                } else if (line.startsWith("Contact :")) {
                    contact = line.split(":")[1].trim();

                } else if (line.startsWith("Address :")) {
                    address = line.split(":")[1].trim();

                } else if (line.startsWith("Designation :")) {
                    designation = line.split(":")[1].trim();

                } else if (line.startsWith("Salary :")) {
                    salary = Double.parseDouble(line.split(":")[1].trim());

                } else if (line.startsWith("Skill :")) {
                    skills = line.split(":")[1].trim();

                } else if (line.startsWith("Experience :")) {
                    experience = line.split(":")[1].trim();

                } else if (line.startsWith("\u2022 ")) { // Unicode for bullet point (•)
                    certifications.insert(line.substring(2).trim());
                }

            }

            // Add the last staff record to the list
            if (staffId != 0) {
                this.StaffIds=staffId-1;
                addStaff(name, gender, dob, contact, address, designation, salary, skills, experience, certifications.giveCurrentArray(),dateofjoining);
            }

        } catch (IOException e) {
            System.out.println("Error reading staff details from file");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred while reading staff details" +e);
        }
    }

}

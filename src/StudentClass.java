import java.io.IOException;


    import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
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
            if (newNode.CurrentClass > 1) {
                newNode.Previous = new PreviousAcademicBackground();
                System.out.println("Previous Institute: ");
                newNode.Previous.PreviousInstitute = sc.nextLine();
                System.out.println("Previous Class: ");
                newNode.Previous.ClassName = sc.nextInt();
                System.out.println("Previous Grade: ");
                newNode.Previous.Grade = sc.next().charAt(0);
            } else {
                newNode.Previous = null;
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
            System.out.println("Address : " + temp.Address + "\n\n");
            while (true) {
                System.out.println("1. Name");
                System.out.println("2. Father Name");
                System.out.println("3. Contact");
                System.out.println("4. Dob");
                System.out.println("5. Address");
                System.out.println("0. Exit");
                System.out.print("Select option which you want to update : ");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        System.out.print("Enter new name :");
                        String name = sc.nextLine();
                        temp.Name = name;
                        break;
                    case 2:
                        System.out.print("Enter new Father Name :");
                        String fathername = sc.nextLine();
                        temp.FatherName = fathername;
                        break;
                    case 3:
                        System.out.print("Enter new contact :");
                        String contact = sc.nextLine();
                        temp.Contact = contact;
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
                        temp.DateOfBirth = dob;
                        break;
                    case 5:
                        System.out.print("Enter new address :");
                        String address = sc.nextLine();
                        temp.Address = address;
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
                System.out.printf("|%-20s|%-20s|%-12S|%-19s|%-13s|%-41s|\n", temp.Name, temp.FatherName, temp.Gender, temp.DateOfBirth, temp.Contact, temp.Address);
                System.out.println("|--------------------|--------------------|------------|-------------------|-------------|-----------------------------------------|\n");

                System.out.println("\uF0D8\tAcademic Details:");
                System.out.println("|====================|===================|=============|");
                System.out.println("|    STUDENT ID      |   CURRENT CLASS   |   SECTION   |");
                System.out.println("|====================|===================|=============|");
                System.out.printf("|%-20d|%-19d|%-13c|\n", temp.StudentId, temp.CurrentClass, temp.Section);
                System.out.println("|--------------------|-------------------|-------------|\n");

                if (temp.Previous != null) {
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


        public void removeStudent(int StudentId) {
            if (isEmpty()) {
                System.out.println("No Student is found");
                return;
            }
            StudentNode temp = head;
            while (temp != null) {
                if (temp.StudentId == StudentId) {
                    break;
                }
                temp = temp.next;
            }
            if (temp == null) {
                System.out.println("Invalid found");
                return;
            }
            if (temp == head) {
                head = temp.next;
                head.prev = null;
                return;
            }
            if (temp == tail) {
                tail = temp.prev;
                tail.next = null;
                return;
            }
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
        }

        public void displayAllStudents() {
            System.out.println("                                                   * * * STUDENT DETAILS * * *                               ");
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

        String Filename = "D:\\DSA Student Part\\file handling.txt";

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

        public StudentNode uploadMarks(int id) {
            StudentNode temp = head;
            while (temp != null) {
                if (temp.StudentId == id) {
                    break;
                }
                temp = temp.next;
            }
            if (temp == null) {
                return null;
            }
            return temp;
        }

        public void PaidFee(int Studentid,int voucherid ) {
            StudentNode temp = head;
            String month = temp.fee1.getMonthNameFromDate(LocalDate.now());
            while (temp != null) {
                if (temp.StudentId == Studentid) {
                    break;
                }
                temp = temp.next;
            }

            if (temp == null) {
                System.out.println("Invalid ID");
                return;
            }
            if (temp.fee1.searchMonth(month)) {
                System.out.println("Fee for this month has already been recorded.");
                return;
            }
            temp.fee1.addStudentFee(voucherid,month);
        }

        public void generateFeeVouchers(String month) {
            StudentNode temp = head;

            if (temp == null) {
                System.out.println("No students found.");
                return;
            }

            while (temp != null) {
                int voucherId = temp.fee.VoucherNo;
                String studentName = temp.Name;
                String fatherName = temp.FatherName;
                double feeAmount;
                LocalDate issueDate = LocalDate.now();
                LocalDate dueDate = issueDate.plusDays(15);

                // Fee structure based on class groups
                if (temp.CurrentClass >= 1 && temp.CurrentClass <= 4) {
                    feeAmount = 5000.0;
                } else if (temp.CurrentClass >= 5 && temp.CurrentClass <= 7) {
                    feeAmount = 7000.0;
                } else if (temp.CurrentClass >= 8 && temp.CurrentClass <= 10) {
                    feeAmount = 14000.0;
                } else {
                    System.out.println("Class not recognized for Student ID: " + temp.StudentId);
                    temp = temp.next;
                    continue;
                }

                // Enhanced Fee Voucher Output
                System.out.println("════════════════════════════════════════════════════════════════════════════════════════");
                System.out.println("                           ABC SCHOOL OF EXCELLENCE                                     ");
                System.out.println("════════════════════════════════════════════════════════════════════════════════════════");
                System.out.println("                   OFFICIAL FEE PAYMENT VOUCHER                                          ");
                System.out.println("════════════════════════════════════════════════════════════════════════════════════════");
                System.out.println(" ");

                // Student Info Section
                System.out.println("╔════════════════════════════════════════════════════════════════════════════════════╗");
                System.out.println("║                                STUDENT DETAILS                                      ║");
                System.out.println("╠════════════════════════════════════════════════════════════════════════════════════╣");
                 System.out.printf("║ Voucher ID            : %-35d ║\n", voucherId);
                 System.out.printf("║ Student ID            : %-35d ║\n", temp.StudentId);
                 System.out.printf("║ Student Name          : %-35s ║\n", studentName);
                 System.out.printf("║ Father's Name         : %-35s ║\n", fatherName);
                 System.out.printf("║ Class                 : %-35d ║\n", temp.CurrentClass);
                System.out.println("╚════════════════════════════════════════════════════════════════════════════════════╝");

                // Fee Information Section
                System.out.println("╔════════════════════════════════════════════════════════════════════════════════════╗");
                System.out.println("║                               FEE INFORMATION                                      ║");
                System.out.println("╠════════════════════════════════════════════════════════════════════════════════════╣");
                 System.out.printf("║ Fee Amount            : Rs. %-28.2f ║\n", feeAmount);
                 System.out.printf("║ Issue Date            : %-35s ║\n", issueDate);
                 System.out.printf("║ Due Date              : %-35s ║\n", dueDate);
                 System.out.printf("║ Billing Month         : %-35s ║\n", month);
                System.out.println("╚════════════════════════════════════════════════════════════════════════════════════╝");

                // Important Notes Section
                System.out.println("╔════════════════════════════════════════════════════════════════════════════════════╗");
                System.out.println("║                              IMPORTANT NOTES                                       ║");
                System.out.println("╠════════════════════════════════════════════════════════════════════════════════════╣");
                System.out.println("║ 1. Fee must be paid before the due date to avoid a fine.                           ║");
                System.out.println("║ 2. Late payments incur an additional charge of Rs. 200 per day.                   ║");
                System.out.println("║ 3. Fees can be paid via bank transfer, cheque, or directly at the school office.   ║");
                System.out.println("╚════════════════════════════════════════════════════════════════════════════════════╝");

                // Bank Stamp / Principal's Signature Section
                System.out.println("╔════════════════════════════════════════════════════════════════════════════════════╗");
                System.out.println("║                        BANK STAMP / PRINCIPAL SIGNATURE                            ║");
                System.out.println("╠════════════════════════════════════════════════════════════════════════════════════╣");
                System.out.println("║                               [BANK LOGO]                                          ║");
                System.out.println("║                            ABC BANK LIMITED (STAMP)                                ║");
                System.out.println("║                            _______________________                                 ║");
                System.out.println("║                           Signature: _______________________                       ║");
                System.out.println("║                           Date: ________________________                           ║");
                System.out.println("╚════════════════════════════════════════════════════════════════════════════════════╝");

                // Footer Section
                System.out.println("════════════════════════════════════════════════════════════════════════════════════");
                System.out.println("                                 SCHOOL CONTACT DETAILS                               ");
                System.out.println("                       Address: 123 School Road, City Name, Zip Code           ");
                System.out.println("                       Phone: (123) 456-7890 | Email: info@abcschool.com      ");
                System.out.println("════════════════════════════════════════════════════════════════════════════════════");
                System.out.println("                                     END OF FEE VOUCHER                                               ");
                System.out.println("════════════════════════════════════════════════════════════════════════════════════");
                System.out.println();

                // Move to the next student
                temp = temp.next;
            }
        }

    }


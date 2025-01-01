import java.io.IOException;
import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

    public class StudentClass {
        public int totalNumberOfStudents;
        public StudentNode head, tail;
        int VoucherNo=0;
        Scanner sc = new Scanner(System.in);
        String Filename = "C:\\Users\\HP\\Desktop\\lab 1\\DSA project\\student.txt";
//  String Filename ="C:\\Users\\Admin\\Desktop\\dsa-project1\\student.txt";

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

            if (head == null) {
                head = tail = newNode;
            } else {
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode ;
            }
        }

        public void updateStudent(int StudentId) {
            if (isEmpty()) {
              StyledConsoleOutput.printStyled("\nNo student available",false,false,"red");
                return;
            }
            StudentNode temp = head;
            while (temp != null) {
                if (temp.StudentId == StudentId)
                    break;
                temp = temp.next;
            }
            if (temp == null) {
                StyledConsoleOutput.printStyled("\nInvalid ID",false,false,"red");
                return;
            }
            System.out.println("Current Details :");
            System.out.println("Name : " + temp.Name);
            System.out.println("Father Name : " + temp.FatherName);
            System.out.println("Contact : " + temp.Contact);
            System.out.println("Dob : " + temp.DateOfBirth);
            System.out.println("Address : " + temp.Address + "\n\n");
            while (true) {
                int choice = 0;
                while (true) {
                    try {
                        System.out.println("1. Name");
                        System.out.println("2. Father Name");
                        System.out.println("3. Contact");
                        System.out.println("4. Dob");
                        System.out.println("5. Address");
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
                        saveToFile();
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
                StyledConsoleOutput.printStyled("                                                 * * * Student DETAILS * * *                               \n",true, false, "cyan");
                StyledConsoleOutput.printStyled("\uF0D8\tPersonal details : ",true,false,"green");
                System.out.println("|====================|====================|============|===================|=============|=========================================|");
                System.out.println("|        NAME        |    FATHER NAME     |   GENDER   |       DOB         |   CONTACT   |                  ADDRESS                |");
                System.out.println("|====================|====================|============|===================|=============|=========================================|");
                System.out.printf("|%-20s|%-20s|%-12S|%-19s|%-13s|%-41s|\n", temp.Name, temp.FatherName, temp.Gender, temp.DateOfBirth, temp.Contact, temp.Address);
                System.out.println("|--------------------|--------------------|------------|-------------------|-------------|-----------------------------------------|\n");

                StyledConsoleOutput.printStyled("\uF0D8\tAcademic Details:",true,false,"green");
                System.out.println("|====================|===================|=============|");
                System.out.println("|    STUDENT ID      |   CURRENT CLASS   |   SECTION   |");
                System.out.println("|====================|===================|=============|");
                System.out.printf("|%-20d|%-19d|%-13c|\n", temp.StudentId, temp.CurrentClass, temp.Section);
                System.out.println("|--------------------|-------------------|-------------|\n");

                if (temp.Previous != null) {
                    StyledConsoleOutput.printStyled("\uF0D8\tPrevious Academic Background: ",true,false,"green");
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

            StyledConsoleOutput.printStyled("\nStudents in Class " + currentClass + ":",true,false,"cyan");
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
            StyledConsoleOutput.printStyled("                                                   * * * STUDENT DETAILS * * *                               ",true,false,"cyan");
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

        public void saveToFile() {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(Filename))) {
                StudentNode temp = head; // Assuming "this" is the head of the StudentNode list
                writer.write("\t\t\t\t\t* * * * STUDENT DETAILS * * * *\n\n");
                while (temp != null) {
                    writer.write("Student ID: " + temp.StudentId + "\n");
                    writer.write("Name: " + temp.Name + "\n");
                    writer.write("Gender: " + temp.Gender + "\n");
                    writer.write("DOB: " + temp.DateOfBirth + "\n");
                    writer.write("Contact: " + temp.Contact + "\n");
                    writer.write("Address: " + temp.Address + "\n");
                    writer.write("Father's Name: " + temp.FatherName + "\n");
                    writer.write("Current Class: " + temp.CurrentClass + "\n");
                    writer.write("Section: " + temp.Section + "\n");

                    writer.write("Previous Academic Background: \n");
                    if (temp.Previous != null) {
                     writer.write("Previous Institute: "+temp.Previous.PreviousInstitute+"\n");
                     writer.write("Previous Class: "+temp.Previous.ClassName+"\n");
                     writer.write("Previous Grade: "+temp.Previous.Grade+"\n");
                    } else {
                        writer.write("No previous academic background available.\n");
                    }

                    writer.write("Marks List: \n");
                    if (!temp.marks.isEmpty() ) {
                        temp.marks.displayAllMarks();
                    } else {
                        writer.write("No marks available.\n");
                    }

                    writer.write("Fee Details: \n");
                    if (!temp.fee.isEmpty()) {
                          temp.fee.displayFee();
                    } else {
                        writer.write("No fee details available.\n");
                    }

                    writer.write("Attendance: \n");
                    if (!temp.attendance.isEmpty()) {
                        temp.attendance.print();

                    } else {
                        writer.write("No attendance records available.\n");
                    }

                    writer.write("\n\n");
                    temp = temp.next;
                }
            } catch (IOException e) {
                System.out.println("Error saving student details to file.");
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

        public void PaidFee(int Studentid,int voucherid , String month) {
            StudentNode temp = head;
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
            if (temp.fee.searchMonth(month)) {
                System.out.println("Fee for this month has already been recorded.");
                return;
            }
            temp.fee.addStudentFee(voucherid,month);
        }

        public void generateFeeVouchers(String month ,int id) {
           VoucherNo++;
            StudentNode temp = head;
            while (temp != null) {
                if (temp.StudentId==id)
                         break;
                temp = temp.next;
            }
            if (temp==null)
            {
                System.out.println("Invalid Id");
                return;
            }
            if (temp.fee.searchMonth(month))
            {
                System.out.println("Fees is already paid for month "+month);
                return;
            }
            int voucherId = VoucherNo;
            String studentName = temp.Name;
            String fatherName = temp.FatherName;
            double feeAmount = 0;
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
            }

            // Enhanced Fee Voucher Output
            System.out.println("══════════════════════════════════════════════════════════════════════════════════════");
            StyledConsoleOutput.printStyled("                           THE CITY SCHOOL OF EXCELLENCE                                     \n",true,false,"white");
            System.out.println("══════════════════════════════════════════════════════════════════════════════════════");
            System.out.println("                         OFFICIAL FEE PAYMENT VOUCHER                                   ");
            System.out.println("══════════════════════════════════════════════════════════════════════════════════════");
            System.out.println(" ");

            // Student Info Section
            System.out.println("╔════════════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                                STUDENT DETAILS                                     ║");
            System.out.println("╠════════════════════════════════════════════════════════════════════════════════════╣");
            System.out.printf("║ Voucher ID            : %-59d ║\n", voucherId);
            System.out.printf("║ Student ID            : %-59d ║\n", temp.StudentId);
            System.out.printf("║ Student Name          : %-59s ║\n", studentName);
            System.out.printf("║ Father's Name         : %-59s ║\n", fatherName);
            System.out.printf("║ Class                 : %-59d ║\n", temp.CurrentClass);
            System.out.println("╚════════════════════════════════════════════════════════════════════════════════════╝");

            // Fee Information Section
            System.out.println("╔════════════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                               FEE INFORMATION                                      ║");
            System.out.println("╠════════════════════════════════════════════════════════════════════════════════════╣");
            System.out.printf(" ║ Fee Amount            : Rs. %-248.2f ║\n", feeAmount);
            System.out.printf(" ║ Issue Date            : %-57s ║\n", issueDate);
            System.out.printf(" ║ Due Date              : %-57s ║\n", dueDate);
            System.out.printf(" ║ Billing Month         : %-57s ║\n", month);
            System.out.println("╚════════════════════════════════════════════════════════════════════════════════════╝");

            // Important Notes
            System.out.println("╔════════════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                              IMPORTANT NOTES                                       ║");
            System.out.println("╠════════════════════════════════════════════════════════════════════════════════════╣");
            System.out.println("║ 1. Fee must be paid before the due date to avoid a fine.                           ║");
            System.out.println("║ 2. Late payments incur an additional charge of Rs. 200 per day.                    ║");
            System.out.println("║ 3. Fees can be paid via bank transfer, cheque, or directly at the school office.   ║");
            System.out.println("╚════════════════════════════════════════════════════════════════════════════════════╝");

            System.out.println("╔════════════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                        BANK STAMP / PRINCIPAL SIGNATURE                            ║");
            System.out.println("╠════════════════════════════════════════════════════════════════════════════════════╣");
            System.out.println("║                               [BANK LOGO]                                          ║");
            System.out.println("║                          ABC BANK LIMITED (STAMP)                                  ║");
            System.out.println("║                            _______________________                                 ║");
            System.out.println("║                           Signature: _______________________                       ║");
            System.out.println("║                           Date: ________________________                           ║");
            System.out.println("╚════════════════════════════════════════════════════════════════════════════════════╝");

            // Footer Section
            System.out.println("════════════════════════════════════════════════════════════════════════════════════");
            System.out.println("                                 SCHOOL CONTACT DETAILS                               ");
            System.out.println("                       Address: 123 School Road, City Name, Zip Code                  ");
            System.out.println("                       Phone: (123) 456-7890 | Email: info@abcschool.com              ");
            System.out.println("════════════════════════════════════════════════════════════════════════════════════");
            System.out.println("                                END OF FEE VOUCHER                                  ");
            System.out.println("════════════════════════════════════════════════════════════════════════════════════");
            System.out.println();

        }
        public void GenerateFeeStatusTable(int StudentId) {
            StudentNode temp = head;
            while (temp != null) {
                if (temp.StudentId == StudentId)
                    break;
                temp = temp.next;
            }
            if (temp == null) {
                System.out.println("Invalid Id");
                return;
            }
            StyledConsoleOutput.printStyled("                                    * * * STUDENT FEE DETAILS * * *                               ",true,false,"cyan");
            System.out.println("|==========|====================|====================|===================|===================|===================|");
            System.out.println("|    ID    |        NAME        |       CLASS        |       MONTH       |     VOUCHER ID    |       STATUS      |");
            System.out.println("|==========|====================|====================|===================|===================|===================|");
            String[] months={"January","February","March","April","May","June","July","August","September","October","November","December"};
            for (int i = 0; i < months.length ; i++) {
                if (temp.fee.ReturnVoucherNo(months[i]) != 0) {
                    System.out.printf("|%-10s|%-20s|%-20s|%-19s|%-19s|%-19s|\n",
                            temp.StudentId, temp.Name, temp.CurrentClass,months[i], temp.fee.ReturnVoucherNo(months[i]), "Paid");
                    System.out.println("|----------|--------------------|--------------------|-------------------|-------------------|-------------------|");
                }
                else{
                    System.out.printf("|%-10s|%-20s|%-20s|%-19s|%-19s|%-19s|\n",
                            temp.StudentId, temp.Name, temp.CurrentClass,months[i], "------", "Unpaid");
                    System.out.println("|----------|--------------------|--------------------|-------------------|-------------------|-------------------|");
                }
            }
        }

        public String[] returnMonthsOfUnpaidFee(StudentNode current)
        { String[] temp=new String[12];
            String[] months={"January","February","March","April","May","June","July","August","September","October","November","December"};
            for (int i = 0; i <months.length; i++) {
                if (!current.fee.searchMonth(months[i]))
                {
                   temp[i]=months[i];
                }
            }
            return temp;
        }
     public void displayAllStudentWithUnpaidFee()
     {
         if (isEmpty())
         {
             System.out.println("Students not available");
             return;
         }
         System.out.println("|==========|====================|====================|====================|");
         System.out.println("|    ID    |        NAME        |       CLASS        |       MONTHS       |");
         System.out.println("|==========|====================|====================|====================|");
         StudentNode temp=head;
         while (temp!=null)
         {
             String[] month = returnMonthsOfUnpaidFee(temp);
             System.out.printf("|%-10s|%-20s|%-20s|",temp.StudentId, temp.Name, temp.CurrentClass);
             for (int i = 0; i <month.length; i++) {
                if (FeeClass.getMonthNameFromDate(LocalDate.now())!=month[i]){
                 if (i==0)
                     System.out.printf("%-20s|\n",month[i]);
                 else
                     System.out.printf("|%-10s|%-20s|%-20s|%-20s|\n","","","",month[i]);
             }
             else
                {
                    if (i==0)
                        System.out.printf("%-20s|\n",month[i]);
                    else
                        System.out.printf("|%-10s|%-20s|%-20s|%-20s|\n","","","",month[i]);
                    break;
                }

             }
             System.out.println("|-----------------------------------|-------------------|--------------------------------|\n");
             temp=temp.next;
         }
     }

     public void markAttendanceOfClass (int Class)
     {
         boolean found=false;
         StudentNode temp=head;
         while (temp!=null)
         {
             if (temp.CurrentClass==Class)
             {   found=true;
                 System.out.println("Roll # "+temp.StudentId+" Name "+temp.Name);
                 System.out.println("1 . present");
                 System.out.println("2 . Absent");
                 System.out.print("Mark Attendance : ");
                 int choice =sc.nextInt();  // kamran yahan exception handling karni hai or yeh bi karna hai input 1 ya 2 hi hoo anything else na hoo
                 if (choice==1)
                 {
                     temp.attendance.insert(true);
                 } else if (choice==2) {
                     temp.attendance.insert(true);
                 }
                 System.out.println();
             }
             temp=temp.next;
         }
         if (found)
         {
             System.out.println("no student in class "+Class);
         }
     }

     public void displayMonthAttendanceOfClass(int Class)
     {
         boolean found=true,check=false;
         StyledConsoleOutput.printStyled("                             Last 15 Days Attendance Of Class "+Class+"\n",true,false,"cyam");
         System.out.println("|=======|======================|===|===|===|===|===|===|===|===|===|====|====|====|====|====|====|");
         System.out.println("| ROLL# |         Name         | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | 11 | 12 | 13 | 14 | 15 |");
         System.out.println("|=======|======================|===|===|===|===|===|===|===|===|===|====|====|====|====|====|====|");
         StudentNode temp=head;
         while (temp!=null)
         {
             if (temp.CurrentClass==Class) {
                 found = false;
                 System.out.printf("|%-7s|%-22s",temp.StudentId,temp.Name);
                 if (temp.attendance.size()<15)
                 {
                     for (int i = 0; i <15-temp.attendance.size(); i++) {
                         if (i<10)
                           System.out.print("| A ");
                         else
                             System.out.print("|  A ");

                     }
                     for (int i = 0; i < temp.attendance.size(); i++) {
                         if (temp.attendance.getAttendance(i))
                               System.out.print("| p ");
                        else
                             System.out.print("| A ");
                     }
                     check=true;
                 } 
                 else if(temp.attendance.size()==15)  {
                     for (int i = 0; i < temp.attendance.size(); i++) {
                         if (temp.attendance.getAttendance(i))
                             System.out.print("| p ");
                         else
                             System.out.print("| A ");
                     }
                     check=true;
                 } else if (temp.attendance.size()>15) {
                     for (int i =temp.attendance.size()-15; i < temp.attendance.size(); i++) {
                         if (temp.attendance.getAttendance(i))
                             System.out.print("| p ");
                         else
                             System.out.print("| A ");
                     }
                     check=true;
                 }
                 if (check)
                     System.out.print("|\n");
                 System.out.println("|-------|----------------------|---|---|---|---|---|---|---|---|---|----|----|----|----|----|----|");
             }
             temp=temp.next;
         }
         if (found)
         {
             System.out.println("| ----- | -------------------- | . | . | . | . | . | . | . | . | . | .. | .. | .. | .. | .. | .. |");
             System.out.println("|-------|----------------------|---|---|---|---|---|---|---|---|---|----|----|----|----|----|----|");
         }

     }
        public void retrieveFromFile() {
            try (BufferedReader reader = new BufferedReader(new FileReader(Filename))) {
                String line;

                // Variables to store student details temporarily
                int studentId = 0,currentClass=0;
                String name = "", gender = "", dob = "",father="", contact = "", address = "";
              char section='\0';
                PreviousAcademicBackground previous = null;
                MarksListForStudent marksList = new MarksListForStudent();
                FeeClass feeDetails = new FeeClass();
                BooleanArray attendance = new BooleanArray();

                while ((line = reader.readLine()) != null) {
                    // Detect student details
                    if (line.startsWith("Student ID:")) {
                        if (studentId!= 0) {
                            // Add the previous student to the list
                            addStudentToList(studentId,father, name, gender, dob, contact, address, currentClass, section,
                                    previous, marksList, feeDetails, attendance);
                        }

                        // Reset all variables for the new student
                        studentId = Integer.parseInt(line.split(":")[1].trim());
                        name = gender = dob = contact = address ="";
                        marksList = new MarksListForStudent();  // Reset the Marks list
                        feeDetails = new FeeClass();          // Reset Fee details
                        attendance = new BooleanArray();         // Reset Attendance
                    } else if (line.startsWith("Name:")) {
                        name = line.split(":")[1].trim();
                    }
                    else if (line.startsWith("Father's Name:")) {
                            father = line.split(":")[1].trim();
                    } else if (line.startsWith("Gender:")) {
                        gender = line.split(":")[1].trim();
                    } else if (line.startsWith("DOB:")) {
                        dob = line.split(":")[1].trim();
                    } else if (line.startsWith("Contact:")) {
                        contact = line.split(":")[1].trim();
                    } else if (line.startsWith("Address:")) {
                        address = line.split(":")[1].trim();
                    } else if (line.startsWith("Current Class:")) {
                        currentClass =Integer.parseInt(line.split(":")[1].trim()) ;
                    } else if (line.startsWith("Section:")) {
                        section = line.split(":")[1].trim().charAt(0);
                    } else if (line.startsWith("Previous Institute:")) {
                        if (previous == null) {
                            previous = new PreviousAcademicBackground();
                        }
                        previous.PreviousInstitute = line.split(":")[1].trim();
                    } else if (line.startsWith("Previous Class:")) {
                        if (previous != null) {
                            previous.ClassName =Integer.parseInt(line.split(":")[1].trim());
                        }
                    } else if (line.startsWith("Previous Grade:")) {
                        if (previous != null) {
                            previous.Grade = line.split(":")[1].trim().charAt(0);
                        }
                    } else if (line.startsWith("Marks List:")) {
                        // Read marks until Fee Details
                        while ((line = reader.readLine()) != null && !line.startsWith("Fee Details:")) {
                            if (line.trim().equals("No marks available.")) {
                                break;
                            }
                            String[] markDetails = line.split(":");
                            String subject = markDetails[0].trim();
                            double marks = Double.parseDouble(markDetails[1].trim());
                            marksList.addMarks(subject, marks);
                        }
                    } else if (line.startsWith("Fee Details:")) {
                        // Read fee details until Attendance
                        while ((line = reader.readLine()) != null && !line.startsWith("Attendance:")) {
                            if (line.trim().equals("No fee details available.")) {
                                break;
                            }
                            String[] feeDetailsArray = line.split(":");
                            int voucherNo = Integer.parseInt(feeDetailsArray[0].trim());
                            String month = feeDetailsArray[1].trim();
                            feeDetails.addStudentFee(voucherNo, month);
                        }
                    } else if (line.startsWith("Attendance:")) {
                        // Read attendance until the end of the student details
                        while ((line = reader.readLine()) != null && !line.trim().equals("")) {
                            if (line.trim().equals("No attendance records available.")) {
                                break;
                            }
                            boolean isPresent = Boolean.parseBoolean(line.trim());
                            attendance.insert(isPresent);
                        }
                    }
                }

                // Add the last student to the list if exists
                if (studentId != 0) {
                    totalNumberOfStudents =studentId;
                    addStudentToList(studentId,father, name, gender, dob, contact, address, currentClass, section,
                            previous, marksList, feeDetails, attendance);
                }

            } catch (IOException e) {
                System.out.println("Error reading student details from file: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred while reading student details: " + e.getMessage());
            }
        }

        // Helper function to add student node to the list
        private void addStudentToList(int studentId,String father, String name, String gender, String dob, String contact, String address,
                                      int currentClass, char section, PreviousAcademicBackground previous,
                                      MarksListForStudent marksList, FeeClass feeDetails, BooleanArray attendance) {
            StudentNode newStudent = new StudentNode();
            newStudent.StudentId = studentId;
            newStudent.Name = name;
            newStudent.Gender = gender;
            newStudent.DateOfBirth = dob;
            newStudent.FatherName=father;
            newStudent.Contact = contact;
            newStudent.Address = address;
            newStudent.CurrentClass = currentClass;
            newStudent.Section = section;
            newStudent.Previous = previous;
            newStudent.marks = marksList;
            newStudent.fee = feeDetails;
            newStudent.attendance = attendance;
            if (head == null) {
                head = tail = newStudent;
            } else {
                tail.next = newStudent;
                newStudent.prev = tail;
                tail = newStudent ;
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
            StudentNode current = head;

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
            StudentNode[] output = new StudentNode[size];
            int[] count = new int[256]; // Count array for all ASCII characters

            // Count occurrences of characters at the given index
            StudentNode current = head;
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
        public void radixSortByClass() {
            int max = findMax(); // Find the maximum value to determine the number of digits
            for (int exp = 1; max / exp > 0; exp *= 10) {
                countingSortByClass(exp);
            }
        }

        private int findMax() {
            int max = Integer.MIN_VALUE;
            StudentNode current = head;
            while (current != null) {
                if (current.CurrentClass > max) {
                    max = current.CurrentClass;
                }
                current = current.next;
            }
            return max;
        }

        private void countingSortByClass(int exp) {
            if (head == null) return;

            int size = getCount();
            StudentNode[] output = new StudentNode[size];
            int[] count = new int[10]; // Count array for digits 0-9

            // Step 1: Count occurrences of digits
            StudentNode current = head;
            while (current != null) {
                int index = (current.CurrentClass / exp) % 10;
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
                int index = (current.CurrentClass / exp) % 10;
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
        public void radixSortById() {
            int max = findMaxId(); // Find the maximum value to determine the number of digits
            for (int exp = 1; max / exp > 0; exp *= 10) {
                countingSortById(exp);
            }
        }

        private int findMaxId() {
            int max = Integer.MIN_VALUE;
            StudentNode current = head;
            while (current != null) {
                if (current.StudentId > max) {
                    max = current.StudentId;
                }
                current = current.next;
            }
            return max;
        }

        private void countingSortById(int exp) {
            if (head == null) return;

            int size = getCount();
            StudentNode[] output = new StudentNode[size];
            int[] count = new int[10]; // Count array for digits 0-9

            // Step 1: Count occurrences of digits
            StudentNode current = head;
            while (current != null) {
                int index = (current.StudentId / exp) % 10;
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
                int index = (current.StudentId / exp) % 10;
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
            StudentNode current = head;
            while (current != null) {
                count++;
                current = current.next;
            }
            return count;
        }
    }



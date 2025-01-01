import java.util.Scanner;
public class MarksListForStudent {
    MarksNode head,tail;
    MarksListForStudent()
    {
        head = null;
        tail = null;
    }
    Scanner sc = new Scanner(System.in);
    public boolean isEmpty()
    {
        return head == null;
    }
    public void displayAllMarks()
    {
        MarksNode temp=head;
        while (temp!=null)
        {
            System.out.println("1. "+temp.mark+" : "+temp.mark);
            temp=temp.next;
        }
    }
    public void addMarks(String subject,double marks)
    {
        MarksNode newNode = new MarksNode();
        newNode.subjects = subject;
        newNode.mark = marks;
        if (isEmpty())
            head = newNode;
        else {
            tail.next = newNode;
        }
        newNode.prev = tail;
        tail = newNode;
    }

    public void updateStudentMarks() {
        MarksNode temp = head;
        while (true) {
            System.out.println("\nCurrent Details:");
            displayStudentMarks();
            System.out.println("\n:");
            int choice = 0;
            while (true) {
                try {
                    System.out.print("Select s.no for edit marks or press 0 to save: ");
                    choice = sc.nextInt();
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    sc.nextLine(); // Clear the buffer
                }
            }

            if (choice==0)
            {   System.out.println("marks update successfully ");
                return;}
            for (int i = 0; i <choice&&temp!=null ; i++) {
                temp=temp.next;
            }
            if (temp==null)
            {
                System.out.println("Invalid selection !!!");
                continue;
            }
            double mark = 0;
            while (true) {
                try {
                    System.out.println("Enter new marks: ");
                    mark = sc.nextDouble();
                    temp.mark = mark;
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter valid marks.");
                    sc.nextLine(); // Clear the buffer
                }
            }

        }
    }


    public void displayStudentMarks() {
        StyledConsoleOutput.printStyled("      * * * STUDENT MARKS DETAILS * * *       ",true,false,"cyan");
        System.out.println("|==========|====================|==========|");
        System.out.println("|   SNO    |     SUBJECT        |  MARKS   |");
        System.out.println("|==========|====================|==========|");

        if (isEmpty()) {
            System.out.println("|   ---    |       ------       |  ------  |");
            System.out.println("|----------|--------------------|----------|");
            return;
        }

        MarksNode temp = head;
        int sno = 1;

        while (temp != null) {
            System.out.printf("|%-10d|%-20s|%-10s|\n",
                    sno, temp.subjects, temp.mark);
            System.out.println("|----------|--------------------|----------|");

            temp = temp.next;
            sno++;
        }
    }

    public void displayMarkSheet() {
        StyledConsoleOutput.printStyled("                                         ** MARK SHEET **                                        ",true,false,"cyan");
        System.out.println("|--------|-----------------------|-------------------|-------------------|------------------|------------------|");
        System.out.println("| S.NO   |       SUBJECT         | MAXIMUM MARKS     | MINIMUM MARKS     | OBTAINED MARKS   |      GRADE       |");
        System.out.println("|--------|-----------------------|-------------------|-------------------|------------------|------------------|");

        MarksNode temp = head;
        int totalMarks = 0, sno = 1;
        String totalGrade;

        while (temp != null) {
            totalMarks += temp.mark;
            String grade = calculateGrade(temp.mark);
            System.out.printf("| %-6s | %-21s | %-17s | %-17s | %-16s | %-16s |\n",
                    centerAlign(String.valueOf(sno), 6),
                    centerAlign(temp.subjects, 21),
                    centerAlign("100", 17),
                    centerAlign("40", 17),
                    centerAlign(String.valueOf(temp.mark), 16),
                    centerAlign(grade, 16));
            temp = temp.next;
            sno++;
        }
        System.out.println("|--------|-----------------------|-------------------|-------------------|------------------|------------------|\n");
        double percentage = (double) totalMarks / (sno - 1);
        totalGrade = calculateGrade(percentage);

        // Display total marks, grade, and percentage
        System.out.println("                 Grand Total: " + totalMarks + "/" + (100 * (sno - 1)) + "            Grade: " + totalGrade + "              Percentage: " + String.format("%.2f", percentage) + "%");

        // Adding remarks based on the total grade
        System.out.println("\nRemarks:");
        switch (totalGrade) {
            case "A":
                System.out.println("Outstanding! Your dedication and hard work shine brightly.");
                System.out.println("Keep up this excellent momentum and inspire others around you!");
                break;
            case "B":
                System.out.println("Great job! Your consistent efforts are paying off.");
                System.out.println("Push a little harder to reach the pinnacle of success—you’ve got this!");
                break;
            case "C":
                System.out.println("Good work! With a bit more focus and determination, you'll achieve even greater heights.");
                System.out.println("Keep believing in yourself!");
                break;
            case "D":
                System.out.println("You passed, and that's a step forward!");
                System.out.println("Now, focus on strengthening your weak areas to ensure a stronger future.");
                break;
            case "E":
                System.out.println("Don't be disheartened. This is an opportunity to work harder and prove your potential.");
                System.out.println("Keep striving and never give up!");
                break;
            case "F":
                System.out.println("Failure is a stepping stone to success. Learn from this experience.");
                System.out.println("Rebuild your confidence and come back stronger!");
                break;
            default:
                System.out.println("Invalid grade. Please check your marks calculation.");
        }
    }

    private String calculateGrade(double marks) {
        if (marks > 89.9) {
            return "A";
        } else if (marks > 79.9) {
            return "B";
        } else if (marks > 69.9) {
            return "C";
        } else if (marks > 59.9) {
            return "D";
        } else if (marks > 49.9) {
            return "E";
        } else {
            return "F";
        }
    }


    private String centerAlign(String text, int width) {
        int padding = (width - text.length()) / 2;
        String format = String.format("%%%ds%%s%%%ds", padding, padding);
        return String.format(format, "", text, "");
    }

}
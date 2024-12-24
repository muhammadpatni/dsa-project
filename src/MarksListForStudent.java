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
            System.out.print("Select s.no for edit marks or press 0 to save");
            int choice = sc.nextInt();
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
            System.out.println("enter new marks");
            temp.mark= sc.nextDouble();
        }
    }


    public void displayStudentMarks() {
        System.out.println("      * * * STUDENT MARKS DETAILS * * *       ");
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
        System.out.println("                                         ** MARKSHEET **                                        ");
        System.out.println("|--------|-----------------------|-------------------|-------------------|------------------|------------------|");
        System.out.println("| S.NO   |       SUBJECT         | MAXIMUM MARKS     | MINIMUM MARKS     | OBTAINED MARKS   |      GRADE       |");
        System.out.println("|--------|-----------------------|-------------------|-------------------|------------------|------------------|");

        MarksNode temp = head;
        int totalMarks = 0, sno = 1;
        String totalGrade ;

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
        System.out.println("                 Grand Total: "+totalMarks+ "/" + (100 * (sno - 1)) + "            Grade: " + totalGrade + "              Percentage: " + String.format("%.2f", percentage) + "%");

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

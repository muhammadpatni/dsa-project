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
    public void addMarks(String subject,float marks)
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
        if (isEmpty()) {
            System.out.println("No students Marks available.");
            return;
        }
        MarksNode temp = head;
        while (true) {
            System.out.println("\nCurrent Details:");
           displayStudentMarks();
            System.out.println("\n:");
            System.out.print("Select s.no for edit marks or press 0 to save");
            int choice = sc.nextInt();
            if (choice==0)
                return;
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
        System.out.println("|------|---------------------|---------------|---------------|----------------|----------------|");
        System.out.println("| S.NO |       SUBJECT       | MAXIMUM MARKS | MINIMUM MARKS | OBTAINED MARKS |     GRADE      |");
        System.out.println("|------|---------------------|---------------|---------------|----------------|----------------|");

        MarksNode temp = head;
        int totalMarks = 0, sno = 1;
        String totalGrade = "";

        while (temp != null) {
            totalMarks += temp.mark;
            String grade = calculateGrade(temp.mark);
            System.out.printf("| %-4d | %-18s | %-13d | %-13d | %-14d | %-14s |\n", sno, temp.subjects, 100, 40, temp.mark, grade);
            temp = temp.next;
            sno++;
        }

        double percentage = (double) totalMarks / (sno - 1);
        totalGrade = calculateGrade(percentage);
        System.out.println("|------|---------------------|---------------|---------------|----------------|----------------|");
        System.out.printf("| %-4s | %-18s | %-13s | %-13s | %-14s | %-14s |\n", "", "TOTAL", "500", "200", totalMarks, totalGrade);
        System.out.println("|------|---------------------|---------------|---------------|----------------|----------------|");
        System.out.printf("PERCENTAGE: %.2f%%\n", percentage);
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
}

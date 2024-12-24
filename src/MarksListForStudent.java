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
}

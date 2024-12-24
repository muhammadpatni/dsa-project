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
            System.out.println("Subject: " + temp.subjects);
            System.out.println("Mark: " + temp.mark);
            System.out.println("\nOptions:");
            System.out.println("1. Update Subject");
            System.out.println("2. Update Mark");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter new subject: ");
                    temp.subjects = sc.nextLine();
                    break;

                case 2:
                    System.out.print("Enter new mark: ");
                    temp.mark = sc.nextInt();
                    sc.nextLine();
                    break;

                case 0:
                    System.out.println("Student updated successfully.");

                    return;

                default:
                    System.out.println("Invalid choice, please try again.");
            }
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

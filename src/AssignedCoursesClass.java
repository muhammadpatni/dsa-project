import java.util.Scanner;

public class AssignedCoursesClass {
    Scanner sc= new Scanner(System.in);

    ClassNode head, tail;
    AssignedCoursesClass()
    {
        head = null;
        tail = null;

    }
    public boolean isEmpty()
    {
        return head == null;
    }

}

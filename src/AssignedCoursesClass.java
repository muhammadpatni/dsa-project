import java.util.Objects;
import java.util.Scanner;

public class AssignedCoursesClass {
    ClassNode head, tail;

    AssignedCoursesClass() {
        head = tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void add(int teacherId, int classNumber, String course, String teacherName) {
        ClassNode newNode = new ClassNode();
        newNode.Teacher_ID = teacherId;
        newNode.CLASS = classNumber;
        newNode.Course = course;
        newNode.Teacher_Name = teacherName;
        if (isEmpty())
            head = newNode;
        else {
            tail.next = newNode;
        }
        newNode.prev = tail;
        tail = newNode;
    }

    public void delete(int teacherId, String course, int Class) {
        ClassNode temp = head;
        while (temp != null) {
            if (temp.Teacher_ID == teacherId && temp.Course.equals(course) && temp.CLASS == Class) {
                break;
            }
            temp = temp.next;
        }
        if (temp==null)
        {
            System.out.println("invalid course name");
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

    public boolean checkCourseIsAlreadyAssigned(int id, String course, int Class) {
        boolean assigned = false;
        ClassNode temp = head;
        while (temp != null) {
            if (temp.Teacher_ID == id && temp.Course.equals(course) && temp.CLASS == Class) {
                assigned = true;
                break;
            }
            temp = temp.next;
        }
        return assigned;
    }

    public void displaySpecificTeacherById(int TeacherId) {
        boolean found = false;
        ClassNode temp = head;
        System.out.println("                             * * * SPECIFIC TEACHER DETAILS * * *                                 ");
        System.out.println("|====================|====================|=============|========================================|");
        System.out.println("|     TEACHER ID     |    TEACHER NAME    |    CLASS    |                  COURSE                |");
        System.out.println("|====================|====================|=============|========================================|");
        if (isEmpty()) {

            System.out.println("|       - - - -      |       - - - -      |   - - - -   |               - - - -                  |");
            System.out.println("|--------------------|--------------------|-------------|----------------------------------------|");

            return;
        }
        while (temp != null) {
            if (temp.Teacher_ID == TeacherId) {
                found = true;
                System.out.printf("|%-20s|%-20s|%-13s|%-40s|\n", temp.Teacher_ID, temp.Teacher_Name, temp.CLASS, temp.Course);
                System.out.println("|--------------------|--------------------|-------------|----------------------------------------|");
            }
            temp = temp.next;
        }
        if (!found) {
            System.out.println("|       - - - -      |       - - - -      |   - - - -   |               - - - -                  |");
            System.out.println("|--------------------|--------------------|-------------|----------------------------------------|");
        }
    }

    public void displaySpecificClassById(int ClassId) {
        boolean found = false;
        ClassNode temp = head;
        System.out.println("                             * * * SPECIFIC CLASS DETAILS * * *                                 ");
        System.out.println("|====================|====================|=============|========================================|");
        System.out.println("|     TEACHER ID     |    TEACHER NAME    |    CLASS    |                  COURSE                |");
        System.out.println("|====================|====================|=============|========================================|");
        if (isEmpty()) {

            System.out.println("|       - - - -      |       - - - -      |   - - - -   |               - - - -                  |");
            System.out.println("|--------------------|--------------------|-------------|----------------------------------------|");

            return;
        }
        while (temp != null) {
            if (temp.CLASS == ClassId) {
                found = true;
                System.out.printf("|%-20s|%-20s|%-13s|%-40s|\n", temp.Teacher_ID, temp.Teacher_Name, temp.CLASS, temp.Course);
                System.out.println("|--------------------|--------------------|-------------|----------------------------------------|");
            }
            temp = temp.next;
        }
        if (!found) {
            System.out.println("|       - - - -      |       - - - -      |   - - - -   |               - - - -                  |");
            System.out.println("|--------------------|--------------------|-------------|----------------------------------------|");
        }
    }

    public void printCoursesForTeacherInSpecificClass(int id ,int Class)
    {
        ClassNode temp = head;
        while (temp != null) {
            if (temp.Teacher_ID == id&&temp.CLASS==Class)
            {
                System.out.println(". "+temp.Course);
            }
            temp = temp.next;
        }
    }
    public boolean search(int id)
    {
        ClassNode temp = head;
        while (temp != null) {
            if (temp.Teacher_ID == id)
            { return true;
            }
            temp = temp.next;
        }
        return false;
    }

    }

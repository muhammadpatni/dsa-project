import java.io.*;
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

    String Filename =" C:\\Users\\Admin\\Desktop\\dsa-project\\AssignedCourseClass.txt";

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
            System.out.println("Invalid course name");
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

    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Filename))) {
            ClassNode temp = head;
            writer.write("\t \t \t \t * * * * ASSIGNED COURSES DETAILS * * * * \n\n");

            if (temp == null) {
                writer.write("No assigned courses to display.\n");
                return;
            }

            while (temp != null) {
                writer.write("Teacher ID: " + temp.Teacher_ID + "\n");
                writer.write("Teacher Name: " + temp.Teacher_Name + "\n");
                writer.write("Class: " + temp.CLASS + "\n");
                writer.write("Course: " + temp.Course + "\n");
                writer.write("----------------------------------------------------\n");
                temp = temp.next;
            }
        } catch (IOException e) {
            System.out.println("Error saving assigned courses details to file.");
        }
    }

    public void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(Filename))) {
            String line;

            int teacherId = 0;
            int classNumber = 0;
            String course = "";
            String teacherName = "";

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Teacher ID:")) {
                    teacherId = Integer.parseInt(line.split(":")[1].trim());
                } else if (line.startsWith("Teacher Name:")) {
                    teacherName = line.split(":")[1].trim();
                } else if (line.startsWith("Class:")) {
                    classNumber = Integer.parseInt(line.split(":")[1].trim());
                } else if (line.startsWith("Course:")) {
                    course = line.split(":")[1].trim();
                } else if (line.startsWith("----------------------------------------------------")) {
                    // Add a new node for the current teacher-course assignment
                    add(teacherId, classNumber, course, teacherName);

                    // Reset variables for the next record
                    teacherId = 0;
                    classNumber = 0;
                    course = "";
                    teacherName = "";
                }
            }

            // Check for the last record in case the file doesn't end with a separator line
            if (teacherId != 0) {
                add(teacherId, classNumber, course, teacherName);
            }

        } catch (IOException e) {
            System.out.println("Error reading assigned courses from file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing number in file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred while reading assigned courses: " + e.getMessage());
        }
    }





}
 
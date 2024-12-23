import java.util.Scanner;

public class AssignedCoursesClass {
         ClassNode head,tail;

    AssignedCoursesClass()
    {
        head=tail=null;
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

        public void delete(int teacherId) {
            ClassNode temp = head;
            if(isEmpty())
            {
                System.out.println("no Teacher available ..");
                return;
            }
            while(temp!=null){
                if (temp.Teacher_ID==teacherId)
                {
                    break;
                }
                temp=temp.next;
            }
            if (temp==null)
            {
                System.out.println("Invalid ID");
                return;
            }
            if (temp==head)
            {   head=temp.next;
                head.prev=null;
                return;
            }
            if (temp==tail)
            {
                tail=temp.prev;
                tail.next=null;
                return;
            }
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
            System.out.println("Teacher ID not found: " + teacherId);
        }
        public boolean checkCourseIsAlreadyAssigned(int id,String course,int Class)
        { boolean assigned=false;






        return assigned;
        }

        public void displayForSpecificTeacher() {
            ClassNode current = head;
            while (current != null) {
                System.out.println("Teacher ID: " + current.Teacher_ID + ", Class: " + current.CLASS + ", Course: " + current.Course + ", Teacher Name: " + current.Teacher_Name);
                current = current.next;
            }
        }

    }

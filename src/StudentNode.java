import java.util.Date;

public class StudentNode {
    String Name,Gender , DateOfBirth,Contact,Address,FatherName;
    int StudentId, CurrentClass;
    char Section;
    PreviousAcademicBackground Previous;
    MarksListForStudent marks;
    StudentNode prev,next;
    FeeClass fee1 = new FeeClass();
    FeeNode fee = new FeeNode();
}

public class StudentNode {
    String Name,Gender , DateOfBirth,Contact,Address,FatherName;
    int StudentId, CurrentClass;
    char Section;
    PreviousAcademicBackground Previous;
    MarksListForStudent marks;
    StudentNode prev,next;
    FeeClass fee = new FeeClass();
    BooleanArray attendance = new BooleanArray();
}

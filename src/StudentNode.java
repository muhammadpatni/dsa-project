public class StudentNode {
    String Name,Gender , DateOfBirth,Contact,Address,FatherName;
    int StudentId, CurrentClass;
    char Section;
    PreviousAcademicBackground Previous;
    MarksListForStudent marks=new MarksListForStudent();
    StudentNode prev,next;
    FeeClass fee = new FeeClass();
    BooleanArray attendance = new BooleanArray();
}

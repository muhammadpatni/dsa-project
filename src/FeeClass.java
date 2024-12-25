import java.time.LocalDate;
import java.util.Scanner;

public class FeeClass {

    private int FeeId;
    Scanner sc= new Scanner(System.in);
    FeeNode head,tail;
    FeeClass()
    {
        head = null;
        tail = null;
        FeeId =0;

    }
    public boolean isEmpty()
    {
        return head == null;
    }
    public boolean searchMonth(String month) {
        FeeNode temp = head;
        while (temp != null) {
            if (month.equals(temp.Month)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }
    public  String getMonthNameFromDate(LocalDate date)
    {
        String dateString = date.toString();
        String monthString = dateString.substring(5, 7);
        switch (monthString) {
            case "01":
                return "January";
            case "02":
                return "February";
            case "03":
                return "March";
            case "04":
                return "April";
            case "05":
                return "May";
            case "06":
                return "June";
            case "07":
                return "July";
            case "08":
                return "August";
            case "09":
                return "September";
            case "10":
                return "October";
            case "11":
                return "November";
            case "12":
                return "December";
            default:
                return "Invalid month";
        }
    }

    public void addStudentFee(int Voucherid, String Month) {
        FeeNode newNode = new FeeNode();
        newNode.VoucherNo = Voucherid;
        newNode.Month = Month;
        newNode.date = LocalDate.now();
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
        }
        tail = newNode;
    }


}
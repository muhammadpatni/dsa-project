import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.Buffer;
import java.time.LocalDate;

public class FeeClass {

    FeeNode head,tail;
    FeeClass()
    {
        head = null;
        tail = null;
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
    public void displayFee(BufferedWriter writer) throws IOException {
        FeeNode temp=head;
        while (temp!=null)
        {
            writer.write("Month: "+temp.Month+"\n");
            writer.write("Voucher Number: "+temp.VoucherNo+"\n");
            writer.write("Date: "+temp.date+"\n");
            temp=temp.next;
        }
    }


    public static String getMonthNameFromDate(LocalDate date)
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

    public void addStudentFee(int Voucherid, String Month,String date) {
        FeeNode newNode = new FeeNode();
        newNode.VoucherNo = Voucherid;
        newNode.Month = Month;
        newNode.date =date;
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
        }
        tail = newNode;
    }
    public int ReturnVoucherNo(String month) {
        FeeNode temp = head;
        while (temp != null) {
            if (month.equals(temp.Month)) {
                return temp.VoucherNo;
            }
            temp = temp.next;
        }
        return 0;
    }
}

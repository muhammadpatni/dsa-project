public class Array {
    private String array[];
    private int index;

    Array() {
        array = new String[3];
        index = 0;
    }
    public int size() {
        return index;
    }
    public  String[] giveCurrentArray()
    {  String[] temp=new String[index];
        for (int i = 0; i < size(); i++) {
            temp[i]=array[i];
        }
        return  temp;
    }
    public boolean noCertificate()
    {
        return index==0;
    }

    public String GetCertificate(int certificate)
    {
        return array[certificate];
    }

    public void print() {
        for (int i = 0; i < index; i++) {
            System.out.println(i+1 + " : " + array[i]);
        }
    }
    public void insert(String Course) {
        if (index == array.length) {
            String[] temp = new String[array.length * 2];

            for (int i = 0; i < array.length; i++) {
                temp[i] = array[i];
            }
            array = temp;
        }
        array[index++] = Course.toLowerCase();
    }

    public  void makeArrayEmpty()
    {
        index=0;
    }
    public  void delete(int Pos)
    {
        if (Pos==-1)
        {
            System.out.println("course not found");
            return;
        }
        for(int i = Pos-1; i<index; i++)
        {
            if(i==index-1)
                array[i] = null;
            else
            {
                array[i] = array[i+1];
            }
        }
        index--;
    }
    public int position (String Course) {
        for (int i = 0; i < index; i++) {
            String c=Course.toLowerCase();
            if (c.equals(array[i]))
            {
                return i+1;
            }
        }
        return -1;
    }
    public boolean search(String course)
    {
        if (index==0)
           {
               return false;
           }
        boolean find=false;
        for (int i = 0; i <index ; i++) {
            if (array[i].equals(course))
            {
                find=true;
                break;
            }
        }
        return find;
    }
}

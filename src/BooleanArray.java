public class BooleanArray {
    private boolean array[];
    private int index;

    BooleanArray() {
        array = new boolean[3];
        index = 0;
    }
    public boolean getAttendance(int i)
    {
        return array[i];
    }
    public int size() {
        return index;
    }
    public void print()
    {
        for (int i = 0; i < index; i++) {
            System.out.println(array[i]);
        }
    }
    public boolean isEmpty()
    {return  index==0;}
    public void insert(boolean isPresent) {
        if (index == array.length) {
            boolean[] temp = new boolean[array.length * 2];

            for(int i = 0; i < array.length; i++) {
                temp[i] = array[i];
            }
            array = temp;
        }
        array[index++] =isPresent;
    }
}

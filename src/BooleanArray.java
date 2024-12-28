public class BooleanArray {
    private boolean array[];
    private int index;

    BooleanArray() {
        array = new boolean[3];
        index = 0;
    }
    public int size() {
        return index;
    }
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
                array[i] = false;
            else
            {
                array[i] = array[i+1];
            }
        }
        index--;
    }
}

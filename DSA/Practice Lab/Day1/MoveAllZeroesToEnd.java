import java.util.Arrays;

public class MoveAllZeroesToEnd {

    static int[] moveAllZeroesToEnd(int[] arr){
        int writeIndex = 0;
        for(int i=0;i<arr.length;i++){
            if(arr[i] != 0){
                int temp = arr[i];
                arr[i] = arr[writeIndex];
                arr[writeIndex] = temp;

                writeIndex++;
            }
        }
        return arr;
    }

    public static void main(String[] args){
        int[] arr = {10,0,20,0,30,0,40};
        System.out.println(Arrays.toString(moveAllZeroesToEnd(arr)));
    }
}

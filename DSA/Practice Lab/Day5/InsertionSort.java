import java.util.Arrays;

public class InsertionSort {

    static int[] insertionSort(int[] arr){
        for(int index = 1; index < arr.length; index++){
            int currentVal = arr[index];
            int currentPosition = index - 1;
            while(currentPosition >= 0 && currentVal < arr[currentPosition]){
                arr[currentPosition+1] = arr[currentPosition];
                currentPosition--;
            }
            arr[currentPosition+1]=currentVal;
        }
        return arr;
    }

    public static void main(String[] args) {
        int arr[] = {9,4,5,2,1};
        System.out.println(Arrays.toString(insertionSort(arr)));
    }
}

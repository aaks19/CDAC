import java.util.Arrays;

public class SelectionSort {
    public static int[] selectionSort(int[] arr){
        int n = arr.length;
        for(int i=0; i<n ; i++){
            int minIndex = i;
            for(int j=i+1 ; j<n; j++){
                if(arr[j] < arr[minIndex]){
                    minIndex = j;
                }
            }
            if(i != minIndex){
                swap(arr, i , minIndex);
            }
        }
        return arr;
    }

    public static void swap(int[] arr, int i, int minIndex){
        int temp = arr[i];
        arr[i] = arr[minIndex];
        arr[minIndex] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {10,41,52,12,4,5,19};

        System.out.println(Arrays.toString(selectionSort(arr)));
    }
}

import java.util.Arrays;

public class SelectionSort {


    static void swap(int[] arr, int i,int minIndex){
        int temp = arr[i];
        arr[i] = arr[minIndex];
        arr[minIndex] = temp;
    }

    static int[] selectionSort(int[] arr){
        int n = arr.length;
        for(int i=0;i<n;i++){
            int minIndex = i;
            for(int j=i+1;j<n;j++){
                if(arr[j]<arr[minIndex]){
                    minIndex = j;
                }
            }
            if(i!=minIndex){
                swap(arr,i,minIndex);
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {6,4,2,5,9,1,8};
        System.out.println("Sorted array : "+Arrays.toString(selectionSort(arr)));
    }
}

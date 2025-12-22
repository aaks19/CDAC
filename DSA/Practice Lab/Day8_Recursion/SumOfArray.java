
public class SumOfArray {

    public static int sumArray(int[] arr, int size) {
        if(size==0){
            return 0;
        }
        int lastElem = arr[size-1];
        int sum = lastElem + sumArray(arr, size-1);

        return sum;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        System.out.println("Sum of array = "+sumArray(arr,arr.length));
    }
}

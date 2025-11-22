import java.util.Arrays;

public class RotationArray {


    static int[] rotationArray(int[] arr,int k)
    {
        int n = arr.length;
        int d = k+1;
        d = d%n;

        int i=0;
        int start =0;
        int end=arr.length-1;

        while(i<(d-1))
        {
            int temp = arr[i];
            arr[i] = arr[d-1];
            arr[d-1] = temp;

            i++;
            d--;
        }
        System.out.println(Arrays.toString(arr));

        while(k+1<n)
        {
            int temp= arr[k+1];
            arr[k+1] = arr[n-1];
            arr[n-1] = temp;


            k++;
            n--;
        }
        System.out.println(Arrays.toString(arr));

        while(start <= end/2)
        {
             int temp= arr[start];
            arr[start] = arr[end];
            arr[end] = temp;

            start++;
            end--;
        }
        
        System.out.println(Arrays.toString(arr));
        return arr;
    }
     public static void main(String[] args) {
        
        int arr[]={1,2,3,4,5};
        int d = 2;

        int[] result =rotationArray(arr,d);
        System.out.println(Arrays.toString(result));
     }
}
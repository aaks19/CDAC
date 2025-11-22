import java.util.Arrays;

public class ReverseArray
{

    static int[] reverseArray(int[] arr)
    {
        int start=0;
        int end =arr.length-1;

        
        while(start <= end/2)
        {
            int temp= arr[start];
            arr[start]=arr[end];
            arr[end]=temp;

            start++;
            end--;
        }
        return arr;

    }

    public static void main(String[] args)
    {
        int arr[] = {5,4,3,2,1};
        int result[]=reverseArray(arr);

        // for(int i : result)
        // { System.out.print(i);}

        System.out.println(Arrays.toString(result));
       
    }
}
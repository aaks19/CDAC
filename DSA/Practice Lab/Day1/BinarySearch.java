public class BinarySearch {
    
    static int binarySearch(int[] arr, int t){
        int left = 0;
        int right = arr.length - 1;
        while(left<=right){
            int mid = left + (right-left)/2;
            if(arr[mid] == t){
                return mid;
            }
            if(arr[mid]>t){
                right = mid-1;
            }else{
                left = mid+1;
            }
        }
        return -1;
    }

    public static void main(String[] args){
        int[] arr = {1,2,3,3,3,4,5,6};
        System.out.println(binarySearch(arr,3));
    }
}

public class BinarySearchRecursion {
    public static int binarySearch(int[] arr, int t, int left, int right){
        if(left>right){
            return -1;
        }
        int mid = left + (right-left)/2;
        if(arr[mid] == t){
            return mid;
        }
        if(arr[mid]>t){
            return binarySearch(arr, t, left, mid-1);
        }
        else{
            return binarySearch(arr, t, mid+1, right);
        }
    }

    public static void main(String[] args) {
        int arr[] = {10,19,25,41,56,85,95};
        int target = 56;
        System.out.println("Searching element "+target + " found at : "+ binarySearch(arr, target,0,arr.length-1));
    }
}

public class NoOfElement {
    // First Occurence
    static int firstOccurence(int[] arr, int target){
        int left = 0 ;
        int right = arr.length-1;
        int result = -1;

        while(left<=right){
            int mid = left + (right-left)/2;
            if(arr[mid]==target){
                result = mid;
                right = mid-1;
            }else if(arr[mid]>target){
                right = mid-1;
            }else{
                left = mid+1;
            }
        }
        return result;
    }

    //Last occurence
    static int lastOccurence(int[] arr, int target){
        int left = 0 ;
        int right = arr.length-1;
        int result = -1;

        while(left<=right){
            int mid = left + (right-left)/2;
            if(arr[mid]==target){
                result = mid;
                left = mid+1;
            }else if(arr[mid]>target){
                right = mid-1;
            }else{
                left = mid+1;
            }
        }
        return result;
    }

    static int noOfElement(int f, int l){
        if(f==-1){
            return -1;
        }
        return l-f+1;
    }
    
    public static void main(String[] args) {
        int[] arr = {1,2,2,2,2,2,2,3,4,5};
        int target = 6;
        
        System.out.println("First occurence of "+ target +" is at index "+firstOccurence(arr, target));
        System.out.println("Last occurence of "+ target +" is at index "+lastOccurence(arr, target));

        System.out.println("No of element = "+noOfElement(firstOccurence(arr, target),lastOccurence(arr, target)));
    }
}

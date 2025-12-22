class MergeSort{
    public static void mergeSort(int[] arr, int left, int right){
        if(left == right){
            return;
        }
        int mid = left + (right - left)/2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid+1, right);
        // merge(arr,left,right,mid);
    }

    public static void merge(int[] arr, int left, int right, int mid){
        int n1 = mid-left+1;
        int n2 = right - mid;

        int arr1[] = new int[n1];
        int arr2[] = new int[n2];

        for(int i=0;i<arr1.length;i++){

        }

        for(int j=0;j<arr2.length;j++){
            
        }
    }
}
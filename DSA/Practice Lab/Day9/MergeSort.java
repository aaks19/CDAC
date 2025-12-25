class MergeSort{
    public static void mergeSort(int[] arr, int left, int right){
        if(left == right){
            return;
        }
        int mid = left + (right - left)/2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid+1, right);
        merge(arr,left,right,mid);
    }

    public static void merge(int[] arr, int left, int right, int mid){
        int n1 = mid-left+1;
        int n2 = right - mid;

        int arr1[] = new int[n1];
        int arr2[] = new int[n2];

        for(int i=0;i<arr1.length;i++){
            arr1[i] = arr[left+i];
        }

        for(int j=0;j<arr2.length;j++){
            arr2[j] = arr[mid+1+j];
        }

        int i = 0;
        int j = 0;
        int k = left;
        while(i<n1 && j<n2){
            if(arr1[i]<arr2[j]){
                arr[k++] = arr1[i++];
            }else{
                arr[k++] = arr2[j++];
            }
        }
        while(i<n1){
            arr[k++] = arr1[i++];
        }
        while(j<n2){
            arr[k++] = arr2[j++];
        }
    }

    public static void main(String[] args) {
        int[] arr = {25,4,1,5,45,22,14,85,19};
        mergeSort(arr, 0, arr.length-1);
        for(int i : arr){
            System.out.println(i + " ");
        }
    }
}
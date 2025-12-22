public class QuickSort {
    
    public static int findPartition(int[] arr, int low, int high){
        int pivot = arr[high];
        int partition = low - 1;

        for(int i=low ; i<=high ; i++){
            if(arr[i]<pivot){
                partition++;
                swap(arr,partition,i);
            }
        }
        swap(arr,partition+1,high);
        return partition+1;
    }

    public static void quickSort(int[] arr, int low, int high){
        if(low>=high){
            return;
        }
        int partitionIndex = findPartition(arr, low, high);
        quickSort(arr, low, partitionIndex-1);
        quickSort(arr, partitionIndex+1, high);
    }

    public static void swap(int[] arr, int index1, int index2){
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp; 
    }

    public static void main(String[] args) {
        int[] arrInput = {27,24,85,65,98,20,12,52,19};
        quickSort(arrInput, 0, arrInput.length-1);
        System.out.println("Sorted array : ");
        for(int val : arrInput){
            System.out.print(val+" ");
        }
    }
}

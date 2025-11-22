
import java.util.Arrays;


public class RotateMatrix90 {

    static int[][] rotateMatrix(int[][] arr) {
        int row = arr.length;
        int col = arr[0].length;


        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i < j) {
                    int temp = arr[i][j];
                    arr[i][j] = arr[j][i];
                    arr[j][i] = temp;
                }
            }
        }

        // for clockwise
        // for(int i=0;i<row;i++){
        //     int start = 0;
        //     int end = col - 1;
        //     while(start<end/2){
        //         int temp = arr[i][start];
        //         arr[i][start] = arr[i][end];
        //         arr[i][end] = temp;
                
        //         start++;
        //         end--;
        //     }
        // }

        //for anti-clockwise
        for(int i=0;i<col;i++){
            int start = 0;
            int end = col - 1;
            while(start<end/2){
                int temp = arr[start][i];
                arr[start][i] = arr[end][i];
                arr[end][i] = temp;
                
                start++;
                end--;
            }
        }

        return arr;
    }

    public static void main(String[] args) {

        int[][] arr = {{1, 2}, {3,4}};
        
        System.out.println(Arrays.deepToString(rotateMatrix(arr)));
    }
}

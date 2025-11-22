public class rowColMaxSum {
    
    static int[]  rowColMax(int[][] arr)
    {
        int row = arr.length;
        int col =arr[0].length;

        int maxSum=0;
        int resultArray[]=new int[row];
        int colResultArray[] = new int[col];
        int result[] = new int[2];
        //max row.............
        for(int i=0 ; i<row; i++)
        {
        int sum =0;
            for(int j=0; j< col; j++)
            {
                sum = sum+ arr[i][j];
            }


            maxSum = Math.max(maxSum,sum);
           resultArray[i]=sum;

        }
        
        for(int i=0 ; i<resultArray.length;i++)
        {
            if(resultArray[i]== maxSum)
            {
                result[0]=i;
                break;
            }
            
        }

        //max coloumSum..............
      
        maxSum=0;
        for(int i =0; i<col; i++)
        {
              int sum=0;
            for(int j=0;j<row; j++)
            {
                sum = sum+ arr[j][i];
            }
            
            maxSum = Math.max(maxSum,sum);
            colResultArray[i]=maxSum;   
        }

        for(int i=0;i<colResultArray.length;i++)
        {
            if(colResultArray[i] == maxSum)
            {
                result[1]=i;
                break;
            }
        }

       return result;
    }
    public static void main(String[] args) {
        
        int arr[][] = {{1,2,3},{4,5,6},{7,8,9}};
      //  int arr[][] = {{3,7,2},{9,1,8}};
        
        
       System.out.println("Row: "+rowColMax(arr)[0] + "   coloum: "+rowColMax(arr)[1] );
       
    }
}



public class LinearQueue1 {
    
    int arr[];
    int front, rear, maxSize;
    LinearQueue1(int size)
    {
        this.maxSize = size;
        this.arr = new int[maxSize];
        this.front=0;
        this.rear = -1;
    }

   public  void enque(int data)
    {
        if(rear==maxSize)
        {
            System.out.println("overflow");
        }
        else
        {
            arr[++rear] = data;

        }
    }
    public int deque()
    {
        if(isEmpty())
        {
            System.out.println("underflow");
        }
       
           int data = arr[front++];
          
        
          return data;
    }
    public void peek()
    {
        System.out.println(arr[front]);
    }
    public boolean isEmpty()
    {
        if(front>maxSize-1)
        {
           return true;
        }
      
            return false;
        
    }
    public static void main(String[] args) {
        

        
        LinearQueue1 queue = new LinearQueue1(10);
        queue.enque(10);
        int a =queue.deque();
        System.out.println( queue.isEmpty());
        

    }
}

public class CircularQueue {
    int[] queue ;
    int temp;
    int front, rear, maxSize;
    int count = 0;

    public CircularQueue(int size){
        this.maxSize=size;
        queue = new int[maxSize];
        front = 0;
        rear = -1;
        count = 0;
    }

    public void enqueue(int data){
        if(isFull()){
            System.out.println("Queue is Full");
            return;
        }
        rear = (rear + 1) % maxSize;
        queue[rear] = data;
        count++;
    }

    public int dequeue(){
        if(isEmpty()){
            System.out.println("Queue is empty");
            return -1;
        }
        int data = queue[front];
        front = (front+1)%maxSize;
        count--;
        return data;
    }

    public void display(){
        if(isEmpty()){
            System.out.println("Queue is empty");
            return;
        }

        int i = front;
        int currentcount = 0;
        while(currentcount < count){
            System.out.println(queue[i]);
            i = (i+1)% maxSize;
            currentcount++;
        }
        System.out.println();
    }

    public boolean isFull(){
        return count == maxSize;
    }

    public boolean isEmpty(){
        return count == 0;
    }

    public static void main(String[] args) {
        CircularQueue myQueue = new CircularQueue(5);

        myQueue.enqueue(10);
        myQueue.enqueue(20);
        myQueue.enqueue(30);
        myQueue.enqueue(40);
        myQueue.enqueue(50);
        // myQueue.enqueue(50);


        myQueue.display();

        System.out.println("popped element = "+myQueue.dequeue());
        myQueue.display();
    }
}

public class Queue {
    int[] queueArr;
    int front, rear, maxSize;
     public Queue(int size){
        queueArr = new int[size];
        this.front = 0;
        this.rear = -1;
        this.maxSize = size;
     }

     public void enque(int data){
        if(isfull()){
            System.out.println("Queue is full");
            return;
        }
        queueArr[++rear] = data;
     }

     public int dequeue(){
        if(isEmpty()){
            System.out.println("Queue is empty");
            return -1;
        }
        return queueArr[front++];
     }

     public void display(){
        for(int i=front ; i<=rear ; i++){
            System.out.println(queueArr[i] + " ");
        }
     }

     public boolean isEmpty(){
        return rear == -1;
     }

     public boolean isfull(){
        return rear == maxSize;
     }

     public static void main(String[] args) {
        Queue myQueue = new Queue(5);

        myQueue.enque(10);
        myQueue.enque(20);
        myQueue.enque(30);
        myQueue.enque(40);
        myQueue.enque(50);

        myQueue.display();

        
        myQueue.dequeue();
        myQueue.display();
        
     }
}

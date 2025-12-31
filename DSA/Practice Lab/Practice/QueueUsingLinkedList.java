class Node{
    int data;
    Node next;

    public Node(int data){
        this.data = data;
        this.next = null;
    }
}

public class QueueUsingLinkedList {
    Node front;
    Node rear;

    public void enqueue(int data){
        Node newNode = new Node(data);
        if(front == null){
            front = newNode;
            rear = front;
        }else{
            rear.next = newNode;
            rear = newNode;
        }
    }

    public int dequeue(){
        if(isEmpty()){
            System.out.println("Queue is empty");
            return -1;
        }
        int data = front.data;
        front = front.next;
        
        return data;
    }

    public void display(){
        Node temp = front;
        while(temp!=null){
            System.out.println(temp.data + " ");
            temp = temp.next;
        }
    }

    public boolean isEmpty(){
        return front == null;
    }

    public static void main(String[] args) {
        QueueUsingLinkedList myQueue = new QueueUsingLinkedList();
        myQueue.enqueue(10);
        myQueue.enqueue(20);
        myQueue.enqueue(30);
        myQueue.enqueue(40);
        myQueue.enqueue(50);
        myQueue.enqueue(60);

        myQueue.display();

        System.out.println("Popped element is " + myQueue.dequeue());
        myQueue.display();
    }
}

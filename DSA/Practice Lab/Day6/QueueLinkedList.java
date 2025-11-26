class Node{
    int data;
    Node next;
    public Node(int data){
        this.data = data;
        this.next = null;
    }
}

class QueueUsingLinkedList{
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
        // System.out.println(front.data);
        int data = front.data;
        front = front.next;
        // if(front == null){
        //     rear = null;
        // }
        return data;
    }

    public boolean isEmpty(){
        return front==null;
    }

    public int peek(){
        return front.data;
    }

    public void display(){
        Node temp = front;
        while(temp!=null){
            System.out.println(temp.data);
            temp = temp.next;
        }
    }
}

public class QueueLinkedList {
    public static void main(String[] args) {
        QueueUsingLinkedList queue = new QueueUsingLinkedList();
        queue.enqueue(10);
        queue.enqueue(6);
        System.out.println("Queue is Empty : "+queue.isEmpty());
        System.out.println("First element of Queue (peek) : " + queue.peek());
        queue.display();
        System.out.println("Deleted Element is : "+queue.dequeue());
        queue.display();
    }
}

public class CircularQueue {

    int arr[];
    int front;
    int rear;
    int count;
    int maxSize;

    public CircularQueue(int size) {
        this.maxSize = size;
        front = 0;
        rear = -1;
        count = 0;
        arr = new int[size];
    }

    public void enqueue(int data) {
        if (isFull()) {
            System.out.println("Queue Full");
            return;
        }
        rear = (rear + 1) % maxSize;
        arr[rear] = data;
        count++;
    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue Empty");
        }
        int data = arr[front];
        front = (front + 1) % maxSize;
        count--;
        return data;
    }

    public boolean isFull() {
        return count == maxSize;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Queue Empty");
            return;
        }
        int i = front;
        int currentCount = 0;
        while (currentCount < count) {
            System.out.println(arr[i]);
            i = (i + 1) % maxSize;
            currentCount++;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        CircularQueue circularQueue = new CircularQueue(6);
        circularQueue.enqueue(10);
        circularQueue.enqueue(20);
        circularQueue.enqueue(30);
        circularQueue.enqueue(40);
        circularQueue.enqueue(50);
        circularQueue.enqueue(60);
        System.out.println("--------------------Initial elements-----------------------");
        circularQueue.display();
        // circularQueue.enqueue(70);
        // circularQueue.enqueue(80);
        System.out.println("--------------------Removed elements-----------------------");
        System.out.println(circularQueue.dequeue());
        System.out.println(circularQueue.dequeue());
        System.out.println(circularQueue.dequeue());
        System.out.println("---------------------Resulted Queue----------------------");

        circularQueue.display();

    }
}

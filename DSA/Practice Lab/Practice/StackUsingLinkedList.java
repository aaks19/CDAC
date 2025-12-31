class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class StackUsingLinkedList {
    Node top;

    public void push(int data) {
        Node newNode = new Node(data);
        newNode.next = top;
        top = newNode;
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        }
        int data = top.data;
        top = top.next;
        return data;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void display() {
        Node temp = top;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public int count() {
        int cnt = 0;
        Node temp = top;
        while (temp != null) {
            cnt++;
            temp = temp.next;
        }
        return cnt;
    }

    public int search(int value) {
        Node temp = top;
        int pos = 1;
        while (temp != null) {
            if (temp.data == value) {
                return pos;
            }
            temp = temp.next;
            pos++;
        }
        return -1;
    }

    public void update(int pos, int newValue) {
        if (pos <= 0) {
            System.out.println("Invalid position");
            return;
        }

        Node temp = top;
        int cnt = 1;
        while (temp != null && cnt < pos) {
            temp = temp.next;
            cnt++;
        }

        if (temp != null) {
            temp.data = newValue;
        } else {
            System.out.println("Position out of range");
        }
    }

    public static void main(String[] args) {
        StackUsingLinkedList mystack = new StackUsingLinkedList();

        mystack.push(10);
        mystack.push(20);
        mystack.push(30);
        mystack.push(40);
        mystack.push(50);

        mystack.display();

        System.out.println("Count: " + mystack.count());
        System.out.println("Position of 30: " + mystack.search(30));

        mystack.update(3, 99);
        mystack.display();

        System.out.println("Popped element = " + mystack.pop());
        mystack.display();
    }
}

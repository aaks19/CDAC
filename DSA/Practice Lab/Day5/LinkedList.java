class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class LinkedList {
    Node head;

    public void addAtLast(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node temp = head;

        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    public void addAtFirst(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    public void insertAtPosition(int data, int pos) {
        Node newNode = new Node(data);
        Node temp = head;
        int count = 1;

        while (count < pos - 1) {
            count++;
            temp = temp.next;
        }
        Node next1 = temp.next;
        temp.next = newNode;
        newNode.next = next1;

    }

    public void removeFromPosition(int pos) {
        int count = 1;
        Node temp = head;
        if (pos == 1) {
            head = temp.next;
        }
        while (count < pos - 1) {
            count++;
            temp = temp.next;
        }
        temp.next = temp.next.next;
    }

    public void removeFromLast() {
        Node temp = head;
        while (temp.next.next != null) {
            temp = temp.next;
        }
        temp.next = null;

    }

    public void display() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        System.out.println("----------------Inserting at last----------------------");
        linkedList.addAtLast(10);
        linkedList.addAtLast(20);
        linkedList.addAtLast(30);

        linkedList.display();

        System.out.println("-----------------Inserting at first---------------------");
        linkedList.addAtFirst(40);
        linkedList.addAtFirst(50);
        linkedList.addAtFirst(60);

        linkedList.display();

        System.out.println("-----------------Inserting at specific position---------------------");
        linkedList.insertAtPosition(100, 3);
        linkedList.display();

        System.out.println("-----------------Remove from specific position---------------------");
        linkedList.removeFromPosition(4);
        linkedList.display();

        System.out.println("-----------------Remove from specific position---------------------");
        linkedList.removeFromPosition(1);
        linkedList.display();
        
        System.out.println("-----------------Remove from specific position---------------------");
        linkedList.removeFromPosition(2);
        linkedList.display();

        System.out.println("-----------------Remove from Last position---------------------");
        linkedList.removeFromLast();
        linkedList.display();
    }
}

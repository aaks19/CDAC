class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class CircularLinkedList {
    Node head;

    // Add at first
    public void addAtFirst(int data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
            newNode.next = head;
            return;
        }

        Node temp = head;
        while (temp.next != head) {
            temp = temp.next;
        }

        newNode.next = head;
        temp.next = newNode;
        head = newNode;
    }

    // Add at last
    public void addAtEnd(int data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
            newNode.next = head;
            return;
        }

        Node temp = head;
        while (temp.next != head) {
            temp = temp.next;
        }

        temp.next = newNode;
        newNode.next = head;
    }

    // Add at specific position (1-based)
    public void addAtPosition(int data, int position) {
        if (position == 1) {
            addAtFirst(data);
            return;
        }

        Node newNode = new Node(data);
        Node temp = head;

        for (int i = 1; i < position - 1 && temp.next != head; i++) {
            temp = temp.next;
        }

        newNode.next = temp.next;
        temp.next = newNode;
    }

    // Delete from first
    public void deleteFromFirst() {
        if (head == null)
            return;

        if (head.next == head) {
            head = null;
            return;
        }

        Node temp = head;
        while (temp.next != head) {
            temp = temp.next;
        }

        head = head.next;
        temp.next = head;
    }

    // Delete from last
    public void deleteFromLast() {
        if (head == null)
            return;

        if (head.next == head) {
            head = null;
            return;
        }

        Node temp = head;
        while (temp.next.next != head) {
            temp = temp.next;
        }

        temp.next = head;
    }

    // Delete from specific position (1-based)
    public void deleteFromPosition(int position) {
        if (head == null)
            return;

        if (position == 1) {
            deleteFromFirst();
            return;
        }

        Node temp = head;
        for (int i = 1; i < position - 1 && temp.next != head; i++) {
            temp = temp.next;
        }

        if (temp.next != head) {
            temp.next = temp.next.next;
        }
    }

    // Delete specific data
    public void deleteSpecificData(int data) {
        if (head == null)
            return;

        if (head.data == data) {
            deleteFromFirst();
            return;
        }

        Node temp = head;
        while (temp.next != head && temp.next.data != data) {
            temp = temp.next;
        }

        if (temp.next.data == data) {
            temp.next = temp.next.next;
        }
    }

    // Display list
    public void display() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        Node temp = head;
        do {
            System.out.print(temp.data + " ");
            temp = temp.next;
        } while (temp != head);
        System.out.println();
    }

    public static void main(String[] args) {
        CircularLinkedList cll = new CircularLinkedList();

        cll.addAtEnd(12);
        cll.addAtEnd(15);
        cll.addAtEnd(25);
        cll.addAtEnd(20);

        cll.display();

        cll.addAtFirst(5);
        cll.display();

        cll.addAtPosition(99, 3);
        cll.display();

        cll.deleteFromFirst();
        cll.display();

        cll.deleteFromLast();
        cll.display();

        cll.deleteFromPosition(2);
        cll.display();

        cll.deleteSpecificData(25);
        cll.display();
    }
}

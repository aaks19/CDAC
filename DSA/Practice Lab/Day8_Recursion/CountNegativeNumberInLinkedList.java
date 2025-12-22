class Node {
    int data;
    Node next;

    Node(int x) {
        data = x;
        next = null;
    }
}

public class CountNegativeNumberInLinkedList {
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

    static int countNegativeVal(Node head){
        int count = 0;
        Node temp = head;
        if(temp.data < 0){
            return count + countNegativeVal(temp.next);
        }
        return 0;
    }

    static void printLinkedList(Node head){
        Node temp = head;
        while(temp!=null){
            System.out.println(temp.data);
            temp=temp.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        CountNegativeNumberInLinkedList list = new CountNegativeNumberInLinkedList();
        list.addAtLast(1);
        list.addAtLast(-2);
        list.addAtLast(3);
        list.addAtLast(4);
        list.addAtLast(5);

        // Print the original list
        System.out.println("Original Linked List: ");
        printLinkedList(list.head);

        System.out.println("Negative element in linked list = "+countNegativeVal(list.head));

    
    }
}

class Node {
    int data;
    Node next;

    Node(int x) {
        data = x;
        next = null;
    }
}


public class ReverseLinkedList {
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

    Node reverseList(Node head) {
        // code here
        Node prev = null;
        Node current = head;
        while(current!=null){
            Node next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head=prev;
    return head;
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
        ReverseLinkedList list = new ReverseLinkedList();
        list.addAtLast(1);
        list.addAtLast(2);
        list.addAtLast(3);
        list.addAtLast(4);
        list.addAtLast(5);

        // Print the original list
        System.out.print("Original Linked List: ");
        printLinkedList(list.head);

        // Reverse the linked list
        list.head = list.reverseList(list.head);

        // Print the reversed list
        System.out.print("Reversed Linked List: ");
        printLinkedList(list.head);
    }
}
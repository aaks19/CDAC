class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class CircularLinkedList2 {

    Node head;

    // Insert at the beginning of the list
    public void insertAtFirst(int data) {
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

    // Insert at the end of the list
    public void insertAtLast(int data) {
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

    // Insert at a specific position
    public void insertAtPosition(int data, int pos) {
        Node newNode = new Node(data);
        if (pos == 1) {
            insertAtFirst(data);
            return;
        }
        int cnt = 1;
        Node temp = head;
        while (cnt < pos - 1) {
            cnt++;
            temp = temp.next;
        }
        newNode.next = temp.next;
        temp.next = newNode;
    }

    // Delete the first node
    public void deleteFirst() {
        if (head == null) {
            System.out.println("No element in linkedlist");
            return;
        }
        if (head.next == null) {
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

    // Delete the last node
    public void deleteLast() {
        if (head == null) {
            System.out.println("No element in the LinkedList");
            return;
        }
        if (head.next == null) {
            head = null;
            return;
        }

        Node temp = head;
        while (temp.next.next != head) {
            temp = temp.next;
        }
        temp.next = head;
    }

    // Delete node at a specific position
    public void deleteFromPosition(int pos) {
        if (pos == 1) {
            deleteFirst();
        }
        Node temp = head;
        int cnt = 1;

        while (cnt < pos - 1) {
            cnt++;
            temp = temp.next;
        }
        if (temp.next != head) {
            temp.next = temp.next.next;
        }
    }

    // Delete a specific value
    public void deleteSpecificValue(int data) {
        if (head == null) {
            System.out.println("No element in the LinkedList");
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

    // Reverse the circular linked list
    public void reverse() {
        if (head == null || head.next == head)
            return;

        Node prev = head;
        Node curr = head.next;
        Node next;

        while (curr != head) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // Fix circular link
        head.next = prev;
        head = prev;
    }

    // Update the value of a specific node
    public void updateValue(int oldData, int newData) {
        if (head == null) {
            System.out.println("No element in the LinkedList");
            return;
        }

        Node temp = head;
        do {
            if (temp.data == oldData) {
                temp.data = newData;  // Update the value of the node
                return;
            }
            temp = temp.next;
        } while (temp != head);  // Continue searching until you reach the head again

        // If we reach here, it means the value was not found
        System.out.println("Value not found in the list");
    }

    // Update value at a specific position
    public void updateAtPosition(int pos, int newData) {
        if (head == null) {
            System.out.println("No element in the LinkedList");
            return;
        }

        Node temp = head;
        int cnt = 1;
        
        // Traverse to the desired position
        while (cnt < pos) {
            temp = temp.next;
            cnt++;
            if (temp == head) {
                System.out.println("Position out of range");
                return;
            }
        }

        // Update the value at the found position
        temp.data = newData;
        System.out.println("Updated value at position " + pos);
    }

    // Display the circular linked list
    public void display() {
        if (head == null) {
            System.out.println("Linked List does not exist");
            return;
        }
        Node temp = head;
        do {
            System.out.print(temp.data + " ");
            temp = temp.next;
        } while (temp != head);
        System.out.println();
    }

    // Main method to test the Circular Linked List functionality
    public static void main(String[] args) {
        CircularLinkedList2 cll = new CircularLinkedList2();
        cll.insertAtFirst(10);
        cll.insertAtFirst(20);
        cll.insertAtFirst(30);
        cll.insertAtFirst(40);

        cll.insertAtLast(50);
        cll.insertAtLast(25);

        cll.insertAtPosition(100, 5);

        System.out.println("Circular Linked List:");
        cll.display();
        System.out.println();

        // Update value at position 5
        cll.updateAtPosition(5, 200);  // Update the value at position 5 to 200
        cll.display();  // Display after update

        // Try updating a non-existent position
        System.out.println("Trying to update a position out of range:");
        cll.updateAtPosition(10, 300);  // Should print "Position out of range"

        // Reverse the list and display
        cll.reverse();
        System.out.println("After reversing the list:");
        cll.display();
    }
}

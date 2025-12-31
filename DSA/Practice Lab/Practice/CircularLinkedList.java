class Node{
    int data;
    Node next;

    public Node(int data){
        this.data = data;
        this.next = null ;
    }
    
}

public class CircularLinkedList {
    
    Node head;

    public void insertAtFirst(int data){
        Node newNode = new Node(data);
        if(head == null){
            head = newNode;
            newNode.next = head;
            return;
        }

        Node temp = head;
        while(temp.next != head){
            temp = temp.next;
        }

        newNode.next = head;
        temp.next = newNode;
        head = newNode;
    }

    public void insertAtLast(int data){
        Node newNode = new Node(data);
        if(head == null){
            head = newNode;
            newNode.next = head;
            return;
        }

        Node temp = head;
        while(temp.next != head){
            temp = temp.next;
        }
        temp.next = newNode;
        newNode.next = head;
    }

    public void insertAtPosition(int data, int pos){
        Node newNode = new Node(data);
        if(pos == 1){
            insertAtFirst(data);
            return;
        }
        int cnt = 1;
        Node temp = head;
        while(cnt < pos-1){
            cnt++;
            temp = temp.next;
        }
        newNode.next = temp.next;
        temp.next = newNode;
    }

    public void deleteFirst(){
        if(head == null){
            System.out.println("No element in linkedlist");
            return;
        }
        if(head.next == null){
            head = null;
            return;
        }

        Node temp = head;
        while(temp.next != head){
            temp = temp.next;
        }
        head = head.next;
        temp.next = head;
    }

    public void deleteLast(){
        if(head == null){
            System.out.println("No element in the LinkedList");
            return;
        }
        if(head.next == null){
            head = null;
            return;
        }

        Node temp = head;
        while(temp.next.next != head){
            temp = temp.next;
        }
        temp.next = head;
    }

    public void deleteFromPosition(int pos){
        if(pos == 1){
            deleteFirst();
        }
        Node temp = head;
        int cnt = 1;

        while(cnt<pos-1){
            cnt++;
            temp = temp.next;
        }
        if(temp.next != head){
            temp.next = temp.next.next;
        }
    }

    public void deleteSpecificValue(int data){
        if(head == null){
            System.out.println("No element in the LinkedList");
            return;
        }
        Node temp = head;
        
        while(temp.next != head && temp.next.data != data){
            temp = temp.next;
        }
        if(temp.next.data == data){
            temp.next = temp.next.next;
        }
    }

    public void reverse(){
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

    public void display(){
        if(head == null){
            System.out.println("Linked List not exist");
            return;
        }
        Node temp = head;
        do { 
            System.out.print(temp.data + " ");
            temp=temp.next;
        } while (temp != head);
        System.out.println();
    }

    public static void main(String[] args) {
        CircularLinkedList cll = new CircularLinkedList();
        cll.insertAtFirst(10);
        cll.insertAtFirst(20);
        cll.insertAtFirst(30);
        cll.insertAtFirst(40);

        cll.insertAtLast(50);
        cll.insertAtLast(25);

        cll.insertAtPosition(100, 5);

        cll.display();
        System.out.println();

        // cll.deleteFirst();
        // cll.display();

        // cll.deleteLast();
        // cll.display();

        // cll.deleteFromPosition(3);

        // cll.deleteSpecificValue(100);
        // cll.display();

        cll.reverse();
        cll.display();
    }
}



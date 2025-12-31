class Node{
    int data;
    Node next;
    Node prev;
    
    public Node(int data){
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

public class DoublLinkedList {
    Node head;
    Node tail;

    public void insertAtFirst(int data){
        Node newNode = new Node(data);
        if(head == null){
            head = tail = newNode;
            return;
        }
        newNode.next = head;
        head.prev = newNode;
        head = newNode;
    }

    public void insertAtLast(int data){
        Node newNode = new Node(data);
        if(tail == null){
            head = tail = newNode;
            return;
        }
        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
    }

    public void insertAtPosition(int data , int pos){
        Node newNode = new Node(data);
        if(pos == 1){
            insertAtFirst(data);
            return;
        }

        Node temp = head;

        int cnt = 1;
        while(cnt<pos-1 && temp != null){
            cnt++;
            temp = temp.next;
        }

        if(temp == tail){
            insertAtLast(data);
            return;
        }
        newNode.next =temp.next;
        newNode.prev = temp;
        temp.next.prev = newNode;
        temp.next = newNode;
    }

    public void deleteFirst(){
        Node temp = head;
        if(head == null){
            System.out.println("No element...");
        }
        if(head == tail){
            head = tail = null;
            return;
        }

        head = temp.next;
        temp.next = null;
        temp.prev = null;
    }

    public void deleteLast(){
        if(tail == null){
            System.out.println("No element");
        }
        if(head == tail){
            head = tail = null;
            return;
        }

        tail = tail.prev;
        tail.next.prev = null;
        tail.next = null;
    }

    public void deleteAtPosition(int pos){
        if(pos == 1){
            deleteFirst();
            return;
        }

        int cnt = 1;
        Node temp = head;
        while(cnt<pos-1 && temp!=null){
            cnt++;
            temp = temp.next;
        }

        if(temp.next == tail){
            deleteLast();
            return;
        }

        Node nodeToDelete = temp.next;
        temp.next = nodeToDelete.next;
        nodeToDelete.next.prev = temp;
    }

    public void deleteSpecificVal(int data){
        if(head == null){
            System.out.println("No Element...");
        }

        if(head.data == data){
            deleteFirst();
            return;
        }
        Node temp = head;
        while(temp.next != null && temp.next.data != data){
            temp = temp.next;
        }

        if(temp.next == tail){
            deleteLast();
            return;
        }

        Node nodeToDelete = temp.next;
        temp.next = nodeToDelete.next;
        nodeToDelete.next.prev = temp;
    }

    public void reverse(){
        if(tail == null){
            System.out.println("No Element...");
        }
        Node temp = tail;
        while(temp!=null){
            System.out.print(temp.data+" ");
            temp = temp.prev;
        }
        System.out.println();
    }

    public void display(){
        Node temp = head;
        while(temp != null){
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DoublLinkedList dll = new DoublLinkedList();
        dll.insertAtFirst(10);
        dll.insertAtFirst(20);
        dll.insertAtFirst(50);
        dll.insertAtFirst(60);

        dll.insertAtLast(80);
        dll.insertAtLast(90);

        dll.display();

        dll.insertAtPosition(100,4);

        // dll.deleteFirst();

        // dll.deleteLast();
    
        dll.display();

        // dll.deleteAtPosition(5);
        // dll.deleteSpecificVal(90);
        dll.reverse();
        dll.display();


    }
}

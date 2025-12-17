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



public class DoublyLinkedList {
    Node head;
    Node tail;

    public void insertAtHead(int data){
        Node newNode = new Node(data);
        if(head == null){
            head = tail = newNode;
            return;
        }
        newNode.next = head;
        head.prev = newNode;
        head = newNode; 
    }

    public void insertAtTail(int data){
        Node newNode = new Node(data);
        if(tail==null){
            head = tail = newNode;
            return;
        }
        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
    }

    public void insertAtPosition(int data, int position){
        Node newNode = new Node(data);
        if(position == 0){
            insertAtHead(data);
            return;
        }
        Node current = head;
        for(int i=0;i<position-1 && current!=null ;i++){
            current = current.next;
        }

        if(current == null){
            System.out.println("invalid position");
            return;
        }
        if(current == tail){
            insertAtTail(data);
            return;
        }

        newNode.next = current.next;
        newNode.prev = current;
        current.next.prev = newNode;
        current.next = newNode;
    }

    public void deleteHead(){
        Node temp = head;

        if(head==null){
            System.out.println("Douly linked list not exist");
            return;
        }
        
        if(head == tail){
            head= tail = null;
            return;
        }

        head = temp.next;
        temp.next = null;
        head.prev = null;
    }

    public void deleteTail(){
        if(tail == null){
            System.out.println("Doubly Linked not exist");
            return;
        }

        if(head == tail){
            head= tail = null;
        }

        Node temp = tail;
        tail = temp.prev;
        tail.next = null;
        temp.prev = null;

    }

    public void deleteAtPosition(int position){
        if(position == 0){
            deleteHead();
            return;
        }
        Node current = head;
        for(int i=0; i< position-1 && current != null ; i++){
            current = current.next;
        }

        if(current == tail){
            deleteTail();
            return;
        }

        Node nodeToDelete = current.next;
        current.next = nodeToDelete.next;
        nodeToDelete.next.prev = current;
        nodeToDelete.prev = null;
        nodeToDelete.next = null;

    }

    public void deleteSpecificValue(int value){
        if(head == null){
            System.out.println("Doubly Linked list not exist");
            return;
        }
        if(head.data == value){
            deleteHead();
        }
        Node current = head;
        while(current.data != value && current!=null){
            current = current.next;
        }
        Node prevNode = current.prev;
        Node nextNode = current.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        current.next = null;
        current.prev = null;

    }

    public void display(){
        Node temp = head;
        while(temp!=null){
            System.out.print(temp.data+"  ");
            temp = temp.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DoublyLinkedList doublyLinkedList= new DoublyLinkedList();

        System.out.println("--------------------Insert at Head----------------------------");
        doublyLinkedList.insertAtHead(10);
        doublyLinkedList.insertAtHead(20);
        doublyLinkedList.insertAtHead(30);
        doublyLinkedList.insertAtHead(40);
        doublyLinkedList.insertAtHead(50);
        doublyLinkedList.insertAtHead(60);

        doublyLinkedList.display();

        System.out.println("-----------------------Insert at Tail-------------------------");
        doublyLinkedList.insertAtTail(90);
        doublyLinkedList.insertAtTail(19);

        doublyLinkedList.display();


        System.out.println("-----------------------Insert at Index-------------------------");
        doublyLinkedList.insertAtPosition(120, 30);
        
        doublyLinkedList.display();


        // System.out.println("-----------------------Delete Head-------------------------");
        // doublyLinkedList.deleteHead();

        // doublyLinkedList.display();


        // System.out.println("-----------------------Delete Tail-------------------------");
        // doublyLinkedList.deleteTail();

        // doublyLinkedList.display();

        // System.out.println("-----------------------Delete at Position-------------------------");
        // doublyLinkedList.deleteAtPosition(3);
        // // doublyLinkedList.deleteAtPosition(0);
        // // doublyLinkedList.deleteAtPosition(5);

        // doublyLinkedList.display();


        // System.out.println("-----------------------Delete specific Value-------------------------");
        // doublyLinkedList.deleteSpecificValue(120);

        // doublyLinkedList.display();


    }
}



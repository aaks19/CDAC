class Node{
    int data;
    Node next;
    public Node(int data){
        this.data = data;
        this.next = null;
    }
}

public class LinkedList {
    
    Node head;

    public void addAtFirst(int data){
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    public void addAtLast(int data){
        Node newNode = new Node(data);

        if(head == null){
            head = newNode;
            return;
        }
        Node temp = head;
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = newNode;
    }

    public void insertAtPosition(int data , int pos){
        Node newNode = new Node(data);
        Node temp = head;
        int cnt = 1;
        while(cnt<pos-1){
            cnt++;
            temp = temp.next;
        }
        Node next1 = temp.next;
        temp.next = newNode;
        newNode.next = next1;
    }

    public int removeFromFirst(){
        if(head == null){
            System.out.println("No element in linked list");
        }
        int val = head.data;
        head = head.next;
        return val;
    }

    public int removeFromLast(){
        if(head == null){
            System.out.println("No element in linked list");
        }
        int val;
        if(head.next == null){
            val = head.data;
            head = null;
        }
        Node temp = head;
        while(temp.next.next != null){
            temp = temp.next;
        }
        val = temp.next.data;
        temp.next = null;
        return val;
    }

    public void revomeFromPosition(int pos){
        Node temp = head;
        int cnt = 1;
        if(pos == 1){
            head = temp.next;
        }
        while(cnt < pos-1){
            cnt++;
            temp = temp.next;
        }
        temp.next = temp.next.next;
    }

    public void removeSpecificVal(int data){
        Node temp = head;
        if(head.data == data){
            head = head.next;
            return;
        }
        while(temp.next != null && temp.next.data != data){
            temp = temp.next;
        }

        temp.next = temp.next.next;
    }

    public void display(){
        Node temp = head;
        while(temp != null){
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.addAtFirst(10);
        list.addAtFirst(20);
        // list.display();

        list.addAtLast(50);
        // list.display();

        list.insertAtPosition(100, 1);
        // list.display();

        // System.out.println("Removeed element : "+list.removeFromFirst());
        // list.display();

        // System.out.println("Remove from last emelent : "+ list.removeFromLast());
        // list.display();

        // list.revomeFromPosition(3);
        // list.display();

        list.removeSpecificVal(20);
        list.display();

    }
}

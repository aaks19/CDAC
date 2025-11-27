class Node{
    int data;
    Node next;
    public Node(int data){
        this.data = data;
        this.next = null;
    }
}

public class CircularLinkedList {
    Node head;
    public void addAtEnd(int data){
        Node newNode = new Node(data);  
        if(head == null){
            head = newNode;
            newNode.next = head;
        }else{
            Node temp = head;
            do{
                temp = temp.next;
            }while(temp.next!=null);
        }
    }

    public void display(){
        Node temp = head;
        while(temp!=null){
            System.out.println(temp.data);
            temp=temp.next;
        }
        System.out.println();
    }


    public static void main(String[] args) {
        CircularLinkedList circularLinkedList = new CircularLinkedList();
        circularLinkedList.addAtEnd(12);
        circularLinkedList.addAtEnd(15);
        circularLinkedList.addAtEnd(25);
        circularLinkedList.addAtEnd(20);
        circularLinkedList.addAtEnd(36);
        circularLinkedList.addAtEnd(50);

        circularLinkedList.display();
    }
}

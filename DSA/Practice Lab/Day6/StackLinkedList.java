class Node{
    int data;
    Node next;

    public Node(int data){
        this.data = data;
        this.next = null;
    }
}

class StackUsingLinkedList{
    Node top;

    public void addelement(int data){
        Node newNode = new Node(data);
        if(newNode == null){
            top = newNode;
        }else{
            newNode.next=top;
            top = newNode;
        }
    }

    public int removeElement(){
        if(isEmpty()){
            System.out.println("Stack is empty");
        }
        
        int data = top.data;
        top=top.next;
        return data;
    } 

    public boolean isEmpty(){
        return top==null;
    }

    public void display() {
        Node temp = top;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }
    public int peek(){
        return top.data;
    }
}

public class StackLinkedList{
    public static void main(String[] args) {
        
        StackUsingLinkedList stack = new StackUsingLinkedList();
        stack.addelement(12);
        stack.addelement(1);
        stack.addelement(15);
        stack.addelement(6);
        stack.addelement(51);
        stack.addelement(19);

        stack.display();
        System.out.println("Stack is empty : "+stack.isEmpty());
        System.out.println("Top value of Stack is (peek) : "+stack.peek());
        System.out.println("removed element : "+stack.removeElement());
        stack.display();
    }
}
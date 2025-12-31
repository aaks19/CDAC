public class Stack {
    int[] stackArr;
    int maxSize;
    int top;

    public Stack(int size){
        stackArr = new int[size];
        top = -1;
        maxSize = size; 
    }

    public void push(int data){
        if(isFull()){
            System.out.println("Stack Overflow");
            return;
        }
        stackArr[++top] = data;
    }

    public int pop(){
        if(isEmpty()){
            System.out.println("Stack is Empty");
            return -1;
        }
        return stackArr[top--];
    }

    public void display(){
        for(int i=top ; i>=0 ; i--){
            System.out.println(stackArr[i]+ " ");
        }
    }

    public boolean isFull(){
        return top == maxSize-1;
    }
    public boolean isEmpty(){
        return top == -1;
    }

    public static void main(String[] args) {
        Stack mystack = new Stack(5);
        mystack.push(10);
        mystack.push(20);
        mystack.push(30);
        mystack.push(40);
        mystack.push(50);
        mystack.push(50);
        mystack.display();
        System.out.println("Popped element is = "+mystack.pop());
        mystack.display();


    }

}

public class MyStack{
    int[] stackArray;
    int top;
    int maxSize;

    public MyStack(int size){
        stackArray = new int[size];
        top = -1;
        maxSize = size;
    }

    public void push(int data){
        if(isFull()){
            System.out.println("Stack is full");
            return;
        }
        stackArray[++top] = data;
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return -1; 
        }
        return stackArray[top--];
    }

    public boolean isEmpty(){
        return top == -1;
    }
    public boolean isFull(){
        return top == maxSize - 1;
    }

    public static void main(String[] args) {
        MyStack stack = new MyStack(3);
        stack.push(10);
        stack.push(20);
        stack.push(20);
        // stack.push(20);
        System.out.println("Popped element: " + stack.pop());
    
    }
}
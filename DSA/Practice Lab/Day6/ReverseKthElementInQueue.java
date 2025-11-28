
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


class ReverseKthElementInQueue {

    int front;
    int rear;
    int arr[];
    int size;

    public ReverseKthElementInQueue(int size){
        this.size = size;
        this.arr = new int[size];
    }


    public static void main(String[] args) {
        Deque<Integer> queue = new ArrayDeque<>();
        Deque<Integer> stack = new ArrayDeque<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);

        int k = 3;

        for(int i=0;i<k;i++){
            stack.push(queue.poll());
        }

        while(!stack.isEmpty()){
            queue.add(stack.pop());
        }

        for(int i=0 ; i<queue.size() - k ; i++){
            queue.add(queue.poll());
        }

        System.out.println(queue);
     }
}

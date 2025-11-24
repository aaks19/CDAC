
import java.util.ArrayDeque;
import java.util.Deque;

public class PostfixExpressionEvaluation {
    
    

    public static void main(String[] args) {
        Deque<Integer> stack = new ArrayDeque<>();
        char[] arr = {'2','3','1','*','+','9','-'};
        StringBuilder expression = new StringBuilder();
        for(char ch : arr){
            expression.append(ch);
        }
        for(int i=0;i<expression.length();i++){
            char ch = expression.charAt(i);
            if(Character.isDigit(ch)){
                stack.push(ch - '0');
            }else{
                int operand2 = stack.pop();
                int operand1 = stack.pop();
                int result = 0;
                switch(ch){
                    case '+':
                        result = operand1 + operand2;
                        break;
                    case '-':
                        result = operand1 - operand2;
                        break;
                    case '*':
                        result = operand1 * operand2;
                        break;
                    case '/':
                        result = operand1 / operand2;
                        break;
                }
                stack.push(result);
            }
        }
        System.out.println("Result of Postfix Expression Evaluation: "+stack.pop());
    }
}

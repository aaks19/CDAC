
import java.util.ArrayDeque;
import java.util.Deque;



public class BalancedParentheses {
    
    static boolean balancedParentheses(String str){
        Deque<Character> stack = new ArrayDeque<>();
        for(char c : str.toCharArray()){
            if(c=='(' || c=='{' || c=='['){
                stack.push(c);
            }
            else{
                if(stack.isEmpty()) return false;
                char openBracket = stack.pop();
                if(c ==')' && openBracket!='(') return false;
                if(c =='}' && openBracket!='{') return false;
                if(c ==']' && openBracket!='[') return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String exp = "[(])";
        System.out.println(balancedParentheses(exp));
    }
}

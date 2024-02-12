import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        while(true){
            char[] string = in.readLine().toCharArray();
            if (string.length == 1) break;
            Stack<Character> stack = new Stack<>();
            boolean isBalanced = true;
            for (char c : string){
                if (c == '(' || c == '['){
                    //시작하는 괄호. 집어넣는다.
                    stack.push(c);
                }
                else if (c == ')' || c == ']'){
                    //끝나는 괄호. 뺀다.
                    if (stack.isEmpty()){
                        isBalanced = false;
                        break;
                    }
                    else{
                        if ((c == ')' && stack.peek() == '(')
                        || (c == ']' && stack.peek() == '[')) stack.pop();
                        else{
                            isBalanced = false;
                            break;
                        }
                    }
                }
            }
            sb.append((isBalanced && stack.isEmpty())? "yes" : "no").append("\n");
        }
        System.out.println(sb);
    }
}
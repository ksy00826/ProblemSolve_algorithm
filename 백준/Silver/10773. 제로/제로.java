import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(in.readLine());
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < K; i++){
            int val = Integer.parseInt(in.readLine());
            if (val == 0) stack.pop();
            else stack.push(val);
        }
        int sum = 0;
        for (int num : stack){
            sum += num;
        }
        System.out.println(sum);
    }
}
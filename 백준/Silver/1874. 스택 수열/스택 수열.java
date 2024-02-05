import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(in.readLine());
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        boolean isBreak = false;
        int no = 1;
        for (int i = 1; i <= N; i++){
            int num = Integer.parseInt(in.readLine());

            //입력받은 숫자가 더 크거나 같으면 계속 푸시
            //stack의 peek이 입력받은 숫자이면 뺌
            //만약 위 둘 경우가 아니면 바로 NO
            while (num >= no){
                stack.push(no);
                no++;
                sb.append("+").append("\n");
            }
            if (stack.peek() == num){
                stack.pop();
                sb.append("-").append("\n");
            }
            else{
                isBreak = true;
                break;
            }
        }
        System.out.println((isBreak)? "NO" : sb);



    }
}
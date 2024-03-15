import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] dp = new int[N];
        Arrays.fill(dp, 1);
        for (int i = 1; i < N; i++){
            for (int j = 0; j < i; j++){
                if (arr[i] > arr[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
//        System.out.println(Arrays.toString(dp));

        StringBuilder sb = new StringBuilder();
        int longLen = 0;
        for (int i = 0; i < N; i++){
            longLen = Math.max(dp[i], longLen);
        }
        sb.append(longLen).append("\n");

        Stack<Integer> stack = new Stack<>();
        for (int i = N-1; i >= 0; i--){
            if (dp[i] == longLen) {
                stack.add(arr[i]);
                longLen--;
            }
        }
        while(!stack.isEmpty()){
            sb.append(stack.pop()).append(" ");
        }
        System.out.println(sb);
    }
}
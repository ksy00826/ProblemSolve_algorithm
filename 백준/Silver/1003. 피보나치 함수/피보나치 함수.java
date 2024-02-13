import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int zeroCnt, oneCnt;
    static int[][] memo;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        StringBuilder sb = new StringBuilder();

        //fibo
        memo = new int[41][2];
        for (int i = 0; i < 41; i++){
            Arrays.fill(memo[i], Integer.MAX_VALUE);
        }
        memo[0][0] = 1;
        memo[0][1] = 0;
        memo[1][0] = 0;
        memo[1][1] = 1;
//        memo[1] = 1;
//        for (int i = 2; i < 41; i++){
//            memo[i] = memo[i-1] + memo[i-2];
//        }

        while(T-- > 0){
            int n = Integer.parseInt(in.readLine());
            zeroCnt = 0;
            oneCnt = 0;
            memo[n] = fibo(n);
            sb.append(memo[n][0]).append(" ").append(memo[n][1]).append("\n");
        }
        System.out.println(sb);
    }

    private static int[] fibo(int n) {
        if (n == 0){
            return new int[]{1, 0};
        }
        else if (n == 1){
            return new int[]{0, 1};
        }
        else{
            if (memo[n-1][0] == Integer.MAX_VALUE){
                memo[n-1] = fibo(n-1);
            }
            if (memo[n-2][0] == Integer.MAX_VALUE){
                memo[n-2] = fibo(n-2);
            }
            return new int[]{memo[n-1][0] + memo[n-2][0], memo[n-1][1] + memo[n-2][1]};
        }

    }
}
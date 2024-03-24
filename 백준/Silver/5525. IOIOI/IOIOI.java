import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int M = Integer.parseInt(in.readLine());
        String S = in.readLine();

        int[] dp = new int[M];
        int cnt = 0;
        for (int i = 2; i < M; i++){
            if (S.charAt(i) == 'I' && S.charAt(i-1) == 'O' && S.charAt(i-2) == 'I'){
                dp[i] = dp[i-2] + 1;
                if (dp[i] >= N) cnt++;
            }
        }
        System.out.println(cnt);
    }
}
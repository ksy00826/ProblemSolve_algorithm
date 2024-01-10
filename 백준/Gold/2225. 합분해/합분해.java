import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        //init
        long[][] dp = new long[N+1][K+1];
        for (int n = 0; n < N+1; n++){
            dp[n][1] = 1;
        }
        for (int k = 0; k < K+1; k++){
            dp[1][k] = k;
        }

        //logic
        for (int n = 2; n < N+1; n++){
            for (int k = 2; k < K+1; k++){
                dp[n][k] = dp[n][k-1] + dp[n-1][k] % 1000000000;
            }
        }

        System.out.println(dp[N][K] % 1000000000);
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] coins = new int[N];
        for (int i = 0; i < N; i++){
            coins[i] = Integer.parseInt(in.readLine());
        }

        //dp logic
        int[] dp = new int[K+1];
        for (int j = 1; j < K+1; j++){
            dp[j] = 1000000;
        }
        for (int n = 1; n < N+1; n++){
            for (int k = coins[n-1]; k < K+1; k++){
                dp[k] = Math.min(dp[k-coins[n-1]] + 1, dp[k]);
            }
        }

//        System.out.println(Arrays.toString(dp));
        System.out.println((dp[K] == 1000000)? -1 : dp[K]);
    }
}
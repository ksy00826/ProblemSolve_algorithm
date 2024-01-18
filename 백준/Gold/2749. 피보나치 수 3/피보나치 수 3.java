import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(in.readLine());
        int p = 1500000;
        int[] dp = new int[p];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i < p; i++){
            dp[i] = (dp[i-1] + dp[i-2]) % 1000000;
        }
        System.out.println(dp[(int) (N % p)]);
    }
}
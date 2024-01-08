import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(in.readLine());
        int[] dp = new int[N+1];

        dp[0] = 1;

        for (int i = 2; i <= N; i+=2){ //짝수만 유효함
            for (int diff = 2;; diff+=2){
                if (i - diff < 0) break;

//                System.out.println(i + ": " + (i - diff));
                if (diff == 2) dp[i] += dp[i-diff] * 3;
                else dp[i] += dp[i-diff] * 2;
            }
        }
//        System.out.println(Arrays.toString(dp));
        System.out.println(dp[N]);
    }
}
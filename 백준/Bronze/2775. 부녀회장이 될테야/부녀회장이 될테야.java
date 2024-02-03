import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());

        StringBuilder sb = new StringBuilder();
        while(T-- > 0){
            int k = Integer.parseInt(in.readLine());
            int n = Integer.parseInt(in.readLine());
            //k층 n호
            int[][] dp = new int[k+1][n+1];
            for (int j = 0; j < n+1; j++){
                dp[0][j] = j;
            }

            for (int i = 1; i < k+1; i++){
                for (int j = 1; j < n+1; j++){
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
            sb.append(dp[k][n]).append("\n");
        }
        System.out.println(sb);
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] costSum;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        StringBuilder sb = new StringBuilder();
        while(T-- > 0){
            int N = Integer.parseInt(in.readLine()); //3~
            costSum = new int[N+1];
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 1; i < N+1; i++){
                costSum[i] = Integer.parseInt(st.nextToken()) + costSum[i-1];
            }

            int[][] dp = new int[N+1][N+1];
            //출발지 i, 목적지 j, 중간에 나눠지는 부분 mid
            for (int j = 2; j <= N; j++){
                for (int i = j-1; i >= 1; i--){
                    dp[i][j] = Integer.MAX_VALUE;
                    for (int s = i; s < j; s++){
                        dp[i][j] = Math.min(dp[i][j], dp[i][s] + dp[s+1][j]);
                    }
                    dp[i][j] += (costSum[j] - costSum[i-1]);
                }
            }
            sb.append(dp[1][N]).append("\n");
        }
        System.out.println(sb);
    }
}
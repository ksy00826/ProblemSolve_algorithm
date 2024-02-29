import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] colorCost;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        colorCost = new int[N][3];
        for (int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int j = 0; j < 3; j++){
                colorCost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = Integer.MAX_VALUE;
        int INF = Integer.MAX_VALUE - 1000;
        dp = new int[N][3];

        //0번 집이 R
        dp[0][0] = colorCost[0][0];
        dp[0][1] = INF;
        dp[0][2] = INF;
        dynamicUpdate();
        answer = Math.min(answer, Math.min(dp[N-1][1], dp[N-1][2]));

        //0번 집이 G
        dp[0][0] = INF;
        dp[0][1] = colorCost[0][1];
        dp[0][2] = INF;
        dynamicUpdate();
        answer = Math.min(answer, Math.min(dp[N-1][0], dp[N-1][2]));

        //0번 집이 B
        dp[0][0] = INF;
        dp[0][1] = INF;
        dp[0][2] = colorCost[0][2];
        dynamicUpdate();
        answer = Math.min(answer, Math.min(dp[N-1][0], dp[N-1][1]));

        System.out.println(answer);
    }

    private static void dynamicUpdate() {
        for (int i = 1; i < N; i++){
            dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + colorCost[i][0];
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + colorCost[i][1];
            dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + colorCost[i][2];
        }
    }

}
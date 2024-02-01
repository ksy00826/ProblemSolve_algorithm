import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class App{
        int memory;
        int cost;
        public App(int memory, int cost){
            this.memory = memory;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        App[] A = new App[N+1];
        st = new StringTokenizer(in.readLine());
        StringTokenizer st2 = new StringTokenizer(in.readLine());
        for (int i = 1; i < N+1; i++){
            A[i] = new App(Integer.parseInt(st.nextToken()), Integer.parseInt(st2.nextToken()));
        }

        int[][] dp = new int[N+1][100001];
        int minCost = Integer.MAX_VALUE;
        for (int i = 1; i < N+1; i++){
            for (int j = 0; j < 100001; j++){
                dp[i][j] = dp[i-1][j];
                if (j >= A[i].cost)
                    dp[i][j] = Math.max(A[i].memory + dp[i-1][j-A[i].cost], dp[i][j]);

                if (dp[i][j] >= M) minCost = Math.min(minCost, j);
            }
        }
        System.out.println(minCost);
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static long[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        map = new int[N][N];
        dp = new long[N][N];
        for (int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //dp
        dp[0][0] = 1;
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                //위쪽
                int cnt = 1;
                for (int up = i-1; up >= 0; up--){
                    if (cnt == map[up][j]) dp[i][j] += dp[up][j];
                    cnt++;
                }
                //왼쪽
                cnt = 1;
                for (int left = j-1; left >= 0; left--){
                    if (cnt == map[i][left]) dp[i][j] += dp[i][left];
                    cnt++;
                }
            }
        }

//        for (int i = 0; i < N; i++){
//            System.out.println(Arrays.toString(dp[i]));
//        }
        System.out.println(dp[N-1][N-1]);
    }

}
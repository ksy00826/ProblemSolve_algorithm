import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N, K;
    static int P = 1000000003;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        K = Integer.parseInt(in.readLine());

        //dp
        int[][] dp = new int[N+1][K+1]; //0~N, (첫 번째 원소를 선택하지 않은 경우(0)와 선택한 경우(1))
        for (int i = 0; i <= N; i++){
            dp[i][0] = 1; //점화식에 따라
            dp[i][1] = i; //i개의 색상에서 1개를 뽑는 경우의 수는 i가지
        }
        for (int n = 2; n < N; n++){
            for (int k = 2; k <= K; k++){
                dp[n][k] = (dp[n-2][k-1] + dp[n-1][k]) % P;
            }
        }
        System.out.println((dp[N-3][K-1] + dp[N-1][K]) % P);
    }
}
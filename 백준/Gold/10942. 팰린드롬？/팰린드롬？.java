import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int[] nums = new int[N];
        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        //dp?
        boolean[][] dp = new boolean[N][N];
        for (int j = 0; j < N; j++){
            for (int i = j; i >= 0; i--){
                //1자리일 때
                if (i == j) dp[i][j] = true;
                //처음과 끝이 같은지, 내부가 팰린드롬인지 파악
                else if (nums[i] == nums[j]) {
                    if (j - i == 1) dp[i][j] = true;
                        //두 자리 이상인 경우 내부가 펠린드롬인지 체크
                    else if (dp[i + 1][j - 1]) dp[i][j] = true;
                }
            }
        }

        int M = Integer.parseInt(in.readLine());
        StringBuilder sb = new StringBuilder();
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            sb.append((dp[s-1][e-1])? 1 : 0).append("\n");
        }
        System.out.println(sb);
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
    static class Pos{
        int r, c;
        public Pos(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        char[] str1 = in.readLine().toCharArray();
        char[] str2 = in.readLine().toCharArray();

        int N = str1.length;
        int M = str2.length;
        int[][] dp = new int[N][M];
        boolean flag = false;
        for (int i = 0; i < N; i++){
            if (str2[0] == str1[i]) flag = true;
            if (flag) dp[i][0] = 1;
        }
        flag = false;
        for (int j = 0; j < M; j++){
            if (str1[0] == str2[j]) flag = true;
            if (flag) dp[0][j] = 1;
        }

        for (int i = 1; i < N; i++){
            for (int j = 1; j < M; j++){
                if (str1[i] == str2[j]) dp[i][j] = dp[i-1][j-1]+1;
                else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
//        for (int i = 0; i < N; i++){
//            System.out.println(Arrays.toString(dp[i]));
//        }

        StringBuilder sb = new StringBuilder();
        int len = dp[N-1][M-1];
        if (len == 0){
            System.out.println(len);
            return;
        }
        //lcs 수열 구하기
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(N-1, M-1));
        int[] dr = {-1, 0};
        int[] dc = {0, -1}; //상, 좌
        while(!q.isEmpty()){
            Pos cur = q.poll();

            boolean canGo = false;
            for (int di = 0; di < 2; di++){
                int nr = cur.r + dr[di];
                int nc = cur.c + dc[di];
                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;

                if (dp[cur.r][cur.c] == dp[nr][nc]){
                    canGo = true;
                    q.offer(new Pos(nr, nc));
                    break; //괜찮겠지?
                }
            }
            if (!canGo) {
                sb.append(str1[cur.r]);
                if (dp[cur.r][cur.c] == 1) break;
                q.offer(new Pos(cur.r-1, cur.c-1));
            }
        }

        System.out.println(len);
        System.out.println(sb.reverse());
    }
}
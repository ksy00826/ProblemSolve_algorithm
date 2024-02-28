import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Pos{
        int r, c;
        int depth;
        public Pos(int r, int c, int depth){
            this.r = r;
            this.c = c;
            this.depth = depth;
        }
    }
    static int N, M, K;
    static char[][] map;
    static String target;
    static int[][][] memo;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        for (int i = 0; i < N; i++){
            map[i] = in.readLine().toCharArray();
        }
        target = in.readLine();

        memo = new int[N][M][target.length()];
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                Arrays.fill(memo[i][j], -1);
            }
        }
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                if (map[i][j] == target.charAt(0)){
                    dfs(i, j, 0);
                }
            }
        }

        int sum = 0;
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                if (memo[i][j][0] != -1) sum += memo[i][j][0];
            }
        }
        System.out.println(sum);

    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    private static int dfs(int i, int j, int pos) {
        for (int k = 1; k <= K; k++){
            for (int di = 0; di < 4; di++){
                int nr = i + dr[di]*k;
                int nc = j + dc[di]*k;
                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;

                int nextPos = pos + 1;
                if (map[nr][nc] == target.charAt(nextPos)){
                    if (nextPos == target.length()-1) {
                        memo[nr][nc][nextPos] = 1;
                    }
                    else if (memo[nr][nc][nextPos] == -1){
                        memo[nr][nc][nextPos] = 0;
                        memo[nr][nc][nextPos] += dfs(nr, nc, nextPos);
                    }
                    if (memo[i][j][pos] == -1) memo[i][j][pos] = 0;
                    memo[i][j][pos] += memo[nr][nc][nextPos];
                }
            }
        }
        return memo[i][j][pos];
    }

}
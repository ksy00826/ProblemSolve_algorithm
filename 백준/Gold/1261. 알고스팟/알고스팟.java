import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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
    static int N, M;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++){
            String[] split = in.readLine().split("");
            for (int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(split[j]);
            }
        }

//        dp = new int[N][M];
//        for (int i = 0; i < N; i++){
//            Arrays.fill(dp[i], Integer.MAX_VALUE -1);
//        }
        bfs(0, 0);
//        for (int i = 0; i < N; i++){
//            System.out.println(Arrays.toString(dp[i]));
//        }
//        System.out.println(dp[N-1][M-1]);
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] dp;
    private static void bfs(int i, int j) {
        PriorityQueue<Pos> q = new PriorityQueue<>(new Comparator<Pos>() {
            @Override
            public int compare(Pos o1, Pos o2) {
                return o1.depth - o2.depth;
            }
        });
        boolean[][] visited = new boolean[N][M];
        q.offer(new Pos(i, j, 0));
        visited[i][j] = true;

        while(!q.isEmpty()){
            Pos cur = q.poll();
            if ((cur.r == N-1) && (cur.c == M-1)){
                System.out.println(cur.depth);
                return;
            }

            for (int di = 0; di < 4; di++){
                int nr = cur.r + dr[di];
                int nc = cur.c + dc[di];
                if (nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc]) continue;

                visited[nr][nc] = true;
                if (map[nr][nc] == 1) q.offer(new Pos(nr, nc, cur.depth + 1));
                else q.offer(new Pos(nr, nc, cur.depth));
            }
        }
    }
}
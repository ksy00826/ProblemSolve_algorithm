import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Pos{
        int r, c;
        int depth;
        public Pos(int r, int c){
            this.r = r;
            this.c = c;
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

        zeroOnebfs(new Pos(0, 0));
        System.out.println(dist[N-1][M-1]);
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] dist;
    private static void zeroOnebfs(Pos start) {
        //dec, dist
        Deque<Pos> dec = new ArrayDeque();
        dist = new int[N][M];
        for (int i = 0; i < N; i++){
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[start.r][start.c] = 0;
        dec.add(start);

        while(!dec.isEmpty()){
            Pos cur = dec.pollFirst();

            for (int di = 0; di < 4; di++){
                int nr = cur.r + dr[di];
                int nc = cur.c + dc[di];
                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;

                //거리가 갱신되면
                if (dist[nr][nc] > dist[cur.r][cur.c] + map[nr][nc]){
                    dist[nr][nc] = dist[cur.r][cur.c] + map[nr][nc];
                    if (map[nr][nc] == 0) dec.addFirst(new Pos(nr, nc));
                    else dec.addLast(new Pos(nr, nc));
                }
            }
        }
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main {
    static class Pos{
        int r, c;
        public Pos(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    static int N;
    static int[][] map;
    static int[][] dist;
    static int INF = Integer.MAX_VALUE - 50 * 50;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++){
            String[] split = in.readLine().split("");
            for (int j = 0; j < N; j++){
                map[i][j] = (Integer.parseInt(split[j]) + 1) % 2;
            }
        }

        dist = new int[N][N];
        for (int i = 0; i < N; i++){
            Arrays.fill(dist[i], INF);
        }
        zeroOneBfs(new Pos(0, 0));

        System.out.println(dist[N-1][N-1]);
    }

    private static void zeroOneBfs(Pos pos) {
        //dist, dec
        Deque<Pos> dec = new ArrayDeque<>();
        dec.offer(pos);
        dist[pos.r][pos.c] = 0;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = { 0, 0, -1, 1};

        while(!dec.isEmpty()){
            Pos front = dec.pollFirst();

            for (int di = 0; di < 4; di++){
                int nr = front.r + dr[di];
                int nc = front.c + dc[di];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

                if (dist[nr][nc] > dist[front.r][front.c] + map[nr][nc]){
                    dist[nr][nc] = dist[front.r][front.c] + map[nr][nc];
                    if (map[nr][nc] == 0) dec.addFirst(new Pos(nr, nc));
                    else dec.addLast(new Pos(nr, nc));
                }
            }
        }
    }
}
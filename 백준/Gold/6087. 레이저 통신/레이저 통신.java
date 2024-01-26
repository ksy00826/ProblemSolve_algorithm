import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Pos{
        int r, c;
        int preDir;
        int changeCnt;
        public Pos(int r, int c){
            this.r = r;
            this.c = c;
        }
        public Pos(int r, int c, int preDir, int changeCnt){
            this.r = r;
            this.c = c;
            this.preDir = preDir;
            this.changeCnt = changeCnt;
        }
    }
    static int H, W;
    static char[][] map;
    static Pos start = null, end = null;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new char[H][W];
        for (int i = 0; i < H; i++){
            map[i] = in.readLine().toCharArray();
            for (int j = 0; j < W; j++){
                if (map[i][j] == 'C'){
                    if (start == null) start = new Pos(i, j);
                    else end = new Pos(i, j);
                }
            }
        }

        int[][][] dist = new int[4][H][W];
        for (int di = 0; di < 4; di++){
            for (int i = 0; i < H; i++){
                Arrays.fill(dist[di][i], INF);
            }
            dijkstra(di, dist[di]);
        }
        System.out.println(
                Math.min(dist[0][end.r][end.c],
                        Math.min(dist[1][end.r][end.c],
                                Math.min(dist[2][end.r][end.c], dist[3][end.r][end.c])))
        );
    }
    static int INF = Integer.MAX_VALUE - 100;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static void dijkstra(int startDi, int[][] dist){
        //pq, dist, visited
        PriorityQueue<Pos> pq = new PriorityQueue<>(new Comparator<Pos>() {
            @Override
            public int compare(Pos o1, Pos o2) {
                return o1.changeCnt - o2.changeCnt;
            }
        });
        boolean[][] visited = new boolean[H][W];
        pq.offer(new Pos(start.r, start.c, startDi, 0));
        dist[start.r][start.c] = 0;

        while(!pq.isEmpty()){
            Pos cur = pq.poll();
            if (visited[cur.r][cur.c]) continue;

            visited[cur.r][cur.c] = true;
            for (int di = 0; di < 4; di++){
                int curDi = (di + startDi) % 4;
                int nr = cur.r + dr[curDi];
                int nc = cur.c + dc[curDi];
                if (nr < 0 || nr >= H || nc < 0 || nc >= W || map[nr][nc] == '*') continue;

                int cost = (cur.preDir != curDi)? 1 : 0;
                if (dist[nr][nc] > dist[cur.r][cur.c] + cost){
                    dist[nr][nc] = dist[cur.r][cur.c] + cost;
                    pq.offer(new Pos(nr, nc, curDi, dist[nr][nc]));
                }
            }
        }
    }
}
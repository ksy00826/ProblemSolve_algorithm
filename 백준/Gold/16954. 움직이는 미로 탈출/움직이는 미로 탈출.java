import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    static class Pos{
        int r, c;
        int time;
        public Pos(int r, int c, int time){
            this.r = r;
            this.c = c;
            this.time = time;
        }
    }
    static int N = 8;
    static char[][] map;
    static int[] dr = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dc = {0, 0, -1, 1, -1, 1, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        map = new char[N][N];
        for (int i = 0; i < N; i++){
            map[i] = in.readLine().toCharArray();
        }

        //dfs하면 끝이 안 날 거 같다.
        //bfs로 해보자! bfs로 하면 최소한 하나만 끝까지 가면 끝이니까!
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(7, 0, 0));
        int currentTime = 0;
        while(!q.isEmpty()){
            //1. 이동한다 - 8방 + 그대로
            while(!q.isEmpty() && q.peek().time == currentTime){
                Pos cur = q.poll();

                for (int di = 0; di < 8; di++){
                    int nr = cur.r + dr[di];
                    int nc = cur.c + dc[di];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= N
                    || map[nr][nc] == '#' || (nr - 1 >= 0 && map[nr-1][nc] == '#')) continue;

                    if (nr == 0 && nc == 7){
                        System.out.println(1);
                        return;
                    }
                    q.offer(new Pos(nr, nc, cur.time+1));
                }
                if (cur.r - 1 >= 0 && map[cur.r-1][cur.c] == '#') continue;
                q.offer(new Pos(cur.r, cur.c, cur.time+1));
            }

            //2. 벽을 내린다.
            for (int i = N-1; i > 0; i--){
                for (int j = 0; j < N; j++){
                    map[i][j] = map[i-1][j];
                }
            }
            for (int j = 0; j < N ;j++){
                map[0][j] = '.';
            }
            currentTime++;
        }
        System.out.println(0);
    }
}
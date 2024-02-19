import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    static class Pos{
        int r, c;
        int cnt;
        int dir;
        public Pos(int r, int c, int cnt, int dir){
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.dir = dir;
        }
    }
    static int N;
    static char[][] map;
    static boolean[][][] visited;
    static int startR = -1, startC, endR, endC;
    static int[] dr = {-1, 0, 1, 0};//상0좌1하2우3 -> 0101
    static int[] dc = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        map = new char[N][N];
        for (int i = 0; i < N; i++){
            map[i] = in.readLine().toCharArray();
            for (int j = 0; j < N; j++){
                if (map[i][j] == '#'){
                    if (startR == -1){
                        startR = i;
                        startC = j;
                    }
                    else{
                        endR = i;
                        endC = j;
                    }
                }
            }
        }

        Deque<Pos> dec = new ArrayDeque<>();
        visited = new boolean[N][N][4];
        for (int di = 0; di < 4; di++){
            int nr = startR + dr[di];
            int nc = startC + dc[di];
            if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == '*') continue;
            dec.offer(new Pos(nr, nc, 0, di));
        }

        while(!dec.isEmpty()){
            Pos cur = dec.pollFirst();
//            System.out.println(cur.r + " " + cur.c);
            if (visited[cur.r][cur.c][cur.dir]) continue;
            visited[cur.r][cur.c][cur.dir] = true;

            if (cur.r == endR && cur.c == endC){
                System.out.println(cur.cnt);
                break;
            }
            else if (map[cur.r][cur.c] == '.'){
                //직진
                int nr = cur.r + dr[cur.dir];
                int nc = cur.c + dc[cur.dir];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == '*') continue;
                dec.offerFirst(new Pos(nr, nc, cur.cnt, cur.dir));
            }
            else { //느낌표인 경우 -> 3방향
                for (int di = 0; di < 4; di++){
                    if (di == (cur.dir + 2)%4) continue;
                    int nr = cur.r + dr[di];
                    int nc = cur.c + dc[di];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == '*') continue;

                    if (di == cur.dir){
                        dec.offerFirst(new Pos(nr, nc, cur.cnt, cur.dir));
                    }
                    else{
                        dec.offerLast(new Pos(nr, nc, cur.cnt+1, di));
                    }

                }
            }
        }
    }
}
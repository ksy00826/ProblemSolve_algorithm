import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static class Pos{
        int r, c;
        public Pos(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    static int N, M;
    static int startR, startC, endR, endC;
    static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(in.readLine());
        startR = Integer.parseInt(st.nextToken())-1;
        startC = Integer.parseInt(st.nextToken())-1;
        endR = Integer.parseInt(st.nextToken())-1;
        endC = Integer.parseInt(st.nextToken())-1;

        map = new char[N][M];
        for (int i = 0; i < N; i++){
            map[i] = in.readLine().toCharArray();
        }

        System.out.println(zeroOneBfs(new Pos(startR, startC)));
    }

    private static int zeroOneBfs(Pos start) {
        Deque<Pos> dec = new ArrayDeque<>();
        int ans = 0;
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        dec.offer(start);
        boolean[][] visited = new boolean[N][M];
        visited[startR][startC] = true;
        while(true){
            boolean[][] isNext = new boolean[N][M];

            while(!dec.isEmpty()){
                Pos cur = dec.pollFirst();

                if (cur.r == endR && cur.c == endC) return ans;

                for (int di = 0; di < 4; di++){
                    int nr = cur.r + dr[di];
                    int nc = cur.c + dc[di];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc]) continue;

                    visited[nr][nc] = true;
                    if (map[nr][nc] == '0') dec.offerFirst(new Pos(nr, nc));
                    else isNext[nr][nc] = true;
                }
            }

            for (int i = 0; i < N; i++){
                for (int j = 0; j < M; j++){
                    if (isNext[i][j]) {
                        dec.offer(new Pos(i, j));
                        map[i][j] = '0';
                    }
                }
            }
            ans++;
        }
    }
}
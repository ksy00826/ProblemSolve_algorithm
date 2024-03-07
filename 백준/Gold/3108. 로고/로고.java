import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Rectangle{
        int r1, c1, r2, c2;
        public Rectangle(int r1, int c1, int r2, int c2){
            this.r1 = r1;
            this.r2 = r2;
            this.c1 = c1;
            this.c2 = c2;
        }
    }
    static class Cell{
        boolean[] road = new boolean[4]; //4방향 길
        public void setRoad(int i){
            road[i] = true;
        }
    }
    static class Pos{
        int r, c;
        public Pos(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    static int N;
    static Rectangle[] rects;
    static Cell[][] map;
    static int R = 1001, C = 1001; //-500~500 (+500) -> 0~1000
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, -1, 0, 1}; //상좌하우(0123)
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        rects = new Rectangle[N];
        for (int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int r1 = Integer.parseInt(st.nextToken()) + 500;
            int c1 = Integer.parseInt(st.nextToken()) + 500;
            int r2 = Integer.parseInt(st.nextToken()) + 500;
            int c2 = Integer.parseInt(st.nextToken()) + 500;
            rects[i] = new Rectangle(r1, c1, r2, c2);
        }

        //사각형을 맵에 그리기
        map = new Cell[R][C];
        for (int i = 0; i < R; i++){
            for (int j = 0; j < C; j++){
                map[i][j] = new Cell();
            }
        }
        drawRect();

        //첫 번째 사각형부터 dfs
        visited = new boolean[R][C];
        int cnt = 0;
        for (Rectangle rect : rects){
            if (visited[rect.r1][rect.c1]) continue;

            //방문하지 않았다면
            visited[rect.r1][rect.c1] = true;
//            System.out.println(" #### " + rect.r1 + " " + rect.c1);
            bfs(new Pos(rect.r1, rect.c1));
            cnt++;
        }
//        System.out.println(cnt);
        System.out.println(cnt - 1 + ((visited[500][500])? 0 : 1));
    }

    private static void bfs(Pos pos) {
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(pos);

        while(!q.isEmpty()){
            Pos cur = q.poll();
//            System.out.println(cur.r + " " + cur.c);

            for (int di = 0; di < 4; di++){
                if (!map[cur.r][cur.c].road[di]) continue; //연결되어 있지 않음
//                System.out.println("dir: " + di);
                //연결되어 있다면 방문 체크
                int nr = cur.r + dr[di];
                int nc = cur.c + dc[di];
                if (nr < 0 || nr >= R || nc < 0 || nc >= C || visited[nr][nc]) continue;
                visited[nr][nc] = true;
//                System.out.println("push : " + nr + " " + nc);
                q.offer(new Pos(nr, nc));
            }
        }
    }

    private static void drawRect() {
        for (Rectangle rect : rects){
            map[rect.r1][rect.c1].setRoad(0);
            map[rect.r1][rect.c1].setRoad(3);

            map[rect.r1][rect.c2].setRoad(0);
            map[rect.r1][rect.c2].setRoad(1);

            map[rect.r2][rect.c1].setRoad(2);
            map[rect.r2][rect.c1].setRoad(3);

            map[rect.r2][rect.c2].setRoad(1);
            map[rect.r2][rect.c2].setRoad(2);

            //좌우 방향(1, 3) : r을 고정
            for (int c = rect.c1+1; c < rect.c2; c++){
                map[rect.r1][c].setRoad(1);
                map[rect.r1][c].setRoad(3);
                map[rect.r2][c].setRoad(1);
                map[rect.r2][c].setRoad(3);
            }

            //상하 방향(0, 2) : c을 고정
            for (int r = rect.r1+1; r < rect.r2; r++){
                map[r][rect.c1].setRoad(0);
                map[r][rect.c1].setRoad(2);
                map[r][rect.c2].setRoad(0);
                map[r][rect.c2].setRoad(2);
            }
        }
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Cell{
        int no;
        int cnt;
        public Cell(int no, int cnt){
            this.no = no;
            this.cnt = cnt;
        }
    }
    static class Pos{
        int r, c;
        public Pos(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    static int N, M;
    static int[][] map;
    static Cell[][] cntMap;
    static boolean[][] visited;
    static Queue<Pos> saveQ = new ArrayDeque<>();
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        cntMap = new Cell[N][M];
        for (int i = 0; i < N; i++){
            int j = 0;
            for (char c : in.readLine().toCharArray()){
                map[i][j++] = c - '0';
            }
        }
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                cntMap[i][j] = new Cell(-1, 0);
            }
        }

        visited = new boolean[N][M];
        int no = 0;
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                if (visited[i][j] || map[i][j] == 1) continue;
                //0인 칸에서 bfs를 해서 갯수를 구하기
                int cnt = bfs(new Pos(i, j));
                for (Pos pos : saveQ){
                    cntMap[pos.r][pos.c] = new Cell(no, cnt);
                }
                no++;
                saveQ.clear();
            }
        }

        //1인 칸이면 주변 검사해서 리턴 맵 채우기
        int[][] res = new int[N][M];
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                if (map[i][j] == 0) continue;

                boolean[] used = new boolean[no];
                int sum = 1;
                for (int di = 0; di < 4; di++){
                    int nr = i + dr[di];
                    int nc = j + dc[di];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] == 1 || used[cntMap[nr][nc].no]) continue;

                    used[cntMap[nr][nc].no] = true;
                    sum += cntMap[nr][nc].cnt;
                }
                res[i][j] = sum;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                sb.append(res[i][j] % 10);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    private static int bfs(Pos start) {
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(start);
        visited[start.r][start.c] = true;
        int cnt = 1;

        while(!q.isEmpty()){
            Pos cur = q.poll();
            saveQ.offer(cur);

            for (int di = 0; di < 4; di++){
                int nr = cur.r + dr[di];
                int nc = cur.c + dc[di];
                if (nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc] || map[nr][nc] == 1) continue;

                visited[nr][nc] = true;
                q.offer(new Pos(nr, nc));
                cnt++;
            }
        }
        return cnt;
    }
}
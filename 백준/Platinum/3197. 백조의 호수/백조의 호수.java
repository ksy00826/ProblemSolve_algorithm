import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    /*
    방법 1) bfs + 이분탐색
    1. 빙산이 언제 녹을 지 전처리 -> bfs 1회
    2. 백조가 언제 만날지를 이분 탐색으로 처리. 얼음이 다 녹는 건 최대 3000회.
        - 갈 수 있는가? = 빙산이 녹았는가?
        - 이미 방문했는가? = bfs를 다시 수행할 때마다 특정한 값으로 채워졌는가?
    https://www.acmicpc.net/board/view/18525

    방법 2) 유니온 파인드
    1.
     */
    static class Pos{
        int r, c;
        int depth;
        public Pos(int r, int c){
            this.r = r;
            this.c = c;
        }
        public Pos(int r, int c, int depth){
            this.r = r;
            this.c = c;
            this.depth = depth;
        }
    }
    static int R, C;
    static char[][] map;
    static int[][] days;
    static Queue<Pos> water;
    static int startR = -1, startC = -1;
    static int endR = -1, endC = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        days = new int[R][C];
        water = new ArrayDeque<>();
        for (int i = 0; i < R; i++){
            String line = in.readLine();
            int j = 0;
            for (char c : line.toCharArray()){
                if (c == 'L'){
                    if (startR == -1){
                        startR = i;
                        startC = j;
                    }
                    else{
                        endR = i;
                        endC = j;
                    }
                    water.offer(new Pos(i, j, 0));
                }
                else if (c == '.'){
                    water.offer(new Pos(i, j, 0));
                }
                map[i][j++] = c;
            }
        }

        //logic
        //1. 빙산이 언제 녹을 지 전처리 -> bfs
        melting();
//        for (int i = 0; i < R; i++){
//            System.out.println(Arrays.toString(days[i]));
//        }

        //2. 백조 bfs 이분탐색 수행
        int day = binarySearch(0, 3000);

        System.out.println(day);
    }

    private static int binarySearch(int start, int end) {
        if (start >= end) return end;

        int mid = (start + end) / 2;
        if (bfs(mid)){ //만났다 - 왼쪽
            end = mid;
        }
        else{ //못만났다 - 오른쪽 부분
            start = mid + 1;
        }
        return binarySearch(start, end);
    }

    private static void melting() {
        boolean[][] visited = new boolean[R][C];
        //얼음이고, 방문하지 않은 곳이면 방문하면서 숫자를 체크

        while(!water.isEmpty()){
            Pos cur = water.poll();

            for (int di = 0; di < 4; di++){
                int nr = cur.r + dr[di];
                int nc = cur.c + dc[di];
                if (nr < 0 || nr >= R || nc < 0 || nc >= C || visited[nr][nc] || map[nr][nc] != 'X') continue;

                visited[nr][nc] = true;
                water.offer(new Pos(nr, nc, cur.depth+1));
                days[nr][nc] = cur.depth+1;
            }
        }
    }


    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    private static boolean bfs(int cutline) {
        Queue<Pos> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[R][C];

        q.offer(new Pos(startR, startC));
        visited[startR][startC] = true;

        while(!q.isEmpty()){
            Pos cur = q.poll();

            if (cur.r == endR && cur.c == endC) return true;

            for (int di = 0; di < 4; di++){
                int nr = cur.r + dr[di];
                int nc = cur.c + dc[di];
                if (nr < 0 || nr >= R || nc < 0 || nc >= C || visited[nr][nc] || days[nr][nc] > cutline) continue;

                visited[nr][nc] = true;
                q.offer(new Pos(nr, nc));
            }
        }
        return false;
    }
}
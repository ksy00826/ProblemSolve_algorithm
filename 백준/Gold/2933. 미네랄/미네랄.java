import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Pos{
        int r, c;
        public Pos(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    static int R, C;
    static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = in.readLine().toCharArray();
        }

        int N = Integer.parseInt(in.readLine());
        st = new StringTokenizer(in.readLine());
        int dir = 0;
        while (N-- > 0) {
            int h = Integer.parseInt(st.nextToken()); //던진 높이

            //미네랄 파괴
            if (breakMineral(dir, h)) {
                //파괴했으면 붕 뜬 미네랄 있는지 체크
                boolean[][] visited = new boolean[R][C];

                //우선 바닥에 붙어있는 미네랄 체크
                for (int j = 0; j < C; j++) {
                    if (!visited[R - 1][j] && map[R - 1][j] == 'x') bfs(R - 1, j, visited);
                }

                int minDiff = Integer.MAX_VALUE;
                int targetI = 0;
                int targetJ = 0;
                for (int i = R - 2; i >= 0; i--) {
                    //나머지 중에서 미네랄 있는데 visited가 false이면 떠있는 것 - only one(문제 조건)
                    for (int j = 0; j < C; j++){
                        if (map[i][j] == 'x' && !visited[i][j]){ //클러스터의 아래 둘레만!!!
                            ///떠 있다. 얼마나 떠 있는지 확인
                            int diff = 0;
                            for (int k = i+1; k < R; k++){
                                if (map[k][j] == 'x' && visited[k][j]) break;
                                diff++;
                            }
                            if (minDiff > diff){
                                minDiff = diff;
                                targetI = i;
                                targetJ = j;
                            }
                        }
                    }
                }
                if (minDiff != Integer.MAX_VALUE) {
                    //해당 클러스터를 모두 diff만큼 내려준다.
                    downCluster(new Pos(targetI, targetJ), minDiff);
                }
            }
            dir++;
        }

        //출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++){
            for (int j = 0; j < C; j++){
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void downCluster(Pos pos, int diff) {
        //해당 점부터 bfs -> 클러스터 점들을 모두 큐에 담는다.
        //큐를 돌면서 (i, j)의 x를 지우고 .을 넣는다. 이거 그냥 bfs랑 같이 해도 되긴 한데.. 일단 분리
        //큐를 돌면서 (i+diff, j)에 x를 넣는다.

        Queue<Pos> cluster = new ArrayDeque<>(); //점을 모을 클러스터

        //bfs
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(pos);
        cluster.offer(pos);
        boolean[][] visited = new boolean[R][C];
        visited[pos.r][pos.c] = true;

        while(!q.isEmpty()){
            Pos cur = q.poll();

            for (int di = 0; di < 4; di++){
                int nr = cur.r + dr[di];
                int nc = cur.c + dc[di];
                if (nr < 0 || nr >= R || nc < 0 || nc >= C || visited[nr][nc] || map[nr][nc] == '.') continue;

                visited[nr][nc] = true;
                q.offer(new Pos(nr, nc));
                cluster.offer(new Pos(nr, nc));
            }
        }

        //x 지우기
        for (Pos p : cluster){
            map[p.r][p.c] = '.';
        }
        //x 내리기
        for (Pos p : cluster){
            map[p.r + diff][p.c] = 'x';
        }
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    private static void bfs(int i, int j, boolean[][] visited) {
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(i, j));
        visited[i][j] = true;

        while(!q.isEmpty()){
            Pos cur = q.poll();

            for (int di = 0; di < 4; di++){
                int nr = cur.r + dr[di];
                int nc = cur.c + dc[di];
                if (nr < 0 || nr >= R || nc < 0 || nc >= C || visited[nr][nc] || map[nr][nc] == '.') continue;

                visited[nr][nc] = true;
                q.offer(new Pos(nr, nc));
            }
        }
    }

    private static boolean breakMineral(int dir, int h) {
        boolean isBroken = false;
        if (dir % 2 == 0) {
            //왼 -> 오
            for (int j = 0; j < C; j++){
                if (map[R-h][j] == 'x') {
                    map[R-h][j] = '.'; //파괴
                    isBroken = true;
                    break;
                }
            }
        }
        else{
            //오 -> 왼
            for (int j = C-1; j >= 0; j--){
                if (map[R-h][j] == 'x') {
                    map[R-h][j] = '.'; //파괴
                    isBroken = true;
                    break;
                }
            }
        }
//        System.out.println("isBroken " + isBroken);
        return isBroken;
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Pos implements Comparable<Pos>{
        int r, c;
        int depth;

        public Pos(int r, int c, int depth) {
            super();
            this.r = r;
            this.c = c;
            this.depth = depth;
        }

		@Override
		public int compareTo(Pos o) {
			if (r != o.r) return r - o.r;
			return c - o.c;
		}
        
    }

    static int N;
    static int[][] map;
    static int time = 0;
    static int meR = 0;
    static int meC = 0;
    static int meSize = 2;
    static int eatCnt = 0;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(in.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    meR = i;
                    meC = j;
                }
            }
        }
        
        //logic
        while(true) {
            if (!bfs()) break;
        }
        System.out.println(time);
    }

    private static boolean bfs() {
        Queue<Pos> q = new ArrayDeque<>();
        Queue<Pos> pq = new PriorityQueue<>(); //pq 하나 더 쓰는게 맘편함
        boolean[][] visited = new boolean[N][N];
        
        q.offer(new Pos(meR, meC, 0)); //depth 0
        visited[meR][meC] = true;
        
        int[] dr = {-1, 0, 0, 1};
        int[] dc = {0, -1, 1, 0};
        int foundDepth = -1;
        while(!q.isEmpty()) {
            Pos cur = q.poll();
            
            if (cur.depth == foundDepth) {
            	Pos cloest = pq.poll();
                //물고기 먹음
                eating();
                map[meR][meC] = 0; //원래자리
                meR = cloest.r;
                meC = cloest.c; //해줘야지
                time += (cur.depth);
                return true;
            }
            for (int di = 0; di < 4; di++) {
                int nr = cur.r + dr[di];
                int nc = cur.c + dc[di];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                if (map[nr][nc] > meSize || visited[nr][nc]) continue;
                if (map[nr][nc] != 0 && map[nr][nc] < meSize) { //물고기가 있으면서, 크기가 작은 거
                	pq.offer(new Pos(nr, nc, cur.depth + 1));
                	foundDepth = cur.depth + 1;
                }
                //빈칸이거나 크기 같으면 이동 가능
                visited[nr][nc] = true;
                q.offer(new Pos(nr, nc, cur.depth + 1));
            }
        }
        
        return false;
    }

    private static void eating() {
        eatCnt++;
        if (eatCnt == meSize) {
            meSize++;
            eatCnt = 0;
        }
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Pos{
		int r, c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
	}

	static int N, M;
	static int[][] map;
	static int[][] count;
	private static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		//inputMap
		map = new int[N][M];
		int startR = 0;
		int startC = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					startR = i; startC = j;
				}
			}
		}
		
		//2부터 bfs
		bfs(new Pos(startR, startC));
		//원래 갈 수 있는 땅인 부분 중에서 도달할 수 없는 곳..
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1 && count[i][j] == 0) count[i][j] = -1;
			}
		}
		
		//print
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(count[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	private static void bfs(Pos pos) {
		Queue<Pos> q = new ArrayDeque<>();
		visited = new boolean[N][M];
		count = new int[N][M];
		
		q.offer(pos);
		visited[pos.r][pos.c] = true;
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			int[] dr = {1, -1, 0, 0};
			int[] dc = {0, 0, 1, -1};
			
			for (int di = 0; di < 4; di++) {
				int nr = cur.r + dr[di];
				int nc = cur.c + dc[di];
				if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				if (map[nr][nc] == 0 || visited[nr][nc]) continue;
				
				visited[nr][nc] = true;
				count[nr][nc] = count[cur.r][cur.c] + 1; 
				q.offer(new Pos(nr, nc));
			}
		}
	}
}
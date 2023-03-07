import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Pos{
		int r, c;
		int time;
		public Pos(int r, int c, int time) {
			super();
			this.r = r;
			this.c = c;
			this.time = time;
		}
		
	}

	/*
	 * bfs를 통해 벽을 피하면서 최단으로 공주님한테 간다.
	 * 가면서 그람을 만나면, 거기서부터는 벽을 다 무시하면서 최단으로 공ㅂ주님한테 갈 수 있따.
	 * 총 시간을 출력! 안되면 fail
	 */
	
	static int N, M, T;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//logic
		bfs();
		
		System.out.println((minTime > T)? "Fail" : minTime);
	}
	static int minTime = Integer.MAX_VALUE;
	private static void bfs() {
		Queue<Pos> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][M];
		
		q.offer(new Pos(0, 0, 0));
		visited[0][0] = true;
		
		int[] dr = {1, -1, 0, 0};
		int[] dc = {0, 0, 1, -1};
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			if (cur.r == N-1 && cur.c == M-1) { //이건 큐에서 뺼 떄 해야 함. 큐에 넣을 떄 하면 time 더 큰게 선택될 수도 있음
				minTime = Math.min(minTime, cur.time);
				return;
			}
			
			for (int di = 0; di < 4; di++) {
				int nr = cur.r + dr[di];
				int nc = cur.c + dc[di];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				if (visited[nr][nc] || map[nr][nc] == 1) continue;
				if (map[nr][nc] == 2) {
					//그람을 만나면 1 다 무시됨!
					//여기서부터는 공주님까지의 거리가 고정. 이 값과 bfs 최종 값을 비교해서 최단 시간을 고르면 됨.
					//이건 큐에 안넣어도 됨.
					minTime = Math.min(minTime, cur.time + (N-1 - nr) + (M-1 - nc) + 1);
					continue;
				}
				visited[nr][nc] = true;
				q.offer(new Pos(nr, nc, cur.time + 1));
			}
		}
	}
}
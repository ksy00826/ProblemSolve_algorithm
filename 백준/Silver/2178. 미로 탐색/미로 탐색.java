import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
	static class Pos{
		int r;
		int c;
		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
	}

	static int N, M;
	static int[][] map;
	private static int[][] count;
	private static int minCnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] line = in.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		minCnt = N*M+10;
		//map
		map = new int[N][M]; //할당
		for (int i = 0; i < N; i++) {
			line = in.readLine().split("");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(line[j]);
			}
		}

		//logic
		count = new int[N][M];
		for (int i = 0; i < N; i++) {
			Arrays.fill(count[i], Integer.MAX_VALUE);
		}
//		dfs(0, 0, 1);
		bfs(0, 0);
		//print
		System.out.println(count[N-1][M-1]);
	}

	private static void bfs(int i, int j) {
		Queue<Pos> q = new ArrayDeque<>();
		q.offer(new Pos(i, j));
		count[i][j] = 1;
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();

			int[] dr = {0, 1, 0, -1};
			int[] dc = {1, 0, -1, 0};
			for (int di = 0; di < 4; di++) {
				int nr = cur.r + dr[di];
				int nc = cur.c + dc[di];
				if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue; //오타 정신차려..
				if (count[nr][nc] != Integer.MAX_VALUE || map[nr][nc] == 0) continue;
				
				count[nr][nc] = Math.min(count[nr][nc], count[cur.r][cur.c] + 1);
				q.add(new Pos(nr, nc));
			}
		}
		
	}
}
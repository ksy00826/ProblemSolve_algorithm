import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Pos{
		int r, c;
		int depth;
		public Pos(int r, int c, int depth) {
			super();
			this.r = r;
			this.c = c;
			this.depth = depth;
		}
		
	}

	static int N, M;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		Queue<Pos> oneQ = new ArrayDeque<>();
		Queue<Pos> zeroQ = new ArrayDeque<>();
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) oneQ.offer(new Pos(i, j, 0));
				else if (map[i][j] == 0) zeroQ.offer(new Pos(i, j, 0));
			}
		}
		
		//logic
		int[][] count = new int[N][M];
		for (int i = 0; i < N; i++) {
			Arrays.fill(count[i], Integer.MAX_VALUE);
		}
		int[] dr = {1, -1, 0, 0};
		int[] dc = {0, 0, 1, -1};
		while(!oneQ.isEmpty()) {
			Pos cur = oneQ.poll();
			
			for (int di = 0; di < 4; di++) {
				int nr = cur.r + dr[di];
				int nc = cur.c + dc[di];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				if (map[nr][nc] == 0) { //0일때만 처리(익힘)
					map[nr][nc] = 1;
					oneQ.offer(new Pos(nr, nc, cur.depth+1));
					count[nr][nc] = Math.min(count[nr][nc], cur.depth+1);
				}
			}
		}
		
		int maxDep = 0;
		while(!zeroQ.isEmpty()) {
			Pos cur = zeroQ.poll();
			if (map[cur.r][cur.c] == 0) {
				System.out.println("-1");
				return;
			}
			maxDep = Math.max(maxDep, count[cur.r][cur.c]);
		}
		System.out.println(maxDep);
	}
}
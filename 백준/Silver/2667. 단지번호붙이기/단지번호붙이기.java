import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

	static int N;
	static int[][] map;
	private static boolean[][] visited;
	static List<Integer> count = new ArrayList<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		
		N = Integer.parseInt(in.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String[] line = in.readLine().split("");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		//logic
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0 || visited[i][j]) continue;
				visited[i][j] = true;
				bfs(new Pos(i, j));
			}
		}
		
		//print
		Collections.sort(count);
		sb.append(count.size()).append("\n");
		for (int cnt : count) {
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
	}

	private static void bfs(Pos pos) {
		Queue<Pos> q = new ArrayDeque<>();

		int cnt = 1;
		q.offer(pos);
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			int[] dr = {1, -1, 0, 0};
			int[] dc = {0, 0, 1, -1};
			for (int di = 0; di < 4; di++) {
				int nr = cur.r + dr[di];
				int nc = cur.c + dc[di];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
				if (visited[nr][nc] || map[nr][nc] == 0) continue;
				
				visited[nr][nc] = true;
				q.offer(new Pos(nr, nc));
				cnt++;
			}
		}
		count.add(cnt);
	}
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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

	static int N, M, K;
	static int[][] map;
	
	static List<Integer> result = new ArrayList<>();
	static int cnt = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(in.readLine());
			int x1, y1, x2, y2;
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			
			draw(x1, y1, x2, y2);
		}
		
		//logic
		visited = new boolean[N][M];
		//0인 부분에서 bfs 해서 영억의 수와 넓이 구함
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0 && !visited[i][j]) bfs(i, j);
			}
		}
		
		//print
		result.sort(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		});
		
		StringBuffer sb = new StringBuffer();
		sb.append(cnt).append("\n");
		for (int n : result) sb.append(n + " ");
		System.out.println(sb);
	}

	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	private static void bfs(int i, int j) {
		int area = 1;
		
		Queue<Pos> q = new ArrayDeque<>();
		q.offer(new Pos(i, j));
		visited[i][j] = true;
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			for (int di = 0; di < 4; di++) {
				int nr = cur.r + dr[di];
				int nc = cur.c + dc[di];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				if (visited[nr][nc] || map[nr][nc] == 1) continue;
				
				visited[nr][nc] = true;
				q.offer(new Pos(nr, nc));
				area++;
			}
		}
		result.add(area);
		cnt++;
	}
	private static void draw(int x1, int y1, int x2, int y2) {
		for (int i = x1; i < x2; i++) {
			for (int j = y1; j < y2; j++) {
				map[i][j] = 1;
			}
		}
	}
}
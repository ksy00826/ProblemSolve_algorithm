import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//logic
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = true;
				dfs(i, j, 0, 0);
				visited[i][j] = false;
			}
		}
		System.out.println(maxSum);
	}

	static boolean[][] visited;
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	static int maxSum = 0;
	private static void dfs(int r, int c, int sum, int depth) {
		if (depth == 4) {
			maxSum = Math.max(maxSum, sum);
			return;
		}

		for (int di = 0; di < 4; di++) {
			int nr = r + dr[di];
			int nc = c + dc[di];
			if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
			if (visited[nr][nc]) continue;
			
			//엿 모양
			if (depth == 2) {
				visited[nr][nc] = true;
				dfs(r, c, sum + map[nr][nc], depth+1);
				visited[nr][nc] = false;
			}
			
			visited[nr][nc] = true;
			dfs(nr, nc, sum + map[nr][nc], depth+1);
			visited[nr][nc] = false;
		}
	}


}
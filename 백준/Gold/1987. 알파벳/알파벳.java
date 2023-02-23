import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

	static int R, C;
	static char[][] map;
	static boolean[] used = new boolean[26]; //알파벳
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] line = in.readLine().split(" ");
		R = Integer.parseInt(line[0]);
		C = Integer.parseInt(line[1]);
		
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = in.readLine().toCharArray();
		}
		
		used[getUsedIdx(0, 0)] = true;
		dfs(0, 0, 0);
		
		System.out.println(maxCnt+1);
	}
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	static int maxCnt = 0;
	private static void dfs(int r, int c, int cnt) {
		maxCnt = Math.max(maxCnt, cnt);
		
		for (int di = 0; di < 4; di++) {
			int nr = r + dr[di];
			int nc = c + dc[di];
			if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
			if (used[getUsedIdx(nr, nc)]) continue;
			
			used[getUsedIdx(nr, nc)] = true;
			dfs(nr, nc, cnt + 1);
			used[getUsedIdx(nr, nc)] = false;
		}
	}

	private static int getUsedIdx(int r, int c) {
		return map[r][c] - 'A';
	}
}
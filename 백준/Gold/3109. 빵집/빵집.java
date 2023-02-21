import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int R, C;
	static String[][] map;
	static boolean[][] visited;
	static int maxCnt = 0;
	static int rowCnt = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] line = in.readLine().split(" ");
		R = Integer.parseInt(line[0]);
		C = Integer.parseInt(line[1]);
		map = new String[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = in.readLine().split("");
		}
		
		//logic
		visited = new boolean[R][C];
		for (rowCnt = 0; rowCnt < R; rowCnt++) {
			visited[rowCnt][0] = true;
			dfs(rowCnt, 0, maxCnt);
		}
		
		System.out.println(maxCnt);
//		for (int i = 0; i < R; i++) {
//			for (int j = 0; j < C; j++) {
//				System.out.print(visited[i][j] + " ");
//			}
//			System.out.println();
//		}
	}

	static boolean ok = false;
	private static void dfs(int r, int c, int cnt) {
//		System.out.println("r: " + r + " c: " + c );
//		System.out.println(cnt);
		if (rowCnt >= R || ok) return;
		
		if (c == C-1) {//마지막 열에 도착 = 파이프 하나 연결 성공
			ok = true;
			return;
		}
		
		int[] dr = {-1, 0, 1};
		int[] dc = {1, 1, 1};
//		boolean canGo = false;
		for (int di = 0; di < dr.length; di++) {
			int nr = r + dr[di];
			int nc = c + dc[di];
			if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
			if (map[nr][nc].equals("x") || visited[nr][nc] || ok) continue;
			
//			canGo = true;
			//갈 수 있는 곳이면
			visited[nr][nc] = true;
			dfs(nr, nc, cnt);
//			if (!ok) visited[nr][nc] = false;
//			else break; ///
		}
		if (c == 0) {
//			for (int i = 0; i < R; i++) {
//				for (int j = 0; j < C; j++) {
//					System.out.print(visited[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println("-----------");
			cnt = (ok) ? cnt + 1 : cnt;
			maxCnt = Math.max(maxCnt, cnt);
			ok = false;
		}
	}
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int R, C, T;
	static int[][] map;
	static int[][] result;
	
	static int cleanerR1, cleanerC1;
	static int cleanerR2, cleanerC2;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		result = new int[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == -1) {
					if (cleanerR1 == 0) cleanerR1 = i;
					else cleanerR2 = i;
				}
			}
		}
		result[cleanerR1][cleanerC1] = -1;
		result[cleanerR2][cleanerC2] = -1;
		
		//logic
		while(T-- > 0){
			//미세먼지 확산
			spread();
			rotate();
			updateMap();
			for (int i = 0; i < R; i++) {
				Arrays.fill(result[i], 0);
			}
			result[cleanerR1][cleanerC1] = -1;
			result[cleanerR2][cleanerC2] = -1;
		}
		
		//print
		int sum = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] != -1) sum += map[i][j];
			}
		}
		System.out.println(sum);
	}

	private static void updateMap() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] != -1) map[i][j] = result[i][j];
			}
		}
	}

	private static void rotate() {
		//위쪽: 상, 우, 하, 좌
		int r = cleanerR1;
		int c = cleanerC1;
		int[] dr1 = {-1, 0, 1, 0};
		int[] dc1 = {0, 1, 0, -1};
		for (int di = 0; di < 4; di++) {
			while(true) {
				int nr = r + dr1[di];
				int nc = c + dc1[di];
				if (nr < 0 || nr > cleanerR1 || nc < 0 || nc >= C) break;
				if (result[r][c] != -1) {
					if (result[nr][nc] == -1) {
						result[r][c] = 0;
						break;
					}
					else result[r][c] = result[nr][nc];
				}
				r = nr;
				c = nc;
			}
		}
		
		//아래: 하, 우, 상, 좌
		r = cleanerR2;
		c = cleanerC2;
		int[] dr2 = {1, 0, -1, 0};
		int[] dc2 = {0, 1, 0, -1};
		for (int di = 0; di < 4; di++) {
			while(true) {
				int nr = r + dr2[di];
				int nc = c + dc2[di];
				if (nr < cleanerR2 || nr >= R || nc < 0 || nc >= C) break;
				if (result[r][c] != -1) {
					if (result[nr][nc] == -1) {
						result[r][c] = 0;
						break;
					}
					else result[r][c] = result[nr][nc];
				}
				r = nr;
				c = nc;
			}
		}
	}

	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	private static void spread() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] > 0) {
					int amount = map[i][j]/5;
					int cnt = 0;
					for (int di = 0; di < 4; di++) {
						int nr = i + dr[di];
						int nc = j + dc[di];
						
						if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
						if (map[nr][nc] == -1) continue;
						
						result[nr][nc] += amount;
						cnt++;
					}
					result[i][j] += map[i][j] - (amount * cnt);
				}
			}
		}
	}
}
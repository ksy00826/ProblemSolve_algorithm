import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int R, C, N;
	static String[][] map;
	static int[][] bombMap;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();

		StringTokenizer st = new StringTokenizer(in.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new String[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = in.readLine().split("");
		}

		//logic
		/*
		 * bombMap의 값
		 * 1: 폭탄 설치
		 * 2: 1초후
		 * 3: 2초후
		 * 4: 3초후
		 * 숫자에 유의.. 폭탄 설치가 1이다.
		 */

		//1. bombMap에 폭탄이 설치된 초기 상태 설정. 미설치 - 0(자동), 설치 - 1
		bombMap = new int[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j].equals("O")) bombMap[i][j] = 1;
			}
		}
		
		if (N != 1) {
			//1초후
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (bombMap[i][j] != 0)bombMap[i][j] += 1;
				}
			}
	
			for (int time = 2; time <= N; time++) {
				//모든 칸에 +1 //모든 곳에 폭탄 설치 -> 빈칸을 1로 설정 + 기존꺼 시간 증가
				//3초 전에 설치한 폭탄 폭발 -> 3인 곳이랑 상하좌우 제거
				for (int i = 0; i < R; i++) {
					for (int j = 0; j < C; j++) {
						if (time % 2 == 0 || bombMap[i][j] != 0)bombMap[i][j] += 1;
					}
				}
	
				for (int i = 0; i < R; i++) {
					for (int j = 0; j < C; j++) {
						if (bombMap[i][j] == 4) removeBomb(i, j); 
					}
				}
			}
		}
		
		//print
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (bombMap[i][j] == 0) sb.append(".");
				else sb.append("O");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void removeBomb(int i, int j) {
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		bombMap[i][j] = 0;
		for (int di = 0; di < 4; di++) {
			int nr = i + dr[di];
			int nc = j + dc[di];
			if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
			if (bombMap[nr][nc] == 4) continue;
			bombMap[nr][nc] = 0; //nc.... 인데 nr로 적음
		}
	}

}
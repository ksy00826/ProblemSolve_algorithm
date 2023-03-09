import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, M, x, y, K;
	static int[][] map;
	//주사위 여섯 면이 보는 방향 저장 : 1앞, 2북, 3동, 4서, 5남, 6뒤
	static int[] direc = {1, 2, 3, 4, 5, 6};
	//주사위 여섯 면이 가진 값 저장
	static int[] values = {0, 0, 0, 0, 0, 0};
	static int frontIdx, backIdx;
	
	//우 좌 상 하
	static int[] dr = {0, 0, -1, 1};
	static int[] dc = {1, -1, 0, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(in.readLine());
		
		//logic
		while(K-- > 0) {
			int di = Integer.parseInt(st.nextToken()) - 1;
			
			int nr = x + dr[di];
			int nc = y + dc[di];
			if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue; //벗어나면 무시
			//갈 수 있으면 이동 : 주사위의 위치, 전개도 업뎃
			x = nr;
			y = nc;
			updateDice(di);
			getFrontIdx();

			if (map[x][y] == 0) {
				map[x][y] = values[backIdx];
			}
			else {
				values[backIdx] = map[x][y];
				map[x][y] = 0;
			}
			sb.append(values[frontIdx]).append("\n");
		}
		System.out.println(sb);
	}
	

	private static void getFrontIdx() {
		for (int i = 0; i < 6; i++) {
			if (direc[i] == 6) {
				backIdx = i;
			}
			if (direc[i] == 1) {
				frontIdx = i;
			}
		}
	}


	private static void updateDice(int di) {
		
		switch(di) {
		case 0: //동
			for (int i = 0; i < 6; i++) {
				switch(direc[i]) {
				case 1: direc[i] = 3; break;
				case 2: direc[i] = 2; break;
				case 3: direc[i] = 6; break;
				case 4: direc[i] = 1; break;
				case 5: direc[i] = 5; break;
				case 6: direc[i] = 4; break;
				}
			}
			break;
		case 1: //서
			for (int i = 0; i < 6; i++) {
				switch(direc[i]) {
				case 1: direc[i] = 4; break;
				case 2: direc[i] = 2; break;
				case 3: direc[i] = 1; break;
				case 4: direc[i] = 6; break;
				case 5: direc[i] = 5; break;
				case 6: direc[i] = 3; break;
				}
			}
			break;
		case 2: //북
			for (int i = 0; i < 6; i++) {
				switch(direc[i]) {
				case 1: direc[i] = 2; break;
				case 2: direc[i] = 6; break;
				case 3: direc[i] = 3; break;
				case 4: direc[i] = 4; break;
				case 5: direc[i] = 1; break;
				case 6: direc[i] = 5; break;
				}
			}
			break;
		case 3: //남
			for (int i = 0; i < 6; i++) {
				switch(direc[i]) {
				case 1: direc[i] = 5; break;
				case 2: direc[i] = 1; break;
				case 3: direc[i] = 3; break;
				case 4: direc[i] = 4; break;
				case 5: direc[i] = 6; break;
				case 6: direc[i] = 2; break;
				}
			}
			break;
		}
	}

}
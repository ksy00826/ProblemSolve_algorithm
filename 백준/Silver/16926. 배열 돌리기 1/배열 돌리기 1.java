import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static int N, M, R;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		int[] line = getLine();
		N = line[0];
		M = line[1];
		R = line[2];
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			map[i] = getLine();
		}
		
		//logic
		while(R-- > 0) {
			for (int i = 0; i < Math.min(N/2, M/2); i++) {//절반만
				rotate(i);
			}
		}

		//print
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	private static void rotate(int i) {
		int save = map[i][i]; //첫 원소 값 저장하고, 하나씩 값을 땡겨옴
		
		int[] dr = {0, 1, 0, -1};
		int[] dc = {1, 0, -1, 0};
		int di = 0;
		int cr = i;
		int cc = i;
		while(true) {
			int nr = cr + dr[di];
			int nc = cc + dc[di];
			
			//마지막이면~~저장하고 끝
			if (cr == i+1 && cc == i) {
				map[cr][cc] = save;
				break;
			}
			//인덱스 범위 초과 => 방향 바꿈
			if (nr < 0 + i || nr >= N-i || nc < 0+i || nc >= M-i) {
				di++;
			}
			//인덱스 범위 이내
			else {
				map[cr][cc] = map[nr][nc];
				cr = nr;
				cc = nc;
			}
			
		}
	}

	private static int[] getLine() throws IOException {
		String[] line = in.readLine().split(" ");
		int[] intLine = new int[line.length];
		for (int i = 0; i < line.length; i++) {
			intLine[i] = Integer.parseInt(line[i]);
		}
		return intLine;
	}
}
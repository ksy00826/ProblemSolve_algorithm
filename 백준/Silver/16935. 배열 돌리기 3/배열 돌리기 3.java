import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N;
	static int M;
	static int R;
	static int[][] map;
	static int[] cal;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] nmr = in.readLine().split(" ");
		N = Integer.parseInt(nmr[0]);
		M = Integer.parseInt(nmr[1]);
		R = Integer.parseInt(nmr[2]);
		
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String[] line = in.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		cal = new int[R];
		String[] line = in.readLine().split(" ");
		for (int i = 0; i < R; i++) {
			cal[i] = Integer.parseInt(line[i]);
		}
		
		//logic
		//입력받은 연산 순서대로 연산 수행
		for (int curCal : cal) {
			switch(curCal) {
			case 1: upDown(); break;
			case 2: leftRight(); break;
			case 3: right90(); break;
			case 4: left90(); break;
			case 5: clock(); break;
			case 6: unClock(); break;
			}
		}
		
		//print
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	private static void upDown() {
		for (int r = 0; r < N/2; r++) { //절반만!!!
			for (int c = 0; c < M; c++) {
				int temp = map[r][c];
				map[r][c] = map[N-1-r][c];
				map[N-1-r][c] = temp;
			}
		}
	}

	private static void leftRight() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M/2; c++) { //절반만!!!
				int temp = map[r][c];
				map[r][c] = map[r][M-1-c];
				map[r][M-1-c] = temp;
			}
		}
	}

	private static void right90() {
		int[][] newMap = new int[M][N];//바뀜
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				newMap[c][r] = map[N-1-r][c];
			}
		}
		
		map = newMap;
		
		//크기 정보 업뎃
		N = map.length;
		M = map[0].length;
	}

	private static void left90() {
		int[][] newMap = new int[M][N]; //바뀜
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				newMap[M-1-c][r] = map[r][c];
			}
		}
		
		map = newMap;
		
		//크기 정보 업뎃
		N = map.length;
		M = map[0].length;
	}

	private static void clock() {
		int[][] newMap = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int ni = 0;
				int nj = 0;
				if (i < N/2 && j < M/2) {		//1
					ni = i;
					nj = j + M/2;
				}
				else if (i < N/2 && j >= M/2) {	//2
					ni = i + N/2;
					nj = j;
				}
				else if (i >= N/2 && j >= M/2) { //3
					ni = i;
					nj = j - M/2;
				}
				else if (i >= N/2 && j < M/2) { //4
					ni = i - N/2;
					nj = j;
				}
				newMap[ni][nj] = map[i][j];
			}
		}
		map = newMap;
	}

	private static void unClock() {
		int[][] newMap = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int ni = 0;
				int nj = 0;
				if (i < N/2 && j < M/2) {		//1
					ni = i + N/2;
					nj = j;
				}
				else if (i < N/2 && j >= M/2) {	//2
					ni = i;
					nj = j - M/2;
				}
				else if (i >= N/2 && j >= M/2) { //3
					ni = i - N/2;
					nj = j;
				}
				else if (i >= N/2 && j < M/2) { //4
					ni = i;
					nj = j + M/2;
				}
				newMap[ni][nj] = map[i][j];
			}
		}
		map = newMap;
	}
	
}
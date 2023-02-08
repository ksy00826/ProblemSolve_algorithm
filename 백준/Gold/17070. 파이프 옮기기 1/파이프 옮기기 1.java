import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static int count = 0;
	static int N;
	static int[][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(in.readLine());
		map = new int[N][N];
		
		//input
		for (int i = 0; i < N; i++) {
			String[] line = in.readLine().split(" ");
			for (int j = 0; j < line.length; j++) {
				map[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		//logic : dfs?
		/*
		 * 파이프의 상태 : 가로 0 , 세로 1, 대각선 2
		 * 가로 - 2가지, 세로 - 2가지, 대각선 - 3가지
		 * 탐색하다가 인덱스 벗어나거나 더이상 갈 수 없으면 리턴
		 */
		
		dfs(0, 1, 0); //시작 index - 파이프의 오른쪽 또는 아래 또는 오른쪽 대각선 아래 인덱스!
		System.out.println(count);
	}

	private static void dfs(int i, int j, int status) {
		//move
		if (i == N-1 && j == N-1) {
			count++;
			return;
		}
		switch(status) {
		case 0: //가로
			if (checkBlock(i, j + 1)) //상태유지
				dfs(i, j + 1, 0);
			break;
		case 1: //세로
			if (checkBlock(i + 1, j)) //상태유지
				dfs(i + 1, j, 1);
			break;
		case 2: //오른쪽 대각선 아래
			if (checkBlock(i, j + 1)) 
				dfs(i, j + 1, 0);
			if (checkBlock(i + 1, j)) 
				dfs(i + 1, j, 1);
			break;	
		}
		if (checkBlock(i+1, j) && checkBlock(i, j+1) && checkBlock(i+1, j+1)) 
			dfs(i + 1, j + 1, 2);
	}

	private static boolean checkBlock(int i, int j) {
		return ((i >= 0 && i < N && j >= 0 && j < N) && map[i][j] == 0);
	}
}
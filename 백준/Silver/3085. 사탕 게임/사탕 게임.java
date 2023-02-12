import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static String[][] pan;
	static int maxVal = 0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());
		pan = new String[N][N];

		for (int i = 0; i < N; i++) {
			String[] line = in.readLine().split(""); //입력값 어떻게 들어오는지 잘 봐야함
			pan[i] = line;
		}

		//logic

		searchRow();
		searchCol();
		System.out.println(maxVal);
	}

	private static String[][] getClone() {
		String[][] clone = new String[N][N];
		for (int i = 0; i < N; i++) {
			clone[i] = pan[i].clone();
		}
	
		return clone;
	}

	private static void searchRow() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N - 1; j++) {
				if (!pan[i][j].equals(pan[i][j+1])) {
					change(i, j, i, j+1, getClone());
				}
			}
		}
	}
	
	private static void searchCol() {
		for (int j = 0; j < N; j++) {
			for (int i = 0; i < N - 1; i++) {
				if (!pan[i][j].equals(pan[i+1][j])) {
					change(i, j, i+1, j, getClone());
				}
			}
		}
	}
	
	private static void change(int i1, int j1, int i2, int j2, String[][] clone) {
		String temp = clone[i1][j1];
		clone[i1][j1] = clone[i2][j2];
		clone[i2][j2] = temp;

		findMaxCandy(clone);
	}

	private static void findMaxCandy(String[][] clone) {
		int max = -1;
		/*
		 * 처음에 이 부분에서.. cnt=0으로 초기화하고, 현재랑 다음꺼 비교햇는데 그러면 배열이 끝나면 반영이 안됨.
		 * 그래서 현재랑 이전꺼랑 비교하도록 바꿈
		 * 그리고 이중for문 중 내부for문 끝나고 max값 한 번 더 반영해줘야 함.
		 * 배열이 끝나면 반영이 안되ㅣ니까
		 */
		//search Row
		for (int i = 0; i < N; i++) {
			int cnt = 1;
			for (int j = 1; j < N; j++) {
				if (clone[i][j-1].equals(clone[i][j])) 
					cnt++;
				else {
					if (max < cnt) max = cnt;
					cnt = 1;
				}
			}
			if (max < cnt) max = cnt; //한번 더 반영
		}

		//search Col
		for (int j = 0; j < N; j++) {
			int cnt = 1;
			for (int i = 1; i < N; i++) {
				if (clone[i-1][j].equals(clone[i][j])) 
					cnt++;
				else {
					if (max < cnt) max = cnt;
					cnt = 1;
				}
			}
			if (max < cnt) max = cnt;
		}
		if (maxVal < max) maxVal = max;
	}
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N;
	static int[][] map;
	static StringBuffer sb = new StringBuffer();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String str = in.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		
		//logic
		divide(0, 0, N);
		System.out.println(sb);
	}
	private static void divide(int r, int c, int len) {
		int sum = 0;
		for (int i = r; i < r + len; i++) {
			for (int j = c; j < c + len; j++) {
				sum += map[i][j];
			}
		}
		
		if (sum == 0) sb.append(0);
		else if (sum == len * len) sb.append(1);
		else {
			sb.append("(");
			int half = len/2;
			divide(r, c, half);
			divide(r, c + half, half);
			divide(r + half, c, half);
			divide(r + half, c + half, half);
			sb.append(")");
		}
	}
}
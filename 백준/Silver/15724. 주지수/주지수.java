import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuffer sb = new StringBuffer();
	private static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		String[] sizes = in.readLine().split(" ");
		int N = Integer.parseInt(sizes[0]) + 1;
		int M = Integer.parseInt(sizes[1]) + 1;
		
		//input: map
		dp = new int[N][M];
		inputMap(N, M);

		//input: pos
		inputPos();
		
		//print
		System.out.println(sb);
	}
	private static void inputMap(int N, int M) throws IOException {
		for (int i = 0; i < N; i++) {
			//dp 초기화 
			if (i == 0) {
				for (int c = 0; c < M; c++) dp[i][c] = 0;
				continue;
			}
			
			int j = 0;
			dp[i][j] = 0;
			j++;
			String[] line = in.readLine().split(" ");
			for (String num : line) {
				dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + Integer.parseInt(num);
				j++;//왜 위에 넣으면 안되는걸까
			}
		}
	}
	
	private static void inputPos() throws IOException {
		int posN = Integer.parseInt(in.readLine());
		while(posN-- > 0) {
			String[] line = in.readLine().split(" ");
			int startR = Integer.parseInt(line[0]);
			int startC = Integer.parseInt(line[1]);
			int endR = Integer.parseInt(line[2]);
			int endC = Integer.parseInt(line[3]);
			
			getSum(startR, startC, endR, endC);
		}
	}
	
	private static void getSum(int startR, int startC, int endR, int endC) {
		int sum = dp[endR][endC] - dp[endR][startC - 1] - dp[startR - 1][endC] + dp[startR - 1][startC - 1];
		sb.append(sum).append('\n');
	}
}

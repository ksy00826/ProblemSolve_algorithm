import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int N, M, C;
	static int[][] map;
	static int r1, c1;
	static int r2, c2;
	static int maxSum;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		
		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			StringTokenizer st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken()); //크기
			M = Integer.parseInt(st.nextToken()); //벌통선택
			C = Integer.parseInt(st.nextToken()); //꿀의 최대 양
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			//logic
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N-M + 1; c++) {
					r1 = r;
					c1 = c;
					combination(r, c + M);
				}
			}
			sb.append(maxSum).append("\n");
			maxSum = 0;
		}
		System.out.println(sb);
	}
	private static void combination(int startR, int startC) {
		for (int c = startC; c < N-M+1; c++) {
			r2 = startR;
			c2 = c;
			select();
		}
		
		for (int r = startR+1; r < N; r++) {
			for (int c = 0; c < N-M+1; c++) {
				r2 = r;
				c2 = c;
				select();
			}
		}
	}
	private static void select() {

//		System.out.println(r1 + " " +c1 + " " + r2 + " " + c2);
		//r1, c1 부분집합
		subSum = 0;
		subset(r1, c1, 0, 0, 0);
		sum1 = subSum;
		
		//r2, c2 부분집합
		subSum = 0;
		subset(r2, c2, 0, 0, 0);
		sum2 = subSum;
		
		maxSum = Math.max(maxSum, sum1 + sum2);
	}
	static int sum1, sum2, subSum;
	private static void subset(int r, int c, int sum, int powSum, int len) {
		if (len == M) {
			subSum = Math.max(subSum, powSum);
			return;
		}
		
		int nextSum = sum + map[r][c];
		int nextPowSum = powSum + (int)Math.pow(map[r][c], 2);
		if (nextSum > C) {
			subSum = Math.max(subSum, powSum);
			return;
		}
		//포함
		subset(r, c+1, nextSum, nextPowSum, len + 1);
		//미포함
		subset(r, c+1, sum, powSum, len + 1);
	}
}
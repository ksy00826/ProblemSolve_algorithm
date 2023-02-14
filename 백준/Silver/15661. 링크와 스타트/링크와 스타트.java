import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	/*
	 * N명.
	 * 두 집합으로 나눔(공집합 없음)
	 * 
	 * 4 <= N <= 20...
	 * 재귀로 짜서 모든 조합을 다 구해도 시간초과 안날고같은뎅
	 */
	static int N;
	static int[][] S;
	static boolean[] visited;
	static int minDiff = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		//input
		N = Integer.parseInt(in.readLine());
		S = new int[N][N];
		for (int i = 0; i < N; i++) {
			String[] line  = in.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				S[i][j] = Integer.parseInt(line[j]);
			}
		}
		visited = new boolean[N];
		
		//logic
		subset(0);
		
		System.out.println(minDiff);
	}
	private static void subset(int cnt) {
		//기저조건
		if (cnt == N) {
			return;
		}
		
		//subset 에서 계속 더해가면서 부분집합 능력치 합을 구하고, 전체 합에서 뺴면 다른팀 합 구해지니까.. 이게 나을수도
		//=>아님!!!
		
		int diff = Math.abs(getTrueSum() - getFalseSum());
		if (minDiff > diff) minDiff = diff;
		
		//유도조건
		visited[cnt] = true;
		subset(cnt + 1);
		
		visited[cnt] = false;
		subset(cnt + 1);
	}
	private static int getFalseSum() {
		int sum = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = i; j < N; j++) {
				if (!visited[i] && !visited[j]) {
					sum += S[i][j];
					sum += S[j][i];
				}
			}
		}
		
		return sum;
	}
	private static int getTrueSum() {
		int sum = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = i; j < N; j++) {
				if (visited[i] && visited[j]) {
					sum += S[i][j];
					sum += S[j][i];
				}
			}
		}
		
		return sum;
	}
}
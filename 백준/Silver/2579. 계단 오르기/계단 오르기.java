import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int[] stairs = new int[N+1];
		for (int i = 1; i <= N; i++) {
			stairs[i] = Integer.parseInt(in.readLine());
		}
		
		if (N == 1) {
			System.out.println(stairs[1]);
			return;
		}
		
		//logic
		int[] dp = new int[N+1]; //개수만 변수
		dp[1] = stairs[1]; //한 칸 오르는 게 최대 가치
		dp[2] = dp[1] + stairs[2];
		for (int i = 3; i <= N; i++) {
			dp[i] = Math.max(dp[i-3] + stairs[i-1], dp[i-2]) + stairs[i];
		}
		
		System.out.println(dp[N]);
	}
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	/*
	 * dp문제 중 냅색 문제!!
	 * 
	 * <기존 냅색 문제>
	 * - 개수 N, 부피 V의 두 개의 변수가 존재하고, 따라서 이차원 dp 배열 사용
	 * - 개수를 늘려가고, 해당 개수 안에서 부피를 늘려가며 최대 가치를 점화식을 이용해서 저장
	 * 
	 * <현재 문제>
	 * - 냅색 문제에서 좀 더 단순화 된 버전인 것 같음.
	 * - 부피에 대한 제한이 없어지고, 세 번 연속 계단을 밟으면 안 된다, 마지막 계단은 반드시 밟아야 한다는 제한요건이 추가됨
	 * (제한요건 해석)
	 * - 세 번 연속 계단을 밟으면 안 된다 : 첫 번째 계단을 제외하고는 1칸 올라가면 그 다음에는 무조건 2칸 올라가야 성립함
	 * 그렇기 떄문에 점화식을 세울 때 이 부분을 유의해야 함
	 * - 마지막 계단은 반드시 밟아야 한다 : 사실 신경쓰지 않아도 됨. dp 배열을 채우는 과정에서 다 걸러짐.
	 * 
	 * <점화식>
	 * dp[i] = max(dp[i-1], dp[i-2]) + w[i] ::: 여기서 dp[i-1]을 하면 첫 번째 제한요건이 걸림.. 그래서 다음과 같이 바꿈
	 * 		 = max(dp[i-3] + w[i-1], dp[i-2]) + w[i] ::: 한 칸 뛰어넘기 전에는 무조건 두 칸 뛰어넘었다는 것을 가정하는 것.
	 * 
	 * 이렇게 점화식을 모두 세웠다면 dp[0], dp[1], dp[2]와 같이 위 점화식에서 커버되지 않는 것은 미리 채워놓고 시작함.
	 * 
	 */

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int[] stairs = new int[N+1];
		for (int i = 1; i <= N; i++) {
			stairs[i] = Integer.parseInt(in.readLine());
		}
		
		//계단의 수는 자연수이므로 1개일 때도 생각해야 함!! 이거 안하면 dp[2]에서 index error
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
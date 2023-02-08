import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		//input
		Scanner sc = new Scanner(System.in);
		int coinN = sc.nextInt();
		int money = sc.nextInt();
		int[] coins = new int[coinN];
		for (int i = 0; i < coinN; i++) {
			coins[i] = sc.nextInt();
		}
		
		//logic : dp
		int[] dp = new int[money + 1]; //1~money 인텍스
		dp[0] = 1;
		for (int coin : coins) {
			for (int i = coin; i <= money; i++) {
				dp[i] += dp[i - coin];
			}
		}
		
		//print
		System.out.println(dp[money]);
	}
}
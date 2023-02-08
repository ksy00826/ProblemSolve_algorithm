import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		//input
		Scanner sc = new Scanner(System.in);
		int money = sc.nextInt();
		
		//compute
		//2원짜리 동전과 5원짜리 동전
		int coin2 = 2, coin5 = 5;
		
		//일단 5원짜리의 수를 최대로 했을 때 몇개가 필요한지 계산
		int cnt5 = money / coin5;
		int cnt2 = 0;
		
		//만약 딱 떨어지지 않을 시, 5원짜리 수를 하나씩 줄여가며 계산
		int rest5 = 0, rest2 = 0; //5원짜리 주고 남은 돈, 2원짜리 주고 남은 돈
		while(cnt5 >= 0) {
			rest5 = money - (coin5 * cnt5);	//5원짜리 주고 남은 돈
			cnt2 = rest5 / coin2;			//2원짜리 몇개 줘야하는지
			rest2 = rest5 % coin2;			//2원짜리 주면 얼마 남는지

			if (rest2 == 0) break;			//나누어 떨어지면 끝
			cnt5--;
		}

		if (rest2 != 0) System.out.println("-1");
		else			System.out.println(cnt5 + cnt2);
	}
}

import java.util.Scanner;

public class Main {

	//봉지의 수를 최소로. 3킬로, 5킬로
	static int N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		int num5 = N / 5; //5킬로 봉지를 쓸 수 있는 최대 개수
		int num3 = 0;
		for (int i5 = num5; i5 >= 0; i5--) {
			//5킬로 봉지를 하나씩 줄여가면서 나머지를 3킬로로 만들 수 있는지 검사
			int rest = N - i5 * 5;
			num5 = i5;
			if (rest % 3 == 0) {
				num3 = rest/3;
				break;
			}
		}
		int res = (num5 + num3 == 0)? -1 : num5 + num3;
		System.out.println(res);
	}
}
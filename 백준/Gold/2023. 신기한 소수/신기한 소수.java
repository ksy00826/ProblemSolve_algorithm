import java.util.Scanner;

public class Main {
	static int n;
	static StringBuffer sb = new StringBuffer();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		
		int[] prime = {2, 3, 5, 7}; //소요시간 줄어듦!
		for (int p : prime) { //가장 왼쪽 자리 수. 1은 소수 아님 
			if(isSosu(p)) countSosu(1, p);
		}
		System.out.println(sb);
	}

	private static void countSosu(int len, int num) {
		if (len == n) {
			if (isSosu(num)) sb.append(num).append('\n');
			return;
		}
		
		for (int i = 0; i <= 9; i++) {
			int newNum = num * 10 + i;
			if (isSosu(newNum))
				countSosu(len + 1, newNum);
		}
	}

	private static boolean isSosu(int num) {
		boolean isDiv = true;
		for (int i = 2; i <= Math.sqrt(num); i++) { //제곱근까찌!! 시간 줄어듦
			if (num % i == 0) {
				isDiv = false;
				break;
			}
		}
		/* N의 약수는 무조건 sqrt(N)의 범위에 존재한다.

		만약 N이 12라 할때, 12의 제곱근은 약 3.46이다.
		12의 약수는 1, 2, 3, 4, 6, 12 이다.
		여기서 1과 12를 제외했을 때 이는 2 * 6, 3 * 4, 4 * 3, 6 * 2의 결과이다.

		이들의 관계는 몫이 커지면 나누는 값이 작아지거나 나누는 값이 커지만 몫이 작아지는 반비례 관계이다. 결국 N의 절반(제곱근)까지 향하게 되면 이후 몫과 나누는 값이 반대로 바뀌게만 되는 상황이다.

		따라서 N의 제곱근까지 나누어 떨어지는지 여부를 조사하면 더 빠르게 소수판별을 할 수 있다.*/

		return isDiv;
	}
}
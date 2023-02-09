import java.util.Scanner;

public class Main {
	static int n;
	static StringBuffer sb = new StringBuffer();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		
		for (int i = 2; i <= 9; i++) { //가장 왼쪽 자리 수. 1은 소수 아님
			if(isSosu(i)) countSosu(1, i);
		}
		System.out.println(sb);
	}

	private static void countSosu(int len, int num) {
		if (len == n) {
			if (isSosu(num)) sb.append(num).append('\n');
			return;
		}
		
		for (int i = 0; i <= 9; i++) { //중복순열
			int newNum = num * 10 + i;
			if (isSosu(newNum))
				countSosu(len + 1, newNum);
		}
	}

	private static boolean isSosu(int num) {
		boolean isDiv = true;
		for (int i = 2; i < num; i++) {
			if (num % i == 0) {
				isDiv = false;
				break;
			}
		}
		return isDiv;
	}
}
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		//input
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		//compute
		int pibo0 = 0;
		int pibo1 = 1;
		
		//n이 0, 1일 때 처리
		int piboCur = 0; //기본값0
		if(n == 1) piboCur = 1;
		
		//i = 0, 1은 이미 정해져있으니
		for (int i = 2; i <= n; i++) {
			piboCur = pibo0 + pibo1;
			pibo0 = pibo1;
			pibo1 = piboCur;
		}
		System.out.println(piboCur);
	}
}

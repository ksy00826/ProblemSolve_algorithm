import java.util.Scanner;

public class Main {
	static int N;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		long[] num0 = new long[N+1];
		long[] num1 = new long[N+1];
		num0[0] = 0; 
		num0[1] = 0; 
		num1[0] = 0; 
		num1[1] = 1; //1
		for (int i = 2; i <= N; i++) {
			num0[i] = num0[i-1] + num1[i-1];
			num1[i] = num0[i-1];
		}
		
		System.out.println(num0[N] + num1[N]);
	}
}
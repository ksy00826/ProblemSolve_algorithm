import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int S, P;
		S = sc.nextInt();
		P = sc.nextInt();

		String str = sc.next(); //nextLine하면 안됨
		int A, C, G, T;
		A = sc.nextInt();
		C = sc.nextInt();
		G = sc.nextInt();
		T = sc.nextInt();

		//logic
		int cnt = 0;
		int a = 0;
		int c = 0;
		int g = 0;
		int t = 0;

		for (int i = 0; i < str.length(); i++) {
			//check dna
			switch(str.charAt(i)) {
			case 'A': a++; break;
			case 'C': c++; break;
			case 'G': g++; break;
			case 'T': t++; break;
			}
			if (i >= P) {
				switch(str.charAt(i - P)) {
				case 'A': a--; break;
				case 'C': c--; break;
				case 'G': g--; break;	
				case 'T': t--; break;
				}
			}

			if (i >= P-1 && a >= A && c >= C && g >= G && t >= T) cnt++; //i : 0~P-2일때까진 세면 안 됨
		}
		System.out.println(cnt);
	}
}
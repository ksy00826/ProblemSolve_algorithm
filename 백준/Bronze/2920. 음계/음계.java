import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int asc = 1;
		int des = 8;
		boolean ASC = true;
		boolean DES = true;
		
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < 8; i++) {
			int num = sc.nextInt();
			if (num != asc) ASC = false;
			if (num != des) DES = false;
			asc++; des--;
		}
		if (ASC) System.out.println("ascending");
		else if (DES) System.out.println("descending");
		else System.out.println("mixed");
	}
}
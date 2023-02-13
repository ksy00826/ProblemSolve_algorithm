import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static List<Integer> circle;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		circle = new ArrayList<>();
		int[] seq = new int[N];
		
		for (int i = 1; i <= N; i++) {
			circle.add(i);
		}
		int i = 0;
		int idx = 0;
		while(circle.size() != 0) {
			idx = myIdx(idx, K);
			seq[i++] = circle.get(idx);
			circle.remove(idx);
		}
		
		//print
		
		System.out.print("<");
		for (i = 0; i < seq.length-1; i++) {
			System.out.print(seq[i] + ", ");
		}
		System.out.println(seq[N-1] + ">");
	}

	private static int myIdx(int idx, int k) {
		idx += k-1;
		return idx % (circle.size());
	}
}
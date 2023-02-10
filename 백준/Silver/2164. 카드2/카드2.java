import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Queue<Integer> q = new ArrayDeque<>();
		
		int N = sc.nextInt();
		
		for (int i = 1; i <= N; i++) q.add(i);
		
		//logic
		
		while(true) {
			if(q.size() == 1) break;
			q.poll();
			if(q.size() == 1) break;
			q.offer(q.poll());
		}
		
		System.out.println(q.poll());
	}
}
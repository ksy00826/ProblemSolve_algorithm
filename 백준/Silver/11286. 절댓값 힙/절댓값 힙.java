import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Comparator;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				if (Math.abs(o1) != Math.abs(o2)) 
					return Math.abs(o1) - Math.abs(o2);
				return o1 - o2;
			}
			
		});
		StringBuffer sb = new StringBuffer();
		
		while(T-- > 0) {
			int cmd = sc.nextInt();
			
			if (cmd == 0) {
				int res = 0;
				if (!pq.isEmpty()) res = pq.poll();
				sb.append(res).append("\n");
			}
			else {
				pq.offer(cmd);
			}
		
		}
		System.out.println(sb);
	}
}
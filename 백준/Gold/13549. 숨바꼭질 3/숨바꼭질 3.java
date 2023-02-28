import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static class Pos {
		int x, depth;

		public Pos(int x, int depth) {
			super();
			this.x = x;
			this.depth = depth;
		}
	}

	static int N, K;
	static int minTime = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		K = sc.nextInt();
		
		//dfs로 할까 했는데 보니까 최소 시간이니까 bfs가 효율적!
		
		Queue<Pos> q = new ArrayDeque<>();
		int[] times = new int[100001];//0~100000
		Arrays.fill(times, Integer.MAX_VALUE);
		
		q.offer(new Pos(N, 0));
		times[N] = 0;

		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			if (inRange(cur.x-1) && times[cur.x-1] > times[cur.x]) {
				times[cur.x-1] = times[cur.x] + 1;
				q.offer(new Pos(cur.x-1, cur.depth+1));
			}
			if (inRange(cur.x+1) && times[cur.x+1] > times[cur.x]) {
				times[cur.x+1] = times[cur.x] + 1;
				q.offer(new Pos(cur.x+1, cur.depth+1));
			}
			if (inRange(cur.x*2) && times[cur.x*2] > times[cur.x]) {
				times[cur.x*2] = times[cur.x];
				q.offer(new Pos(cur.x*2, cur.depth));
			}
		}
		System.out.println(times[K]);
	}
	private static boolean inRange(int i) {
		return i >= 0 && i <= 100000;
	}
}
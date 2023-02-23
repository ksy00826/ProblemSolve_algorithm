import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static class Pos{
		int x, time;

		public Pos(int x, int time) {
			super();
			this.x = x;
			this.time = time;
		}
		
	}
	
	static int N, K;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] line = in.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		K = Integer.parseInt(line[1]);
		
		//logic
		Queue<Pos> q = new ArrayDeque<>();
		boolean[] visited = new boolean[100001];
		
		q.offer(new Pos(N, 0));
		visited[N] = true;
		
		int[] dx = {-1, 1};
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			if (cur.x == K) {
				System.out.println(cur.time);
				System.exit(0);
			}
			
			for (int i = 0; i < 2; i++) {
				int nx = cur.x + dx[i];
				if (nx < 0 || nx > 100000) continue;
				if (visited[nx]) continue;
				
				visited[nx] = true;
				q.offer(new Pos(nx, cur.time + 1));
			}
			if (cur.x*2 <= 100000) {
				if (!visited[cur.x*2]) {
					visited[cur.x*2] = true;
					q.offer(new Pos(cur.x*2, cur.time + 1));
				}
			}
		}
	}
}
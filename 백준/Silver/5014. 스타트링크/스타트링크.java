import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Pos{
		int x, cnt;

		public Pos(int x, int cnt) {
			super();
			this.x = x;
			this.cnt = cnt;
		}
		
	}

	static int H, targetH, curH;
	static int U, D;
	static int minCnt = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		H = Integer.parseInt(st.nextToken());
		curH = Integer.parseInt(st.nextToken());
		targetH = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		minCount = new int[H+1];
		dijkstra(new Pos(curH, 0));
		
		if (minCount[targetH] == Integer.MAX_VALUE) 
			System.out.println("use the stairs");
		else
			System.out.println(minCount[targetH]);
	}

	static int[] minCount;
	static boolean[] visited;
	static PriorityQueue<Pos> pq;
	private static void dijkstra(Pos start) {
		//pq, count, visited
		pq = new PriorityQueue<>(new Comparator<Pos>() {
			@Override
			public int compare(Pos o1, Pos o2) {
				return o1.cnt - o2.cnt;
			}
		});
		pq.offer(start);
		
		Arrays.fill(minCount, Integer.MAX_VALUE);
		minCount[start.x] = 0; // 시작 위치는 0으로
		
		visited = new boolean[H+1];
		
		while(true) {
			Pos cur = pq.poll();
			
			//종료조건
			if (cur == null) {
				//갈 수 없다..
				break;
			}
			if (cur.x == targetH) {
				break;
			}
			
			if (visited[cur.x]) continue;
			visited[cur.x] = true; 
			
			//push
			int nextU = cur.x + U;
			checkAndPush(nextU, cur);
			int nextD = cur.x - D;
			checkAndPush(nextD, cur);
		}
		
	}
	private static void checkAndPush(int next, Pos cur) {
		if (next >= 1 && next <= H && !visited[next] && minCount[next] > cur.cnt + 1) {
			minCount[next] = cur.cnt + 1;
			pq.offer(new Pos(next, minCount[next]));
		}
	}

}
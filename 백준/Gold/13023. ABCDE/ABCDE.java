import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static class Vertex{
		int no;
		Vertex link;
		
		public Vertex(int no, Vertex link) {
			super();
			this.no = no;
			this.link = link;
		}
		@Override
		public String toString() {
			return "Vertex [no=" + no + ", link=" + link + "]";
		}
		
	}
	static int N, M;
	static Vertex[] adjList;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		adjList = new Vertex[N]; //0~N-1
		while(M-- > 0) {
			st = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			adjList[from] = new Vertex(to, adjList[from]);
			adjList[to] = new Vertex(from, adjList[to]);
		}
//		for (int i = 0; i < N; i++) {
//			System.out.println(adjList[i]);
//		}
		//logic
		/*
		 * 0~N-1까지 for로 돌면서
		 * adjList 원소를 탐색하며
		 * depth 4까지 내려갈 수 있는지!
		 * 내려가면 -> 1출력하고 바로 리턴
		 * 안내려가면... 0 출력
		 * 시간초과나려나?
		 */
		
		for (int start = 0; start < N; start++) {
//			System.out.println("start: " + start);
			visited = new boolean[N];
			visited[start] = true;
			if (search(start, 0)) {
				System.out.println("1");
				return;
			}
		}
		System.out.println("0");
	}
	static boolean[] visited;
	private static boolean search(int start, int depth) {
//		System.out.println(start + " " + depth);
		if (depth == 4) return true;
		
		Vertex cur = adjList[start];
		for (Vertex adj = cur; adj != null; adj = adj.link) {
			if (visited[adj.no]) continue;
			
			visited[adj.no] = true;
			if (search(adj.no, depth+1)) return true;
			visited[adj.no] = false; 
		}
		return false;
	}
}
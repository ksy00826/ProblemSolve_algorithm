import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

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
	static int N = 100;
	static Vertex[] adjList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		
		for (int tc = 1; tc <= 10; tc++) {
			sb.append("#").append(tc).append(" ");

			int inputN, startNo;
			
			StringTokenizer st = new StringTokenizer(in.readLine());
			inputN = Integer.parseInt(st.nextToken());
			startNo = Integer.parseInt(st.nextToken());
			
			//인접리스트
			adjList = new Vertex[N+1]; //배열 원소마다는 할 필요 없음
			
			st = new StringTokenizer(in.readLine());
			for (int i = 0; i < inputN/2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				adjList[from] = new Vertex(to, adjList[from]);
			}
			
//			for (Vertex v : adjList) {
//				System.out.println(v);
//			}
			
			//startNo부터 시작하여 bfs로 인접 노드를 방문. 방문한 것들 중 depth가 가장 큰 노드 번호 출력
			int lastNo = bfs(startNo);
			
			sb.append(lastNo).append("\n");
		}
		System.out.println(sb);
	}

	static class Node{
		int no;
		int depth;
		public Node(int no, int depth) {
			super();
			this.no = no;
			this.depth = depth;
		}
		
	}
	private static int bfs(int startNo) {
		Queue<Node> q = new ArrayDeque<>();
		boolean[] visited = new boolean[N+1]; //1-N
		
		q.offer(new Node(startNo, 0));
		visited[startNo] = true;
		
		int lastDep = 0;
		int lastNo = 0;
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			//depth 검사
			if (cur.depth > lastDep) {
				lastDep = cur.depth;
				lastNo = cur.no;
			}
			else if (cur.depth == lastDep) {
				lastNo = Math.max(lastNo, cur.no);
			}
			
			if (adjList[cur.no] == null) continue; //없으면 패스
			for (Vertex adj = adjList[cur.no]; adj != null; adj = adj.link) {
				if (visited[adj.no]) continue;
				
				//방문하지 않았다면 방문 가능
				visited[adj.no] = true;
				q.offer(new Node(adj.no, cur.depth + 1));
			}
		}
		return lastNo;
	}
}
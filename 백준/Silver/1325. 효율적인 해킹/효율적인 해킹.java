import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {
	//dfs
	static int N, M;
	static List<List<Integer>> connection = new ArrayList<>();
	static int maxCnt = 0;
	static boolean[] visited;
	static int[] count;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		
		String[] nm = in.readLine().split(" ");
		N = Integer.parseInt(nm[0]);
		M = Integer.parseInt(nm[1]);
		
		//init
		count = new int[N+1];
		for (int i = 0; i <= N; i++) {
			connection.add(new ArrayList<>());//먼저 할당해줌. N개의 컴퓨터
		}
		
		while(M-- > 0) {
			String[] line = in.readLine().split(" ");
			int com1 = Integer.parseInt(line[0]);
			int com2 = Integer.parseInt(line[1]);
			
			connection.get(com1).add(com2);
			//A가 B를 신뢰하는 경우, B를 해킹하면 A도 해킹할 수 있다!! 고로 이렇게 넣어줘야 함.
			//문제를 제대로 읽자...
		}
		
		//logic
		int max = 0;
		for (int com = 1; com <= N; com++) {
			visited = new boolean[N+1]; //1~N
			int cnt = bfs(com);
//			max = Math.max(max, cnt);
		}
        for (int i = 1; i <= N; i++) {
            max = Math.max(max, count[i]);
        }
		
		//print
		for (int i = 1; i <= N; i++) {
			if (max == count[i]) sb.append(i).append(" ");
		}
		System.out.println(sb);
	}
	
	private static int bfs(int com) {
		Queue<Integer> q = new ArrayDeque<>();
		
		q.offer(com);
		visited[com] = true; //헐 이거 빼먹음..---
		
		while(!q.isEmpty()) {
			int node = q.poll();
			
			for (int adj : connection.get(node)) {
				if (visited[adj]) continue;

				visited[adj] = true; //큐로 들어갈때 방문표시
				count[adj]++;
				q.offer(adj);
			}
		}
		return count[com];
	}

}
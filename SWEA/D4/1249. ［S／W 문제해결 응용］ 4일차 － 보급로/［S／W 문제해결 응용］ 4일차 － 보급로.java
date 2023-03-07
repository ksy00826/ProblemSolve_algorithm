import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {

	/*
	 * 최소비용!! 
	 * 배열의 한칸한칸을 정점으로, 깊이를 가중치로..
	 * 가중치를 최소로..
	 * 
	 * 다익스트라
	 * 하나의 정점에서 다른 모든 정점으로의 최단경로
	 * 
	 * distance 배열을 만들어서 다 무한으로 초기화
	 * 출발점인 0은 0으로 설정
	 * 
	 * 반복
	 * 1. 미방문 정점 중 distance 비용이 가장 최소인 정점 찾기(=> Priority Queue)
	 * 2. 그 정점을 경유지로 하여 방문할 수 있는 정점(인접한 정점)의 최소비용 갱신(=> pq. offer)
	 * 
	 * 여기서는 도착지가 G. G를 방문하면 그 때 값이 G까지의 최솟값
	 * 
	 * ** pq가 항상 유리할까? 아님. 완전그래프 같은  최악의 경우에는 pq에 간선 수만큼 다 들어갈 수 있음..
	 * 여기서는 pq가 유리함. (왜인지 못들음)
	 */
	static int N;
	static int[][] map;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		
		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(in.readLine());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				char[] line = in.readLine().toCharArray();
				for (int j = 0; j < N; j++) {
					map[i][j] = line[j] - '0';
				}
			}
			
			sb.append("#" + tc + " " + dijkstra() + "\n");
		}
		System.out.println(sb);
	}

	private static int dijkstra() {
		//1. 최소비용 배열
		int[][] minTime = new int[N][N];
		//무한으로 초기화
		for (int i = 0; i < N; i++) {
			Arrays.fill(minTime[i], Integer.MAX_VALUE);
		}
		minTime[0][0] = 0; // 시작점
		
		//2. visited 배열(미방문 정점 관리)
		boolean[][] visited = new boolean[N][N];
		
		//3. PQ : r, c, 출발지에서 자신까지의 최소비용을 일차원배열로 표현
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[2], o2[2]); //비용기반 오름차순(최소비용 뽑)
			}
			
		});
		pq.offer(new int[] {0, 0, minTime[0][0]});
		
		int[] cur = null;
		int r, c, minCost;
		while(true) {
			//step1
			cur = pq.poll();
			r = cur[0];
			c = cur[1];
			minCost = cur[2];
			
			if (r == N-1 && c == N-1) return minCost; //마지막껄 꺼내면 최종 결정된 것.
			
			visited[r][c] = true; //방문 : 여기서 방문한 건 그 떄 그 값이 전체에서 최종인 것.
			
			for (int di = 0; di < 4; di++) {
				int nr = r + dr[di];
				int nc = c + dc[di];
				if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]
						&& minTime[nr][nc] > minTime[r][c] + map[nr][nc]) {
					minTime[nr][nc] = minCost + map[nr][nc];
					pq.offer(new int[] {nr, nc, minTime[nr][nc]}); // 짧은걸로 갱신하고 넣음
				}
			}
		}
	}
}
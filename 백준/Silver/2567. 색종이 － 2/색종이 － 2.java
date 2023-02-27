import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Pos{ //좌표를 나타낼 class
		int r, c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	static int N = 100; //전체 판 크기
	static int T;		//입력 스카프 수
	static int[][] map;	//전체 판
	private static int cnt;	//검은 스카프의 둘레 길이
	static boolean[][] visited;	//판 방문여부
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		//input
		T = Integer.parseInt(in.readLine());
		map = new int[N+2][N+2]; //102*102. 상하좌우 한줄씩은 여분.
		
		for (int i = 1; i <= T; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			drawMap(r, c); //좌표값을 입력받아 검은 스카프 색칠 10X10
		}
		
		//logic
		/*
		 * 입력받은 좌표를 이용해서 정사각형을 다 그린 후, 
		 * 임의의 좌표 i, j에서 시작하여 map 값이 0일 경우 bfs를 하면서
		 * 현재 map[i][j]가 0일 경우, 상하좌우를 검사하며 1이면 cnt++
		 * => 총 cnt 값이 둘레
		 */

		visited = new boolean[N+2][N+2];
		for (int i = 0; i < N+2; i++) {
			for (int j = 0; j < N+2; j++) {
				if (!visited[i][j] && map[i][j] == 0) bfs(i, j);
				//방문하지 않았고, 흰 부분인 경우만 bfs
			}
			
		}
		System.out.println(cnt);
	}
	private static void bfs(int r, int c) {
		Queue<Pos> q = new ArrayDeque<>();
		
		q.add(new Pos(r, c));
		visited[r][c] = true;
		
		//상하좌우
		int[] dr = {1, -1, 0, 0};
		int[] dc = {0, 0, 1, -1};
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			for (int di = 0; di < 4; di++) {
				int nr = cur.r + dr[di];
				int nc = cur.c + dc[di];
				
				//인덱스, 방문 검사
				if (nr < 0 || nr >= N+2 || nc < 0 || nc >= N+2) continue;
				if (visited[nr][nc]) continue;
				if (map[nr][nc] == 1) cnt++; //0과 인접한 1의 개수가 둘레
				else {
					//0인 경우에는 방문체크후 큐에 넣음
					visited[nr][nc] = true;
					q.offer(new Pos(nr, nc));
				}
			}
			
		}
	}
	private static void drawMap(int r, int c) {
		for (int i = r; i < r + 10; i++) {
			for (int j = c; j < c + 10; j++) {
				map[i][j] = 1;
			}
		}
	}
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Pos{
		int no;
		int r, c;
		int depth;
		public Pos(int no, int r, int c, int depth) {
			super();
			this.no = no;
			this.r = r;
			this.c = c;
			this.depth = depth;
		}
		
	}

	static int N, K, S, X, Y;
	static int[][] map;
	static List<Pos> virus = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] > 0) {
					virus.add(new Pos(map[i][j], i, j, 0));
				}
			}
		}
		
		st = new StringTokenizer(in.readLine());
		S = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		
		//logic
		//큐에 넣고 bfs
		Collections.sort(virus, new Comparator<Pos>() {

			@Override
			public int compare(Pos o1, Pos o2) {
				return o1.no - o2.no;
			}
		});
		for (Pos vi : virus){
			q.offer(vi);
		}
		
		bfs();
		
		//print
		System.out.println(map[X-1][Y-1]);
	}
	
	static Queue<Pos> q = new ArrayDeque<>();
	private static void bfs() {
		
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		
		while(!q.isEmpty()) {
			Pos v = q.poll();
			if (v.depth == S) {
				break;
			}
			for (int di = 0; di < 4; di++) {
				int nr = v.r + dr[di];
				int nc = v.c + dc[di];
				if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
				if (map[nr][nc] != 0) continue;
				
				map[nr][nc] = v.no;
				q.offer(new Pos(v.no, nr, nc, v.depth + 1));
			}
			
		}
	}
}
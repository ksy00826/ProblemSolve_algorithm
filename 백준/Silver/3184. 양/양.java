import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static class Pos{
		int r, c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
	}
	static int R, C;
	static char[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] line = in.readLine().split(" ");
		R = Integer.parseInt(line[0]);
		C = Integer.parseInt(line[1]);
		map = new char[R][C];

		int totalO = 0;
		int totalV = 0;
		
		Queue<Pos> wolf = new ArrayDeque<>();
		for (int i = 0; i < R; i++) {
			map[i] = in.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 'v') wolf.offer(new Pos(i, j));
				else if (map[i][j] == 'o') totalO++;
			}
		}
		
		//logic
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};

		
		boolean[][] visited = new boolean[R][C];
		while(!wolf.isEmpty()) {
			Pos v = wolf.poll();
			
			if(visited[v.r][v.c]) continue;
			visited[v.r][v.c] = true;
			int oCnt = 0;
			int vCnt = 0;
			
			Queue<Pos> q = new ArrayDeque<>();
			q.offer(v);
			vCnt++; //시작이 늑대니까
			//양의 수와 늑대 수 카운트
			while(!q.isEmpty()) {
				Pos cur = q.poll();
				
				for (int di = 0; di < 4; di++) {
					int nr = cur.r + dr[di];
					int nc = cur.c + dc[di];
					if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
					if (visited[nr][nc] || map[nr][nc] == '#') continue;
					
					visited[nr][nc] = true;
					q.offer(new Pos(nr, nc));
					if (map[nr][nc] == 'v') vCnt++;
					else if(map[nr][nc] == 'o') oCnt++;
				}
			}
			
			//늑대가 크거나 같으면 양 다 주금
			if (oCnt <= vCnt) {
				totalV += vCnt;
				totalO -= oCnt;
			}
		}
		
		System.out.println(totalO + " " + totalV);
	}
}
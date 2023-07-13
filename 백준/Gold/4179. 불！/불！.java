import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {

	static class Pos{
		int r, c;
		int depth;
		public Pos(int r, int c, int depth) {
			super();
			this.r = r;
			this.c = c;
			this.depth = depth;
		}
		@Override
		public String toString() {
			return "Pos [r=" + r + ", c=" + c + ", depth=" + depth + "]";
		}
		
	}
	static char[][] map;
	static int R, C;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] line = in.readLine().split(" ");
		R = Integer.parseInt(line[0]);
		C = Integer.parseInt(line[1]);
		Queue<Pos> fq = new ArrayDeque<>();
		
		Pos start = null, fire = null;
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = in.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 'J') {
					start = new Pos(i, j, 0);
					map[i][j] = '.'; //빈칸임
				}
				else if (map[i][j] == 'F') {
					fq.offer(new Pos(i, j, 0));
				}
			}
		}
		
		//logic : bfs : 지훈이, 불 각각 큐에 넣고..
		/*
		 * 지훈이는 상하좌우로 이동하고 (. 이동 가능, #, F 이동 불가)
		 * 불은 상하좌우로 퍼지고 (. 이동 가능, # 이동 불가)
		 */
		boolean[][] visitedJ = new boolean[R][C];
		
		Queue<Pos> jq = new ArrayDeque<>();
		
		jq.offer(start);
		visitedJ[start.r][start.c] = true; 
		
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		int saveDepth = -1;
		while(!jq.isEmpty()) {
			//지훈이는 상하좌우로 이동
			Pos curJ = jq.poll();
			
//			System.out.println(curJ);
			
			if (saveDepth != curJ.depth) {
				//불은 상하좌우로 퍼짐
//				System.out.println("fire");
				saveDepth = curJ.depth;
				while(!fq.isEmpty()) {
					Pos curF = fq.poll();

					if (saveDepth != curF.depth) {
						fq.offer(curF); //뺐으니까 다시 넣기..
						break;
					}
					for (int di = 0; di < 4; di++) {
						int nr = curF.r + dr[di];
						int nc = curF.c + dc[di];
						if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
							continue;
						}
						else if (map[nr][nc] == '.') {
							//빈칸일 때만 갈 수 있음
							map[nr][nc] = 'F';
							fq.offer(new Pos(nr, nc, curF.depth+1));
						}
					}
				}
				
			}
//			for (int i = 0; i < R; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
			
			for (int di = 0; di < 4; di++) {
				int nr = curJ.r + dr[di];
				int nc = curJ.c + dc[di];
				
				//인덱스 초과하면 종료
				if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
					System.out.println(curJ.depth+1);
					System.exit(0);
				}
				else if (map[nr][nc] == '.' && !visitedJ[nr][nc]) {
					//불이나 벽이 없으면 이동 가능 -> .이면 이동 가능
					visitedJ[nr][nc] = true;
					jq.offer(new Pos(nr, nc, curJ.depth + 1));
				}
			}
		}
		
		System.out.println("IMPOSSIBLE");
	}
}

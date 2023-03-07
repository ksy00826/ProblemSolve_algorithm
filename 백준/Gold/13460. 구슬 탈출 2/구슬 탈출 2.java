import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static class Pos{
		int r, c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Pos [r=" + r + ", c=" + c + "]";
		}
		
	}
	static int N, M;
	static char[][] map;
	
	static Pos red, blue;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] line = in.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			map[i] = in.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 'B') {
					blue = new Pos(i, j);
				}
				else if (map[i][j] == 'R') {
					red = new Pos(i, j);
				}
			}
		}
		
		//logic
		/*
		 * dfs로 상, 하, 좌, 우 쭉 움직이면서 (빨간색 구슬, 파란색 구슬 같은 방향으로)
		 * 
		 * 백트래킹 : cnt 값 최소보다 커지면 -> return;
		 * 10번 초과 -> return;
		 * 파란색이 빠짐 -> return;
		 * 빨간색만 빠짐 -> minCnt 업뎃. return;
		 */
		
		//cnt, di
		for (int di = 0; di < 4; di++) {
			dfs(1, di, blue, red);
		}
		
		if(minCnt == Integer.MAX_VALUE) System.out.println("-1");
		else System.out.println(minCnt);
	}
	
	static int[] dr = {-1, 1, 0, 0}; //상하좌우
	static int[] dc = {0, 0, -1, 1};
	static int minCnt = Integer.MAX_VALUE;
	
	private static void dfs(int cnt, int di, Pos B, Pos R) {
		Pos b = new Pos(B.r, B.c);
		Pos r = new Pos(R.r, R.c);
		
//		System.out.println("depth: " + cnt);
		if (cnt > 10 || cnt > minCnt) return;
		//파란색 움직임
		//빨간색 움직임 - 같이 해야할듯
		boolean blueStop = false;
		boolean redStop = false;
//		System.out.println("di " + di);
		while(true) {
//			System.out.println("B " + b + " " + "R " + r);
			int bnr = b.r + dr[di];
			int bnc = b.c + dc[di];
			int rnr = r.r + dr[di];
			int rnc = r.c + dc[di];
			
			//반복문 종료조건 : 인덱스 범위가 벗어날 일은 없음. 그냥 샵으로 검사.
			if (map[bnr][bnc] == '#') 
				blueStop = true;
			if (map[rnr][rnc] == '#')
				redStop = true;
			
			//뭐든 빠지면 리턴
			if (map[bnr][bnc] == 'O') return; //파란색이 빠지면 바로 리턴
			if (map[rnr][rnc] == 'O') {
				if (blueBreak(bnr, bnc, di)) return; //파란색도 같이 빠지면 리턴
				minCnt = Math.min(minCnt, cnt);
//				System.out.println("@@@@@@@@@min" + minCnt);
				return;
			}

			if (blueStop && redStop) break;
			//만나면 리턴 아님...
			/*
			 * 다음 갈 곳이 겹치거나
			 * 다음 갈 곳에 현재 상대 구슬이 있는 경우 (쌍방) - 이런 경우는 없음. 한 방향으로 기울어지니까.
			 * 
			 * 붙게 되면 붙어서 이동함.
			 * => 하나가 멈췄는데 다른 하나의 다음이 멈춘거랑 위치가 같으면 업뎃 안함.
			 */
			if (blueStop) {
				//red는 stop 아닐때
				if (rnr == b.r && rnc == b.c) redStop = true;
			}
			if (redStop) {
				//blue는 stop 아닐때
				if (bnr == r.r && bnc == r.c) blueStop = true;
			}
			if (blueStop && redStop) break;
			
			//인덱스 업뎃(이동)
			if (!blueStop) {
				b.r = bnr;
				b.c = bnc;
			}
			if (!redStop) {
				r.r = rnr;
				r.c = rnc;
			}
		}
		
		for(int nextDi = 0; nextDi < 4; nextDi++) {
			if (nextDi == di) continue;
			dfs(cnt+1, nextDi, b, r);
		}
	}

	private static boolean blueBreak(int bnr, int bnc, int di) {
		while(true) {
			if (map[bnr][bnc] == '#') break;
			if (map[bnr][bnc] == 'O') return true;
			bnr += dr[di];
			bnc += dc[di];
		}
		return false;
	}
}
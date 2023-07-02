import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	/*
	 * 좌표 인덱스 변화로 풀 수 있을 것 같음.
	 * **주의할 점
	 * 1. x, y 좌표가 c, r 좌표임!!
	 */
	
	static class Pos{
		int r, c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	//우, 상, 좌, 하
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {1, 0, -1, 0};
	
	static int N;
	static int[][] map = new int[101][101];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		
		//드래곤 커브 입력 및 그리기
		for (int i = 0; i < N; i++) {
			//input
			StringTokenizer st = new StringTokenizer(in.readLine());
			int c = Integer.parseInt(st.nextToken()); //r, c 반대!
			int r = Integer.parseInt(st.nextToken());
			int di = Integer.parseInt(st.nextToken());
			int th = Integer.parseInt(st.nextToken());
			
			List<Integer> dir = new ArrayList<>();
			dir.add(di);
			
			//0세대 그리기
			map[r][c] = 1;
			int nr = r + dr[di];
			int nc = c + dc[di];
			map[nr][nc] = 1;
			
//			System.out.println("@@"+i);
			draw(new Pos(nr, nc), dir, th); //드래곤 커브 그리기
		}
		
		int cnt = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (map[i][j] == 1 && map[i+1][j] == 1 &&
						map[i][j+1] == 1 && map[i+1][j+1] == 1) cnt++;
			}
		}
		System.out.println(cnt);
	}

	private static void draw(Pos start, List<Integer> dir, int th) {
		//start를 시작점으로, dir 방향만큼 가면서 그리기. th가 0보다 작아지면 끝
		if (th < 1) return;

		//기존 dir + dir에 +1%4, reverse 한 거 합치기
		List<Integer> ls = new ArrayList<>();
		for (int di : dir) {
			ls.add((di + 1) % 4);
		}
		Collections.reverse(ls);
		dir.addAll(ls);
		
//		System.out.println(th);
//		System.out.println(ls);

		//그리기
		int nr = start.r;
		int nc = start.c;
		map[nr][nc] = 1;
//		System.out.println(dir);
		for (int di : ls) {
			nr += dr[di];
			nc += dc[di];
			map[nr][nc] = 1;
		}

//		for (int i = 0; i < 10; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
		
		//다음 세대 그리기
		draw(new Pos(nr, nc), dir, th-1);
	}
}
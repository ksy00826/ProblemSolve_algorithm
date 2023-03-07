import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Turn{
		int time;
		char dir;
		public Turn(int time, char dir) {
			super();
			this.time = time;
			this.dir = dir;
		}
	}
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

	//simulation...
	static int N, K;
	static int[][] map;
	static Queue<Turn> turnQ = new ArrayDeque<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());
		K = Integer.parseInt(in.readLine());
		map = new int[N][N];

		//사과의 위치를 받아서 맵에 표시
		StringTokenizer st = null;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(in.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r-1][c-1] = -1; //사과
		}
		int L = Integer.parseInt(in.readLine());
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(in.readLine());
			int time = Integer.parseInt(st.nextToken());
			char dir = st.nextToken().charAt(0);
			turnQ.offer(new Turn(time, dir));
		}

		//simulation
		int time = 0;
		map[0][0] = 2; // 뱀
		//뱀의 정보 : 길이, 방향
		int[] dr = {0, 1, 0, -1}; //우하좌상
		int[] dc = {1, 0, -1, 0};
		int di = 0;
		int headR = 0;
		int headC = 0;
		int tailR = 0;
		int tailC = 0;
		Queue<Pos> snake = new ArrayDeque<>();
		snake.offer(new Pos(0, 0));

		Turn cur = turnQ.poll();
		while(true) {
			time++;
			int nr = headR + dr[di];
			int nc = headC + dc[di];
			//종료조건
			if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
				System.out.println(time);
				return;
			}
			if (map[nr][nc] == 2) { //자신
				System.out.println(time);
				return;
			}

			//이동조건
			if (map[nr][nc] == -1) { //사과
				map[nr][nc] = 2;
				headR = nr;
				headC = nc;
				snake.offer(new Pos(headR, headC));
			}
			else { //빈칸 -> 이동
				map[nr][nc] = 2;
				//좌표도 업뎃
				headR = nr;
				headC = nc;
				snake.offer(new Pos(headR, headC));
				Pos tail = snake.poll();
				tailR = tail.r;
				tailC = tail.c;
				map[tailR][tailC] = 0;
			}
			if (cur != null && time == cur.time) {
				//방향 바꿔줌
				di = getDirIndex(cur.dir, di);
				cur = turnQ.poll();
			}
		}
	}

	private static int getDirIndex(char dir, int di) {
		switch(dir) {
		case 'D':
			di = (di + 1) % 4;
			break;
		case 'L':
			di = (di + 3) % 4;
			break;
		}
		return di;
	}
	
	/*
	 * 실수한 부분
	 * 1. 꼬리 부분을 업뎃하고 0으로 바꿔줘야 함
	 * 2. q가 빌 떄까지 돌았는데 이렇게 하면 종료조건 외에 더 빠ㅃㄹ리 끝나버림. 그래서 그냥 while문 하나로 통합하고 같아지면 poll 하는걸로
	 * 
	 */
}
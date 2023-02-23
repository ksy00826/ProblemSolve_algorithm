import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Pos{
		int h, r, c;
		int depth;

		public Pos(int h, int r, int c, int depth) {
			super();
			this.h = h;
			this.r = r;
			this.c = c;
			this.depth = depth;
		}

	}

	static int N, M, H;
	static int[][][] box;
	static int[][][] count;
	static int maxDay;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		box = new int[H][N][M];


		Queue<Pos> zeroQ = new ArrayDeque<>();
		Queue<Pos> oneQ = new ArrayDeque<>();
		
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(in.readLine());
				for (int k = 0; k < M; k++) {
					box[i][j][k] = Integer.parseInt(st.nextToken());
					if (box[i][j][k] == 0) zeroQ.offer(new Pos(i, j, k, 0));
					if (box[i][j][k] == 1) oneQ.offer(new Pos(i, j, k, 0));
				}
			}
		}
		if (oneQ.size() == H * N * M) {
			System.out.println(0);
			return;
		}

		//logic
		count = new int[H][N][M];
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				Arrays.fill(count[i][j], Integer.MAX_VALUE);
			}
		}

		//1에서 bfs
		//위, 아래, 왼쪽, 오른쪽, 앞, 뒤
		int[] dh = {-1, 1, 0, 0, 0, 0};
		int[] dr = {0, 0, -1, 1, 0, 0};
		int[] dc = {0, 0, 0, 0, -1, 1};
		
		while(!oneQ.isEmpty()) {
			Pos curPos = oneQ.poll();

			for (int di = 0; di < dh.length; di++) {
				int nh = curPos.h + dh[di];
				int nr = curPos.r + dr[di];
				int nc = curPos.c + dc[di];
				if (nh < 0 || nh >= H || nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				if (box[nh][nr][nc] == -1 || box[nh][nr][nc] == 1) continue;
				
				//0일때만 아래
				count[nh][nr][nc] = Math.min(count[nh][nr][nc], curPos.depth+1);
				box[nh][nr][nc] = 1;
				oneQ.offer(new Pos(nh, nr, nc, curPos.depth+1));
			}
		}

		while(!zeroQ.isEmpty()) {
			Pos pos = zeroQ.poll();
			if (box[pos.h][pos.r][pos.c] == 0) {
				System.out.println("-1");
				System.exit(0);
				break;
			}
			maxDay = Math.max(maxDay, count[pos.h][pos.r][pos.c]);
		}
		
		System.out.println(maxDay);
	}
}
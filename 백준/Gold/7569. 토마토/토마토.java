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
	private static boolean[][][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		box = new int[H][N][M];


		Queue<Pos> zeroQ = new ArrayDeque<>();
		Queue<Pos> oneQ = new ArrayDeque<>();
		
		boolean oneFlag = false;
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
		//		while(!zeroQ.isEmpty()) {
		//			Pos pos = zeroQ.poll();
		//			visited = new boolean[H][N][M]; //매번 초기화
		//			boolean isFound = bfs(pos);
		//			if (!isFound || !oneFlag) {
		//				maxDay = -1;
		//				break;
		//			}
		//		}
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

	static int maxDepth;

	private static void bfsOne(Pos start) {
		Queue<Pos> q = new ArrayDeque<>();
		visited = new boolean[H][N][M];

		visited[start.h][start.r][start.c] = true;
		q.offer(start);

		//위, 아래, 왼쪽, 오른쪽, 앞, 뒤
		int[] dh = {-1, 1, 0, 0, 0, 0};
		int[] dr = {0, 0, -1, 1, 0, 0};
		int[] dc = {0, 0, 0, 0, -1, 1};

		while(!q.isEmpty()) {
			Pos curPos = q.poll();

			for (int di = 0; di < dh.length; di++) {
				int nh = curPos.h + dh[di];
				int nr = curPos.r + dr[di];
				int nc = curPos.c + dc[di];
				if (nh < 0 || nh >= H || nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				if (visited[nh][nr][nc] || box[nh][nr][nc] == -1) continue;
				
				//0일때만 아래
				visited[nh][nr][nc] = true;
				count[nh][nr][nc] = Math.min(count[nh][nr][nc], curPos.depth+1);
				q.offer(new Pos(nh, nr, nc, curPos.depth+1));
			}
		}

	}

	private static boolean bfs(Pos start) {
		Queue<Pos> q = new ArrayDeque<>();
		q.offer(start);

		//위, 아래, 왼쪽, 오른쪽, 앞, 뒤
		int[] dh = {-1, 1, 0, 0, 0, 0};
		int[] dr = {0, 0, -1, 1, 0, 0};
		int[] dc = {0, 0, 0, 0, -1, 1};

		//		int depth = 0; 이렇게 하면 안됨. 큐에 같이 넣어줘야 함
		visited[start.h][start.r][start.c] = true;
		while(!q.isEmpty()) {
			Pos curPos = q.poll();
			//			depth++;

			for (int di = 0; di < dh.length; di++) {
				int nh = curPos.h + dh[di];
				int nr = curPos.r + dr[di];
				int nc = curPos.c + dc[di];
				if (nh < 0 || nh >= H || nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				if (visited[nh][nr][nc] || box[nh][nr][nc] == -1) continue; // 뺴먹음. 이건 못지ㅣ나감.. 아 visited 안함
				if (box[nh][nr][nc] == 1) { //최소 depth
					maxDay = Math.max(maxDay, curPos.depth+1);
					return true;
				}
				visited[nh][nr][nc] = true;
				q.offer(new Pos(nh, nr, nc, curPos.depth+1)); //까먹을뻔
			}
		}
		return false;
	}
}

//logic
/*
 * 그냥 완탐같다.
 * 
 * 여러 방향 탐색..
 * 근데 이것도 저번에 한 것 처럼
 * box를 바로 바꾸면 바꾼게 다음에 영향을 미치니까..
 * 그냥 새로운 3차 배열 만들어서 거기에 적으면서 해야할듯
 * 그리고 그 결과를 다시 박스에 저장하려면
 * 깊은복사.
 *
 * N * M * H = 1000000 이정도면 괜찮겠찌?
 * 여기에 6방향
 * 
 * => 시간초과
 * 아님 !!!!! 이렇게 하면 너무 비효율적
 * 어차피 우리가 알고싶은건 토마토가 모두 익을 때까지 며칠이 걸리느냐이다.
 * 그렇기 때문에 for문 돌면서 0이 있으면 bfs 돌려서 가장  가까운 1이 어디있는지 찾음.
 * 그 거리 구함.
 * 모든 0에 대해 거리 구해서 가장 큰 값ㅅ이 답.
 * 
 * => 메모리 초과
 * : 엄청난 실수를 했다 ! visited를 만듦
 * 
 * => 시간초과..
 * 
 * 
 * 게시판
 * 익은 토마토에 대한 데이터 처리를 하셨으면 그 데이터는 삭제를 해야 하는데, 이 코드엔 그런 코드가 없어서,
 *  이미 있던 데이터까지 중복 처리를 하느라 시간이 걸리는 것 같습니다.
 *  처리한 데이터는 버리도록 바꿔보는게 좋을 것 같네요.
 *  추가로 현재 익은 토마토들을 처리하면, 그 토마토 위치는 더이상 처리 할 필요가 없습니다.
 *  큐를 2개 사용함으로써 1초마다 처리해야 하는 익은 토마토 데이터를 분리 할 수 있습니다 
 *  
 *  
 */
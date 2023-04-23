import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static class Cell{
		boolean left, right;

		public Cell() {}
		public Cell(boolean left, boolean right) {
			super();
			this.left = left;
			this.right = right;
		}
		@Override
		public String toString() {
			return "Cell [left=" + left + ", right=" + right + "]";
		}
		
		
	}

	static int N, M, H; //세로선, 가로선, 가로점선
	static Cell[][] map;
	static OutputStream output;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//output = new FileOutputStream("./output.txt");
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken())+1;
		
		map = new Cell[H][N]; //H x N만큼 null로 초기화
		//원소별로 할당 필요
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new Cell();	
			}
		}
//		for (int i = 0; i < H; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
		//이미 놓여진 사다리의 정보
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			map[a][b].right = true;
			map[a][b+1].left = true;
		}
		
		//logic
		/*
		 * 1. 세로선 0~N-1에서 출발 -> 세로선 idx 넘기기
		 * 2. 하나의 세로선마다 밑으로 내려가면서
		 * 	1. 좌, 우를 보고 연결되어 있으면 무조건 그곳으로 감
		 * 	2. 연결되어 있는 곳이 없으면,
		 * 		ㄱ. 왼쪽으로 연결해서 가고
		 * 		ㄴ. 오른쪽으로 연결해서 가고
		 * 		ㄷ. 아래로 그냥 가고
		 * 	3. 새로운 사다리를 연결했으면 cnt+1
		 *		cnt가 3이 되면 더이상 연결하지 않고 그대로 쭉 내려감
		 *		조건을 어길 시 return;
		 *		조건을 만족하면서 끝까지 갔으면 최소 cnt 저장
		 *
		 * dfs로 구현. 매개변수 : startN, curR, curC, cnt
		 * 
		 * ##visited!!
		 */
		visited = new boolean[N][H][N];
		visited[0][0][0] = true;
//		for (int i = 0; i < N; i++) {
//			curC[i] = c
//		}
		curRArr = new int[N];
		curCArr = new int[N];
		
		
		dfs(0, 0);
		
		System.out.println((minCnt == Integer.MAX_VALUE)? -1 : minCnt);
	}
	
	static int minCnt = Integer.MAX_VALUE;
	static boolean[][][] visited;
	static int[] curRArr;
	static int[] curCArr;
	
	private static void dfs(int startN, int cnt) throws IOException {
		int curR = curRArr[startN];
		int curC = curCArr[startN];
		if (cnt > 3) {
			return;
		}
		
		if (curR == H-1 ) {
			if (curC == startN) {
				//한 줄에 대해 조건을 만족한 경우
				if (startN == N-1) {
					//해당 c가 마지막 column이었다면
					minCnt = Math.min(minCnt, cnt); //업데이트하고 리턴
					return;
				}

				//그게 아니면 다음 col로 넘어감. visited 초기화.. 하면 dfs가 안됨. 일단 그냥 3차원으로 저장
				visited[startN+1][0][startN+1] = true;
				curRArr[startN+1] = 0;
				curCArr[startN+1] = startN+1;
				
				dfs(startN+1, cnt);
				visited[startN+1][0][startN+1] = false;
				return;
				}
			else {
				
				return;
			}
		}
		
		
		if (map[curR][curC].right == true) {
			//오른쪽에 연결
			if (!visited[startN][curR][curC+1]) {
				visited[startN][curR][curC+1] = true;
				curCArr[startN]++;
				dfs(startN, cnt);
				curCArr[startN]--;
				visited[startN][curR][curC+1] = false;
			}
			else if (curR+1 < H) {
				visited[startN][curR+1][curC] = true;
				curRArr[startN]++;
				dfs(startN, cnt);
				curRArr[startN]--;
				visited[startN][curR+1][curC] = false;
			}
		}
		else if (map[curR][curC].left == true) {
			//왼쪽에 연결
			//방문을 안했으면 거기로 이동. 방문했으면 그냥 내려감
			if (!visited[startN][curR][curC-1]) {
				visited[startN][curR][curC-1] = true;
				curCArr[startN]--;
				dfs(startN, cnt);	
				curCArr[startN]++;
				visited[startN][curR][curC-1] = false;
			}
			else if (curR+1 < H) {
				visited[startN][curR+1][curC] = true;
				curRArr[startN]++;
				dfs(startN, cnt);
				curRArr[startN]--;
				visited[startN][curR+1][curC] = false;
			}
		}
		else {
			//연결 안 됨 - 왼/오로 이동할 떈 인덱스 체크
			//왼
			if (curC-1 >= 0 && !visited[startN][curR][curC-1] 
					&& (!map[curR][curC-1].left)) {
				map[curR][curC].left = true;
				map[curR][curC-1].right = true;
				visited[startN][curR][curC-1] = true;
				curCArr[startN]--;
				dfs(startN, cnt+1);
				curCArr[startN]++;
				visited[startN][curR][curC-1] = false;
				map[curR][curC].left = false;
				map[curR][curC-1].right = false;
			}
			//오
			if (curC+1 < N && !visited[startN][curR][curC+1]
					&& (!map[curR][curC+1].right)) { //연결되어 있으면 안됨
				map[curR][curC].right = true;
				map[curR][curC+1].left = true;
				visited[startN][curR][curC+1] = true;
				curCArr[startN]++;
				dfs(startN, cnt+1);
				visited[startN][curR][curC+1] = false;
				curCArr[startN]--;
				map[curR][curC].right = false;
				map[curR][curC+1].left = false;
			}
			//아래
			if (curR+1 < H) {
				visited[startN][curR+1][curC] = true;
				curRArr[startN]++;
				dfs(startN, cnt);	
				curRArr[startN]--;
				visited[startN][curR+1][curC] = false;
			}
		}
	}
	
}
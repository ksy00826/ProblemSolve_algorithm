import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static int M;
	static int D;
	static int[][] map;
	static boolean[] visited;
	static int maxNum = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		
		String[] nmd = in.readLine().split(" ");
		N = Integer.parseInt(nmd[0]);
		M = Integer.parseInt(nmd[1]);
		D = Integer.parseInt(nmd[2]);
		visited = new boolean[M];
		
		inputMap();
		dfs(0);
		System.out.println(maxNum);
	}

	private static void dfs(int cnt) {
		if (cnt == 3) {
			int[][] clone = new int[N][M];
			for (int i = 0; i < N; i++) {
				clone[i] = map[i].clone();
			}
			int num = attack(clone); ///!!!. 이차원배열임.
			if (num > maxNum) maxNum = num;
			return;
		}
		
		for (int i = 0; i < M; i++) {
			if (visited[i]) continue;
			
			visited[i] = true;
			dfs(cnt + 1);
			visited[i] = false;
		}
	}
	
	static int[] row = new int[3];
	static int[] col = new int[3];
	

	private static int attack(int[][] myMap) { //bfs?
		int cnt = 0;
		while(true) {
			if (clear(myMap)) break;
			//궁수마다 D 이내이면서 가장 거리가 짧고 ! 가장 왼쪽인 적을 찾는다.(겹쳐도 상관없음).
			int idx = 0;
			for (int i = 0; i < visited.length; i++) {
				if (visited[i]) find(i, idx++, myMap);
				
			}
			// 그리고 걔네만 지도에서 0으로 만들어줌. 그리고 다른 애들 다 row 1씩 증가.
			for (int i = 0; i < 3; i++) {
				if (row[i] == -1 && col[i] == -1) continue;
				//attack은 했지만 같은 애라서 죽진 않은 경우...가 있을수도.(이미 0인 경우는 그냥 패스)-근데 이거 하니까 딴게 틀림
				if (myMap[row[i]][col[i]] == 0) continue;
				myMap[row[i]][col[i]] = 0;
				cnt++;
				
			}
			for (int i = N-1; i >= 0; i--) {
				for (int j = 0; j < M; j++) {
					if (myMap[i][j] == 1) {
						myMap[i][j] = 0;
						if (i + 1 < N) myMap[i+1][j] = 1;
//						System.out.println("down"); //개소름... 내려간걸 또 보고 또 내림..
					}
				}
			}
		}
		return cnt;
	}

	private static boolean clear(int[][] myMap) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (myMap[i][j] == 1) return false;
			}
		}
		return true;
	}

	private static void find(int i, int idx, int[][] myMap) {
		int r1 = N;
		int c1 = i;
		boolean isFound = false;
		
		//가장 왼쪽에 있는 적이니까. - 아니 근데 가장 가까운 적 중에서 거리가 같으면 가장 왼쪽인 적..
		// 아그러면 오른쪽 col부터 탐색하면서 거리 가장 최소인거 뽑자
		int minDist = Integer.MAX_VALUE;
		for (int c = M-1; c >= 0; c--) {
			for (int r = N-1; r >= 0; r--) {
				if (myMap[r][c] == 0) continue;
				
				int dist = getDist(r1, c1, r, c);
				if (dist > D) break;
				
				if (myMap[r][c] == 1 && dist <= minDist) {
					isFound = true;
					row[idx] = r;
					col[idx] = c;
					minDist = dist;
				}
			}
		}
		if (!isFound) {//D 이내에 적이 없는 경우
			row[idx] = -1;
			col[idx] = -1;
		}
	}

	private static int getDist(int r1, int c1, int r2, int c2) {
		return Math.abs(r1-r2) + Math.abs(c1-c2);
	}

	private static void inputMap() throws IOException {
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String[] line = in.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(line[j]);
			}
		}
	}
	
}

/*
 * 이것도 완탐같은데.. 그냥 다 해보는  수밖에 없을듯..
 * 궁수 3명이고, 각 턴마다 거리가 D 이하인 적 중에서 가장 왼쪽에 있는 적 하나 공격함.
 * 
 * 주의점
 * 1. 문제 잘 읽기
 * 		입력값 수, 범위같은거. 요구조건 잘 파악
 * 
 * 2. 이차원 배열의 복사.. dfs같은거 할 때 계속 바뀌는데, 복구시키지 않을 거면 복사해서 넘겨줘야 함.
 * 	근데 이때 복사하려면 각 행마다 다 클론 해줘야 함.
 * 
 */
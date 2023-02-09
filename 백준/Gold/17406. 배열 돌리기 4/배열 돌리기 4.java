import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static int N, M, K;
	static int[][] map;
	static int[][] rotMapdd;
	static int[][] rotation;
	static boolean[] executed;
	static int minArrVal = Integer.MAX_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		//input
		String[] nmk = in.readLine().split(" ");
		N = Integer.parseInt(nmk[0]);
		M = Integer.parseInt(nmk[1]);
		K = Integer.parseInt(nmk[2]);
		map = new int[N][M];
		rotMapdd  = new int[N][M];
		rotation = new int[K][3];
		executed = new boolean[K]; //회전연산 수행했는지

		map = inputArr(N, M);
		rotation = inputArr(K, 3);
		
		//logic
		dfs(0, myClone(map));

		//val
		System.out.println(minArrVal);
	}

	private static void dfs(int cnt, int[][] rotMap) {
		if (cnt == K) {
			//배열 값 구하기 & 최소 저장
			int val = getArrVal(rotMap);
			if (minArrVal > val) {
				rotMapdd = rotMap.clone();
				minArrVal = val;
			}
			return;
		}

		//수행 안 한 연산 수행
		for (int i = 0; i < K; i++) {
			if (executed[i]) continue;

			executed[i] = true;
			dfs(cnt + 1, rotation(i, myClone(rotMap)));
			executed[i] = false;
		}
	}

	

	private static int[][] myClone(int[][] rotMap) { ///하.... 2차원 배열 복사는 이렇게 해줘야 함.. 그래야 깊은복사 됨(주소 달라짐)
		int[][] clone = new int[rotMap.length][rotMap[0].length];
		for (int i = 0; i < rotMap.length; i++) {
			clone[i] = rotMap[i].clone();
		}
		return clone;
	}

	private static int getArrVal(int[][] rotMap) {
		int min = Integer.MAX_VALUE - 1;
		for (int i = 0; i < rotMap.length; i++) {
			int sum = 0;
			for (int j = 0; j < rotMap[0].length; j++) {
				sum += rotMap[i][j];
			}
			if (min > sum) min = sum;
		}
		return min;
	}

	private static int[][] rotation(int rotI, int[][] rotMap) {
		int r, c, s;
		r = rotation[rotI][0]-1;
		c = rotation[rotI][1]-1;
		s = rotation[rotI][2];

		int[] dr = {0, 1, 0, -1};
		int[] dc = {1, 0, -1, 0};
		for (int curS = 1; curS <= s; curS++) {
			int cr = r - curS;
			int cc = c - curS;
			int curVal;
			int preVal = 0;
			int di = 0;//안으로

			while(true) {
				//현재 값 저장 후 이전 값 대입
				curVal = rotMap[cr][cc];
				rotMap[cr][cc] = preVal;
				preVal = curVal;

				//다음 인덱스 계산
				int nr = cr + dr[di];
				int nc = cc + dc[di];
				//처음 위치이면 저장하고 break; -- 위치를 아래 if문 안에 넣었는데 밖으로 뺴야함.ㅠ
				if (nr == r - curS && nc == c - curS) {
					rotMap[nr][nc] = preVal;
					break;
				}

				// - 인덱스 범위 이내면 cr, cc에 nr, nc 대입
				if (nr >= (r - curS) && nr <= (r + curS)
						&& nc >= (c - curS) && nc <= (c + curS)) { //curS 써야 함
					cr = nr;
					cc = nc;
				}
				// - 인덱스 범위 초과하면 di++ 하고 cr cc 업뎃.....하니까 인덱스 반복 안되고 제대로 됨.......
				else {
					di++;
					cr += dr[di];
					cc += dc[di];
				}
			}
		}
		return rotMap;
	}

	private static int[][] inputArr(int n, int m) throws IOException {
		int[][] res = new int[n][m];
		for (int i = 0; i < n; i++) {
			String[] line = in.readLine().split(" ");
			for (int j = 0; j < m; j++) {
				res[i][j] = Integer.parseInt(line[j]);
			}
		}
		return res;
	}
}

/*
 * 배열 크기 NxM, 회전 연산 개수 K
 * 배열 입력
 * 회전 연산 입력
 * 
 * 야구공이랑 크게 다르지 않은 완탐같음
 * 생각해보니 야구공도 dfs로 풀 수 있을 거 같음
 * 이번에는 순열 만들지 말고 dfs 해보자..
 * 생각해보면 dfs도 결국 순열
 * 굳이 순열 만들어서 하지 않아도 됨..
 * 
 * 1. 입력받기
 * 2. 회전연산 K개를 for문 돌면서 뭐부터 시작할지 결정하고
 * 		그 시작부터 dfs를 돈다.
 * 	2.1. dfs(i, cnt) //i : 회전연산 index, cnt : 회전 연산 수행 수
 * 		해당 회전 연산을 수행.
 * 		visited = true;
 * 		for문을 돌면서 수행 안 한 연산에 대해 dfs(j, cnt + 1);
 * 		visited = false;
 * 		* 기저조건 : cnt == k
 * 	2.2. 문제 조건에 따라 배열 합 구하기
 * 		=> 최소인 것 저장!!
 * 3. 출력
 * 
 * 실수한 부분
 * 1. 인덱스 반복
 * rotation을 할 때 매 코너마다 인덱스가 반복되어서 값이 이상하게 저장됨.
 * 
 * 2. 이차원 배열의 깊은 복사
 * 일차원 배열은 그냥 clone만 해줘도 되는데 이차원 배열은 이차원 내 일차원 배열을 for로 돌면서 
 * 일차원 배열마다 clone 해줘야 완전히 복사됨.
 * 이것땜에 시간 엄청 날림 ㅡㅡ
 */
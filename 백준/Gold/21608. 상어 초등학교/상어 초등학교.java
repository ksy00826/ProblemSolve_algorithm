import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	static class Position{
		int r;
		int c;
		int emptyCnt;
		public Position(int r, int c, int emptyCnt) {
			super();
			this.r = r;
			this.c = c;
			this.emptyCnt = emptyCnt;
		}
		public Position() {
			// TODO Auto-generated constructor stub
		}
		
	}
	static int N;
	static int[][] myClass;
	static int[][] myLike;
	static List<Position> result = new ArrayList<>();
	static Map<Integer, List<Integer>> likeInfo = new HashMap<>();
	static int satisfaction = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		//input
		N = Integer.parseInt(in.readLine());
		myClass = new int[N][N];
		myLike = new int[N][N];
		
		//input & logic
		for (int i = 0; i < N*N; i++) {
			//input Person
			String[] line = in.readLine().split(" ");
			int me = Integer.parseInt(line[0]); //아 여기서 0 쓰고 밑에서 또 씀...
			List<Integer> likeLs = new ArrayList<>();
			for (int j = 1; j <= 4; j++) {
				likeLs.add(Integer.parseInt(line[j]));
			}
			likeInfo.put(me, likeLs);
			
			//logic
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					//좋아하는 학생이 앉은 곳 인접한 칸에 myLike+1
					if (likeLs.contains(myClass[r][c])) likeAdd(r, c);
				}
			}
			
			//maxLike 구하기
			int maxLike = -1;
			for(int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (myClass[r][c] != 0) continue;
					
					int like = myLike[r][c];
					if (maxLike < like) {
						result.clear(); //max갱신
						maxLike = like;
						result.add(new Position(r, c, getEmpty(r, c)));
					}
					else if (maxLike == like) {
						result.add(new Position(r, c, getEmpty(r, c)));
					}
				}
			}
			
			//좋아하는 애가 안앉ㅇ아있을수도 잇지. 그러면 걍 비어있는 칸 다..
			if (result.isEmpty()) {
				for(int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						if (myClass[r][c] != 0) continue;
						result.add(new Position(r, c, getEmpty(r, c)));
					}
				}
			}
			
			//result에는 이제 like가 최대인 자리가 비어있는 자리와 함께 입력되어 있음
			//뒤부터 돌면서 비어있는 자리가 최대인 자리!
			int maxEmptyCnt = -1;
			Position maxPos = new Position();
			for (int k = result.size()-1; k >= 0; k--) {
				int cnt = result.get(k).emptyCnt;
				if (maxEmptyCnt <= cnt) { //같아도.. 왜냐면 row col 값떔에(3, 4번 조건)
					maxEmptyCnt = cnt;
					maxPos = result.get(k);
				}
			}
			
			//자리에 앉음
			myClass[maxPos.r][maxPos.c] = me;
			
			//init
			for (int k = 0; k < N; k++) {
				Arrays.fill(myLike[k], 0);
			}
			result.clear();
			
//			System.out.println();
//			for (int[] li : myClass) {
//				for (int person : li) {
//					System.out.print(person + " ");
//				}
//				System.out.println();
//			}
		}
		//좋아하는 학생 수
		findSatisfact();
		System.out.println(satisfaction);
	}
	private static void findSatisfact() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				int me = myClass[r][c];
				int cnt = 0;
				for (int di = 0; di < dr.length; di++) {
					int nr = r + dr[di];
					int nc = c + dc[di];
					//인덱스 체크
					if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
					if (likeInfo.get(me).contains(myClass[nr][nc])) cnt++;
				}
				satisfaction += ((cnt == 0)? 0 : Math.pow(10, cnt-1));
			}
		}
	}
	//위, 아래, 좌, 우
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	private static int getEmpty(int r, int c) {
		int cnt = 0;
		for (int di = 0; di < dr.length; di++) {
			int nr = r + dr[di];
			int nc = c + dc[di];
			//인덱스 체크
			if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
			if (myClass[nr][nc] == 0) cnt++;
		}
		return cnt;
	}

	private static void likeAdd(int r, int c) {
		for (int di = 0; di < dr.length; di++) {
			int nr = r + dr[di];
			int nc = c + dc[di];
			//인덱스 체크
			if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
			myLike[nr][nc]++;
		}
	}
	
}
//logic
/*
 * 이중 for로 배열 돌면서
 * 좋아하는 학생 앉은 곳 주위에 +1;
 * 하면서 maxLike 구하면서 list에 넣고
 * 그 중에서도 인접한 칸 중에 빈칸 많은걸로 list에 넣고 => 첫 번쨰 원소 뽑으면 행열 가장 작은 거 나오겟지.
 */
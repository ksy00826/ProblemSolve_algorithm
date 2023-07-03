import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class Cell{
		int good;
		List<Integer> liveTree;
		List<Integer> deadTree;
		public Cell(int good, List<Integer> liveTree, List<Integer> deadTree) {
			super();
			this.good = good;
			this.liveTree = liveTree;
			this.deadTree = deadTree;
		}
	}

	/*
	 * 주의 : r, c는 1부터
	 * 처음 양분은 5만큼
	 * 
	 * <자료구조>
	 * 나무 : 나이,  => 그냥 int
	 * cell : 양분, 살아있는 나무, 죽은 나무
	 * 
	 */
	
	static int N, M, K; //NxN, 나무 수, K년
	static Cell[][] map;
	static int[][] A;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		//A입력
		A = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//맵 초기화
		map = new Cell[N][N];
		//r, c 다 해줘야 함. 아니면 null pointer error
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				//초기에는 5 만큼의 양분 존재
				map[r][c] = new Cell(5, new ArrayList<>(), new ArrayList<>());
			}
		}
		
		//나무 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int age = Integer.parseInt(st.nextToken());
			map[r][c].liveTree.add(age);
		}
		
		//logic
		while(K-- > 0) {

			//봄 : 양분 섭취
			spring();
			
			//여름 : 죽은 나무가 양분으로
			summer();
			
			//가을 : 나무 번식. age = 5의 배수
			fall();
			
			//겨울 : 기기가 양분 추가
			winter();
		}
		
		//K년 후.. 살아있는 나무의 개수
		int cnt = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				cnt += map[r][c].liveTree.size();
			}
		}
		System.out.println(cnt);
	}

	private static void winter() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				map[r][c].good += A[r][c];
			}
		}
	}

	static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
	private static void fall() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				for (int age : map[r][c].liveTree) {
					if (age % 5 == 0) {
						//인접 8방에 나이 1인 나무 생성
						spread(r, c);
					}
				}
			}
		}
	}

	private static void spread(int r, int c) {
		for (int di = 0; di < 8; di++) {
			int nr = r + dr[di];
			int nc = c + dc[di];
			if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
			//아기나무
			map[nr][nc].liveTree.add(1);
		}
	}

	private static void summer() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				for (int age : map[r][c].deadTree) {
					//나이 / 2 = 새로운 양분
					map[r][c].good += (age / 2); //소수점 아래는 자동 버림
				}
				map[r][c].deadTree.clear(); //다 양분으로 변함
			}
		}
	}

	private static void spring() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				//매 cell마다 나무들을 스캔하여 나이 먹임
				//오름차순 정렬 후 스캔
				Collections.sort(map[r][c].liveTree); //디폴트가 오름차순
				
				int deadIdx = -1;
				for (int i = 0; i < map[r][c].liveTree.size(); i++) {
					//나이만큼의 양분이 있는지 체크
					int age = map[r][c].liveTree.get(i);
					if (map[r][c].good >= age) {
						map[r][c].good -= age;
						map[r][c].liveTree.set(i, age+1); //나이 증가
					}
					else {
						//죽음. 오름차순 정렬이기 때문에 뒤에 있는 것도 다 죽음. 반복문 밖에서 처리
						deadIdx = i; //i~끝까지 다 죽음
						break; //빼먹음
					}
				}
				
				//죽은 나무는 live에서 뺴고, dead에 넣음. remove를 쓰기 위해 끝에서부터 지움
				if (deadIdx != -1) {
					for (int i = map[r][c].liveTree.size() - 1; i >= deadIdx; i--) {
						int deadTree = map[r][c].liveTree.get(i);
						map[r][c].liveTree.remove(i);
						map[r][c].deadTree.add(deadTree);
					}
				}
				
			}
		}
	}
}
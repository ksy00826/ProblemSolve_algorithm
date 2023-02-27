import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	/*
	 * 직교다격형의 모든 꼭짓점은 서로 다르며, 연속한 두 변 이외에는 어떤 두 변도 만나지 않는다..
	 * 
	 * 입력을 (r, c)라고 하면,
	 * c 값이 연속되면서 c > 0인 좌표 두 개의 r 좌표 쌍이 봉우리의 왼쪽, 오른쪽 좌표이다.
	 * 선은 축과 수직되기 때문에 다음과 같은 정보를 저장함으로써 봉우리를 판별할 수 있다.
	 * 
	 * 1. 각 봉우리의 왼쪽 위 좌표와 높이, 오른쪽 위 좌표와 높이를 각각 저장 => (r1, h), (r2, h)
	 * 2. r값을 기준으로 오름차순 정렬
	 * 3. 리스트에서 값을 하나씩 꺼내오면서, 현재 봉우리의 높이(h', 초기값 0)와 꺼내온 봉우리의 높이(h)를 비교
	 * 		h' == 0 : 다른 봉우리에 포함되지 않는 봉우리 개수++
	 * 		h' > h + boolean : 다른 봉우리를 포함하는 봉우리 개수++ (마지막에 전체 봉우리 수 - 이 값)
	 */
	
	static class Pos{ //좌표값 저장(왼쪽위, 오른쪽위 꼭짓점)
		int r, h;

		public Pos(int r, int h) {
			super();
			this.r = r;
			this.h = h;
		}

		@Override
		public String toString() {
			return "Pos [r=" + r + ", h=" + h + "]";
		}

	}
	static int T; //입력 꼭짓점 수
	static List<Pos> pos = new ArrayList<>(); //좌표값을 저장할 리스트
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(in.readLine());
		Pos[] input = new Pos[T];
		
		int startX = Integer.MAX_VALUE;
		int startY = Integer.MAX_VALUE;
		int startI = 0;
		for (int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			input[i] = new Pos(x, y);
			
			if (startX >= x) {
				if(startY >= y) {
					startX = x;
					startY = y;
					startI = i;
				}
			}
		}
		
		
		
		int maxY = 0;
		int minX = Integer.MAX_VALUE;
		int maxX = Integer.MIN_VALUE;
		int preY = 0;
		for (int i = startI; i < T; i++) {
			int x = input[i].r;
			int y = input[i].h;
			
			if (preY < 0 && y > 0) {
				minX = x;
				maxX = x;
				maxY = y;
			}
			else if (preY > 0 && y < 0) {
				pos.add(new Pos(minX, maxY));
				pos.add(new Pos(maxX, maxY));
				minX = Integer.MAX_VALUE;
				maxX = Integer.MIN_VALUE;
				maxY = 0;
			}
			if (y > 0) {
				minX = Math.min(minX, x);
				maxX = Math.max(maxX, x);
				maxY = Math.max(maxY, y); //가장 큰 값으로 저장
			}
			preY = y;
		}
		for (int i = 0; i < startI; i++) {
			int x = input[i].r;
			int y = input[i].h;
			
			if (preY < 0 && y > 0) {
				minX = x;
				maxX = x;
				maxY = y;
			}
			else if (preY > 0 && y < 0) {
				pos.add(new Pos(minX, maxY));
				pos.add(new Pos(maxX, maxY));
				minX = Integer.MAX_VALUE;
				maxX = Integer.MIN_VALUE;
				maxY = 0;
			}
			if (y > 0) {
				minX = Math.min(minX, x);
				maxX = Math.max(maxX, x);
				maxY = Math.max(maxY, y); //가장 큰 값으로 저장
			}
			preY = y;
		}
		//logic
		//sort : r값 기준 오름차순 정렬
		Collections.sort(pos, new Comparator<Pos>() {

			@Override
			public int compare(Pos o1, Pos o2) {
				return o1.r - o2.r;
			}
		});
//		for (Pos p : pos) {
//			System.out.println(pos);
//		}
		
		//리스트에서 값을 하나씩 꺼내오면서, 스택의 봉우리의 높이와 꺼내온 봉우리의 높이를 비교
		int ans1 = 0; //초기값 0
		int ans2 = 0; //초기값 0
		boolean many = false;
		Stack<Integer> hStack = new Stack<>();

		int preH = 0;
		for (Pos p : pos) {
			if (hStack.isEmpty()) { //현재 봉우리 안이 아니라는 뜻. ans1++
				ans1++;
				hStack.add(p.h);//현재 봉우리 값 (봉우리 안으로 들어옴)
				preH = p.h;
			}
			else if (hStack.peek() == p.h) {
				//현재 가장 최근 봉우리가 끝남
				hStack.pop();
				if (many || preH == p.h) {
					ans2++;
					many = false;
				}
			}
			else if (hStack.peek() != p.h) {
				hStack.add(p.h);
				preH = p.h;
				if (hStack.size() >= 2) many = true;
			}
		}
		
		System.out.println(ans1 + " " + ans2);
	}
}
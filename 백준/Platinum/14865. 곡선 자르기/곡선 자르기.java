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
	 * => 이렇게만 넣으면 안됨. 하나의 봉우리에도 여러 개의 모서리가 있을 수 있기 때문에,
	 * 아래 코드와 같이 minX, maxX, maxY를 구해서 넣어줘야 함.(직교다각형은 겹치지 않기 때문에 이렇게 좌표를 정제할 수 있음)
	 * 
	 * 1. 각 봉우리의 왼쪽 위 좌표와 높이, 오른쪽 위 좌표와 높이를 각각 저장 => (r1, h), (r2, h)
	 * 2. r값을 기준으로 오름차순 정렬
	 * 3. 리스트에서 값을 하나씩 꺼내오면서, 스택의 top의 높이와 리스트의 봉우리의 높이(h)를 비교
		 * 1. 스택이 비어있다 : 봉우리 시작.
		 * 		push
		 * 		ans1++
		 * 2. peek == ls.h : 봉우리의 끝.
		 * 		pop
		 * 		여러겹이었거나, 한겹인 경우 딱 한 번만 ans2++
		 * 3. peek != ls.h : 봉우리 안의 다른 봉우리
		 * 		push
		 * 		preH
		 * 		여러겹인 경우 many
		 * 		
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
		
		//input
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

		//logic
		//1. 왼쪽 아래 좌표부터 시작해서 좌표를 정제하여 pos에 넣음
		int maxY = 0;
		int minX = Integer.MAX_VALUE;
		int maxX = Integer.MIN_VALUE;
		int preY = 0;
		int i = startI;
		while(true) {
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
			i++;
			i %= T;
			if (i == startI) break;
		}
		
		//2. sort : r값 기준 오름차순 정렬
		Collections.sort(pos, new Comparator<Pos>() {

			@Override
			public int compare(Pos o1, Pos o2) {
				return o1.r - o2.r;
			}
		});
		
		//3. 리스트에서 값을 하나씩 꺼내오면서, 스택 맨 위의 봉우리의 높이와 꺼내온 봉우리의 높이를 비교
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
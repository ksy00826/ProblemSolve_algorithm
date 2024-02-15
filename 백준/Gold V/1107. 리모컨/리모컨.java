import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Number{
		int no, depth;
		public Number(int no, int depth){
			this.no = no;
			this.depth = depth;
		}
	}
	static boolean[] isBroken;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(in.readLine());
		int brokenN = Integer.parseInt(in.readLine());
		isBroken = new boolean[10]; //0~9 숫자버튼만 고장
		StringTokenizer st = null;
		if (brokenN > 0)
			st = new StringTokenizer(in.readLine());
		//숫자가 전부 고장 -> 무조건 +-로 이동
		if (brokenN == 10){
			System.out.println(Math.abs(num - 100));
			return;
		}
		while(brokenN-- > 0){
			isBroken[Integer.parseInt(st.nextToken())] = true;
		}

		//처음 위치 채널에서, 숫자 바로 눌러서 갈 수 있는지 체크
		boolean direct = false;
		int directCnt = Integer.MAX_VALUE;
		int targetNum = Integer.MAX_VALUE / 2;
		int cnt = Integer.MAX_VALUE / 2;
		Queue<Number> plusQ = new ArrayDeque<>();
		Queue<Number> minusQ = new ArrayDeque<>();
		if (makeOk(num)){
			direct = true;
			directCnt = String.valueOf(num).length();
//			System.out.println("direct " + directCnt);
		}
		plusQ.offer(new Number(num, 0));
		minusQ.offer(new Number(num, 0));

		while(!plusQ.isEmpty() || !minusQ.isEmpty()){
			//마이너스
			if (!minusQ.isEmpty()){
				Number cur2 = minusQ.poll();
				if (cur2.depth > directCnt) break;
				int minusOne = cur2.no-1;
				if (minusOne >= 0){
					if (makeOk(minusOne)){
						targetNum = minusOne;
						cnt = cur2.depth+1;
//						System.out.println("minus");
//						System.out.println(targetNum + " " + cnt);
						break;
					}
					else minusQ.offer(new Number(minusOne, cur2.depth+1));
				}
			}
			if (!plusQ.isEmpty()){
				Number cur1 = plusQ.poll();
				if (cur1.depth > directCnt) break;

				int plusOne = cur1.no+1;
				if (makeOk(plusOne)){
					targetNum = plusOne;
					cnt = cur1.depth+1;
//					System.out.println("plus");
//					System.out.println(targetNum + " " + cnt);
					break;
				}
				else plusQ.offer(new Number(plusOne, cur1.depth+1));

			}

		}

//		System.out.println(targetNum);
//		System.out.println(cnt);
		//숫자만 누르는 것 or 숫자를 누르고, 이동하는것 or 100에서 바로 이동하는 것 중 작은 거 출력
		int minValue = Integer.MAX_VALUE;
		if (direct) minValue = directCnt;
		System.out.println(Math.min(minValue, Math.min(String.valueOf(targetNum).length() + cnt, Math.abs(num - 100))));
	}

	private static boolean makeOk(int plusOne) {
		boolean broken = false;
		for (char c : String.valueOf(plusOne).toCharArray()){
			if (isBroken[c-'0']){
				broken = true;
				break;
			}
		}
		return !broken;
	}
}
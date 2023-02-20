import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, targetR, targetC;
	static int cnt;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		targetR = Integer.parseInt(st.nextToken());
		targetC = Integer.parseInt(st.nextToken());

		//logic
		//		System.out.println((int)Math.pow(2, N));
		visit(0, 0, (int)Math.pow(2, N));
	}

	private static void visit(int r, int c, int size) {
		//2X2이고, targetIndex가 포함되어 있다면 cnt 저장하고 끝
		if (size == 2 && ((r == targetR || r+1 == targetR) && (c == targetC || c+1 == targetC))) {
			//System.out.println("found");
			//계산
			if (r == targetR) {
				if (c == targetC) cnt += 1;
				else cnt += 2;
			}
			else {
				if (c == targetC) cnt += 3;
				else cnt += 4;
			}
			System.out.println(cnt-1);
			System.exit(0);
		}
		if (size == 2) {
			//System.out.println("size2");
			cnt += 4;
			return;
		}

		int half = size/2;
		if (targetR >= r && targetR < r + half && targetC >= c && targetC < c + half) {
			visit(r, c, half);
		}
		else if (targetR >= r && targetR < r + half && targetC >= c + half && targetC < c + 2*half) {
			cnt += (half*half);
			visit(r, c + half, half);
		}
		else if (targetR >= r + half && targetR < r + half*2 && targetC >= c && targetC < c + half) {
			cnt += (half*half)*2;
			visit(r + half, c, half);
		}
		else {
			cnt += (half*half)*3;
			visit(r + half, c + half, half);
		}
	}
}
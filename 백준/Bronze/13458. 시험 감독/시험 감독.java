import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, B, C;
	static int[] A;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		A = new int[N];
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(in.readLine());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		//logic
		//총 감독관은 무조건 1명.
		long bCnt = 0;
		long cCnt = 0;
		for (int num : A) {
			bCnt++;
			long rest = num - B;
			if (rest <= 0) continue;
			
			cCnt += (rest / C);
			if (rest % C != 0) cCnt++;
		}
		
		System.out.println(bCnt + cCnt);
	}
}
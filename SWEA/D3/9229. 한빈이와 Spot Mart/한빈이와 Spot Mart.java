import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	static int maxW = -1;
	static int N;
	static int M;
	static boolean[] selected;
	static int[] weight;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		
		int T = Integer.parseInt(in.readLine());
		int tc = 1;
		while(tc <= T) {
			sb.append("#" + tc + " ");
			
			//input
			String[] nm = in.readLine().split(" ");
			N = Integer.parseInt(nm[0]);
			M = Integer.parseInt(nm[1]);
			
			weight = new int[N];
			String[] line = in.readLine().split(" ");
			for (int i = 0; i < N; i++) {
				weight[i] = Integer.parseInt(line[i]);
			}
			
			//logic
			selected = new boolean[N];
			comb(0, 0, 0);
			
			sb.append(maxW).append("\n");
			
			//init
			maxW = -1;
			tc++;
		}
		System.out.println(sb);
	}

	private static void comb(int cnt, int sum, int start) {
		if (cnt == 2) {
			if (maxW < sum && sum <= M) maxW = sum;
			return;
		}
		
		for (int i = start; i < N; i++) {
			if (selected[i]) continue;
			
			selected[i] = true;
			comb(cnt + 1, sum + weight[i], i + 1);
			selected[i] = false;
		}
	}
}
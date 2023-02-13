import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] nm = in.readLine().split(" ");
		int N = Integer.parseInt(nm[0]);
		int M = Integer.parseInt(nm[1]);

		String[][] pan = new String[N][M];
		for (int i = 0; i < N; i++) {
			pan[i] = in.readLine().split("");
		}
		int minCnt = 100;
		
		for (int i = 0; i < N-7; i++) {
			for (int j = 0; j < M-7; j++) {
				//8x8
				int cnt = 0;

				for (int r = i; r < i + 8; r++) {
					for (int c = j; c < j + 8; c++) {
						if (((r-i) % 2 == 0 && (c-j) % 2 == 0) || ((r-i) % 2 != 0 && (c-j) % 2 != 0)) {
							if (pan[r][c].equals("B")) cnt++;
						}
						else {
							if (pan[r][c].equals("W")) cnt++;
						}
					}
				}
				if (minCnt > cnt) minCnt = cnt;
				
				cnt = 0;
				for (int r = i; r < i + 8; r++) {
					for (int c = j; c < j + 8; c++) {
						if (((r-i) % 2 == 0 && (c-j) % 2 == 0) || ((r-i) % 2 != 0 && (c-j) % 2 != 0)) {
							if (pan[r][c].equals("W")) cnt++;
						}
						else {
							if (pan[r][c].equals("B")) cnt++;
						}
					}
				}
				if (minCnt > cnt) minCnt = cnt;

			}
		}
		System.out.println(minCnt);
	}

}
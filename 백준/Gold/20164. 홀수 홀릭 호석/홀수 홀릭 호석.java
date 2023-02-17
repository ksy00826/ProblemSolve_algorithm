import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int minOddCnt = Integer.MAX_VALUE;
	static int maxOddCnt = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		//입력수 N은 딱 int 범위.. 
		/*
		 * 일단 string으로 받아서
		 * 자릿수 보고 나눔.
		 */
		
		String num = in.readLine();
		split(num, 0);
		System.out.println(minOddCnt + " " + maxOddCnt);
	}

	private static void split(String num, int oddCnt) {
		int len = num.length();
		
		oddCnt += oddCount(num);
		
		if (len == 1) {
			maxOddCnt = Math.max(maxOddCnt, oddCnt);
			minOddCnt = Math.min(minOddCnt, oddCnt);
			return;
		}
		else if (len == 2) {
			int n1 = Integer.parseInt(num.substring(0, 1));
			int n2 = Integer.parseInt(num.substring(1, 2));
			split(Integer.toString(n1 + n2), oddCnt);
		}
		else {
			//세 단위로 나눔. 움 숫자 두 개 골라서 substring 하면 될듯
			for (int i = 1; i < len-1; i++) {
				for (int j = i + 1; j < len; j++) {
					int n1 = Integer.parseInt(num.substring(0, i));
					int n2 = Integer.parseInt(num.substring(i, j));
					int n3 = Integer.parseInt(num.substring(j, len));
					split(Integer.toString(n1 + n2 + n3), oddCnt);
				}
			}
		}
	}

	private static int oddCount(String num) {
		int cnt = 0;
		for (int i = 0; i < num.length(); i++) {
			int n = Integer.parseInt(num.substring(i, i+1));
			if (n % 2 != 0) cnt++;
		}
		return cnt;
	}
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static String S, T;
	static boolean isChange = false;
	static int cntT;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		S = in.readLine();
		T = in.readLine();
		if (subStr(S)) {
			cntT = getACnt(T);
			dfs(S, getACnt(S));
		}
		/*
		 * 시간초과가 좀 날 거 같음.. 
		 * depth 50인 재귀라..
		 * 가지치기를 좀 해야하나? 
		 * 1. T의 A 개수 < S의 A 개수  리턴..
		 * 2. T의 길이 < S의 길이 리턴
		 * 
		 * 흠 이렇게 해도 14퍼에서 시간초과남
		 * substr 넣어도 17에서 시간초과 힝 ㅠㅠ
		 * 
		 */
		int res = (isChange)? 1 : 0;
		System.out.println(res);
	}
	
	private static boolean subStr(String s) { //- contains 하면 됨
//		for (int i = 0; i < T.length()-s.length() + 1; i++) {
//			String str = T.substring(i, i+s.length());
//			StringBuffer sb = new StringBuffer(str);
//			if (str.equals(s) || sb.reverse().toString().equals(s)) {
//				isSub = true;
//				break;
//			}
//		}
		StringBuffer sb = new StringBuffer(T);
		if (T.contains(s) || sb.reverse().toString().contains(s)) return true;
		return false;
	}

	private static void dfs(String str, int cntS) {
		if (str.equals(T)) {
			isChange = true;
			return;
		}
		if (str.length() >= T.length()
				|| cntS > cntT
				|| !subStr(str)) return; //조건에 substr 계속 추가했더니 통과!! 
		
		//연산1
		String newStr = str.concat("A");
		dfs(newStr, cntS + 1);
		
		//연산2 - 문자열 추가 및 뒤집기에 string buffer 이용
		StringBuffer sb = new StringBuffer(str);
		sb.append("B").reverse();
		newStr = sb.toString();
		dfs(newStr, cntS);
		
	}
	private static int getACnt(String str) {
		int cnt = 0;
		
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == 'A') cnt++;
		}
		
		return cnt;
	}
	
}
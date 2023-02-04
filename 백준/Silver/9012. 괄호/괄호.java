import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static StringBuffer sb = new StringBuffer();
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(in.readLine());
		
		while(T-- > 0) {
			String[] line = in.readLine().split("");
			int openCnt = 0;
			int closeCnt = 0;
			boolean isYes = true;
			for (String p : line) {
				if (p.equals("(")) openCnt++;
				else if (p.equals(")")) closeCnt++;
				
				//닫히는 게 더 많을 수 없음
				if (openCnt < closeCnt) {
					isYes = false;
					break;
				}
			}
			
			String res = (openCnt == closeCnt && isYes)? "YES" : "NO";
			sb.append(res + "\n");
		}
		System.out.println(sb);
	}
}

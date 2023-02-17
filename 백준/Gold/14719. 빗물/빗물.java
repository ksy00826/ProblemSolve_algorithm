import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int W, H;
	static int[] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(in.readLine());
		map = new int[W];
		for (int i = 0; i < W; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		
		//logic
		//왼쪽에서부터 시작
		int[] rain = new int[W];
		int maxH = 0;
		for (int i = 0; i < W; i++) {
			if (maxH < map[i]) {
				maxH = map[i];
			}
			rain[i] = maxH;
		}
		
		//오른쪽에서부터 시작
		maxH = 0;
		for (int i = W-1; i >= 0; i--) {
			if (maxH < map[i]) {
				maxH = map[i];
			}
			rain[i] = Math.min(maxH, rain[i]);
		}
		
		//rain구하기
		int rainSum = 0;
		for (int i = 0; i < W; i++) {
			rainSum += rain[i] - map[i];
		}
		System.out.println(rainSum);
	}
}
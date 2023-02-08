import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static StringBuffer sb = new StringBuffer();
	static int n;
	static int[][] map;
	static int[][] sums;
	
	public static void main(String[] args) throws IOException {
		String[] nm = in.readLine().split(" ");
		n = Integer.parseInt(nm[0]);
		int m = Integer.parseInt(nm[1]);
		
		map = new int[n][n];
		inputMap();
		sums = new int[n+1][n+1];
		findSumArr();
		
		while(m-- > 0){
			int[] xy = getLine();
			int x1 = xy[0];
			int y1 = xy[1];
			int x2 = xy[2];
			int y2 = xy[3];
			
			sb.append(sums[x2][y2] - sums[x1-1][y2] - sums[x2][y1-1] + sums[x1-1][y1-1]).append('\n');
		}
		System.out.println(sb);
	}

	private static void findSumArr() {
		for (int i = 1; i <= map.length; i++) {
			for (int j = 1; j <= map[0].length; j++) {
				sums[i][j] = sums[i-1][j] + sums[i][j-1] - sums[i-1][j-1] + map[i-1][j-1];
			}
		}
	}

	private static void inputMap() throws IOException {
		for (int i = 0; i < n; i++) {
			map[i] = getLine();
		}
	}

	private static int[] getLine() throws IOException {
		String[] line = in.readLine().split(" ");
		int[] lineInt = new int[line.length];
		for (int j = 0; j < line.length; j++) {
			lineInt[j] = Integer.parseInt(line[j]);
		}
		return lineInt;
	}
}
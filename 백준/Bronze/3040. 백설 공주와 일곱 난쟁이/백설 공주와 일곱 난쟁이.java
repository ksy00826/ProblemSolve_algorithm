import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N = 9;
	static int[] input;
	private static boolean[] visited;
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input = new int[N];
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(in.readLine());
		}
		
		//크기가 7인 부분집합 구하기
		visited = new boolean[N];
		subset(0, 0, 0);
	}

	private static void subset(int idx, int cnt, int sum) {
		if (cnt == 7 && sum == 100) {
			print();
			return;
		}
		if (idx == N || cnt > 7 || sum > 100) return;
		
		visited [idx] = true;
		subset(idx+1, cnt+1, sum + input[idx]);
		
		visited[idx] = false;
		subset(idx+1, cnt, sum);
		
	}

	private static void print() {
		for (int i = 0; i < N; i++) {
			if (visited[i]) System.out.println(input[i]);
		}
	}
}
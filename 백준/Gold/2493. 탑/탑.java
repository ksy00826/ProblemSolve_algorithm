import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	static int[] top;
	static int[] index;
	static int[] height;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		String[] line = in.readLine().split(" ");
		top = new int[N];
		for (int i = 0; i < N; i++) {
			top[i] = Integer.parseInt(line[i]);
		}
		
		//logic
		index = new int[N];
		height = new int[N];
		int idx = 0;
		
		int[] res = new int[N];
		res[0] = 0;
		for (int i = 1; i < N; i++) {
			if (top[i-1] >= top[i])  {
				res[i] = i;
				index[idx] = i;
				height[idx] = top[i-1];
				idx++;
			}
			else {
				res[i] = getNear(i, idx);
			}
		}
		
		//print
		for (int i : res) {
			System.out.print(i + " ");
		}
	}

	private static int getNear(int curI, int cIdx) {
		int idx = 0;
		for (int i = cIdx; i >= 0; i--) {
			
			if (height[i] >= top[curI]) {
				idx = index[i];
				break;
			}
		}
		return idx;
	}

}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static int N;
	static long[] nums;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());
		nums = new long[N];
		String[] line = in.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			nums[i] = Long.parseLong(line[i]);
		}

		//logic
		Arrays.sort(nums);
		
		//wow
		int maxLen = (N == 1)? 1 : 2;
		for (int i = 0; i < N-2; i++) {
			int j = i + 1;
			int len = 2; //기본값 2
			for (int k = j + 1; k < N; k++) {
				if (nums[i] + nums[j] > nums[k]) len++;
				else break;
			}
			maxLen = Math.max(maxLen, len);
		}
		
		System.out.println(maxLen);
	}
}
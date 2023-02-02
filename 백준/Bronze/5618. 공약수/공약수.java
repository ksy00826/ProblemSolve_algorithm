import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static int[] nums;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		//input
		int N = Integer.parseInt(in.readLine());
		String[] lineArr = in.readLine().split(" ");
		
		nums = new int[N];
		int i = 0;
		for (String numStr : lineArr) {
			nums[i++] = Integer.parseInt(numStr);
		}
		
		//logic
		for (int n = 1; n <= getMinNum(); n++) {
			boolean isDivisor = true;
			for (int num : nums) {
				if (num % n != 0) {
					isDivisor = false;
					break;
				}
			}
			if (isDivisor) System.out.println(n);
		}
	}
	
	public static int getMinNum() {
		int minVal = Integer.MAX_VALUE;
		for (int num : nums) {
			if (minVal > num) minVal = num;
		}
		return minVal;
	}
}

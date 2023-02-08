import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int[] nums = new int[9];
		int sum = 0;
		for (int i = 0; i < 9; i++) {
			nums[i] = Integer.parseInt(in.readLine());
			sum += nums[i];
		}
		
		int targetIdx1 = 0;
		int targetIdx2 = 0;
		for (int i = 0; i < 9; i++) {
			if (targetIdx1 != targetIdx2) break;
			for (int j = i + 1; j < 9; j++) {
				if (i == j) continue; //두 명 뺴야하니까
				if ((sum - nums[i] - nums[j]) == 100) {
					targetIdx1 = i;
					targetIdx2 = j;
					break;
				}
			}
		}
		
		List<Integer> res = new ArrayList<>();
		for (int i = 0; i < 9; i++) {
			if (i != targetIdx1 && i != targetIdx2) res.add(nums[i]);
		}
		
		Collections.sort(res);
		for (int i = 0; i < res.size(); i++)
			System.out.println(res.get(i));
	}
}
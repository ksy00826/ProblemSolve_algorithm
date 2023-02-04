import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		String[] sizes = in.readLine().split(" ");
		int size1 = Integer.parseInt(sizes[0]);
		int size2 = Integer.parseInt(sizes[1]);
		
		int[] arr1 = getLine(size1);
		int[] arr2 = getLine(size2);
		
		int idx1 = 0;
		int idx2 = 0;
		int idx = 0;
		while(idx < size1 + size2) {
			int num1 = (idx1 < size1) ? arr1[idx1] : Integer.MAX_VALUE;
			int num2 = (idx2 < size2) ? arr2[idx2] : Integer.MAX_VALUE;
			
			if (num1 < num2) {
				sb.append(num1 + " ");
				idx1++;
			}
			else {
				sb.append(num2 + " ");
				idx2++;
			}
			idx++;
		}
		System.out.println(sb);
	}
	
	private static int[] getLine(int size) throws IOException {
		String[] arr = in.readLine().split(" ");
		int[] intArr = new int[size];
		for (int i = 0; i < size; i++) {
			intArr[i] = Integer.parseInt(arr[i]);
		}
		return intArr;
	}
}

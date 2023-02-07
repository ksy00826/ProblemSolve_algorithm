import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static StringBuffer sb = new StringBuffer();
	static int[] status;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		//input
		int N = Integer.parseInt(in.readLine());
		status = new int[N + 1];
		String[] arr = in.readLine().split(" ");
		for (int i = 0; i < arr.length; i++) status[i + 1] = Integer.parseInt(arr[i]);
		
		int stuN = Integer.parseInt(in.readLine());
		
		//logic
		while(stuN-- > 0) {
			//inputStudent
			arr = in.readLine().split(" ");
			String who = arr[0];
			int num = Integer.parseInt(arr[1]);
			
			if (who.equals("1")) changeMan(num); //남자
			else 				changeWoman(num); //여자
		}
		
		//print
		int idx = 1;
		while(true) {
			if (idx == status.length) break; //끝
			
			sb.append(status[idx]);
			if (idx % 20 == 0)	sb.append('\n');
			else 				sb.append(' ');
			idx++;
		}
		System.out.println(sb);
	}

	private static void changeWoman(int num) {
		int i = 1;
		onOff(num); //가운데
		while(true) {
			if ((num - i >= 1 && num + i < status.length) //인덱스 0은 제외
					&& (status[num - i] == status[num + i])) {
				onOff(num - i);
				onOff(num + i);
				i++;
			}
			else break;
		}
	}

	private static void changeMan(int num) {
		for (int i = 0; i < status.length; i++) {
			if (i % num == 0) onOff(i);
		}
	}

	private static void onOff(int i) {
		status[i] = (status[i] == 0) ? 1 : 0;
	}
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int dayN = 366; //인덱스 에러
	static int[] day = new int[dayN]; //계속 초기화 까먹음
	
	public static void main(String[] args) throws NumberFormatException, IOException {
//		Scanner sc = new Scanner(System.in);
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		
		//모든 날 입력받으면서 ++
		while (N-- > 0) {
			String[] line = in.readLine().split(" ");
			int start = Integer.parseInt(line[0]);
			int end = Integer.parseInt(line[1]);
			
			addArray(start, end);
		}
		
		//면적 계산
		int area = 0;
		int w = 0, h = 0;
		for (int i = 0; i < dayN; i++) {
			if (day[i] != 0) {
				w++;
				h = (h > day[i])? h : day[i];
			}
			else {
				if (w == 0 && h == 0) continue;
				area += (w * h);
				w = 0;
				h = 0;
			}
		}
		//365에서! 0이 아니면 면적 업뎃 안되니까 -- 됐당! -- 처음과 끝 조건 잘 확인해야 함!!!
		if (day[365] != 0) {
			area += (w * h);
		}
		System.out.println(area);
	}

	private static void addArray(int start, int end) {
		for (int i = start; i <= end; i++) {
			day[i]++;
		}
	}
}
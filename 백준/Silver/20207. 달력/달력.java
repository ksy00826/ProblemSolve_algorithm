import java.util.Scanner;

public class Main {

	/*
	 * 우선 날짜들을 다 입력받고 다음과 같은 기준으로 정렬한다.
	 * 1. 시작일이 빠른 것
	 * 2. 시작일이 같다면, 일정 기간이 긴 것
	 * 
	 * 아니다
	 * 그냥 day[365] 만들고
	 * 시작일과 종료일 입력받으면서
	 * 카운트하고
	 * 가로 = 연속적으로 0이 아닌 길이
	 * 세로 = 겹치는 최대 일정 수
	 */
	static int dayN = 366; //인덱스 에러
	static int[] day = new int[dayN]; //계속 초기화 까먹음
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		//모든 날 입력받으면서 ++
		while (N-- > 0) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			
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
		//365에서! 0이 아니면 면적 업뎃 안되니까
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
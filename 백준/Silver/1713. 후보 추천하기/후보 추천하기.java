import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static class Student{
		int cnt;
		int date;
		public Student(int cnt, int date) {
			super();
			this.cnt = cnt;
			this.date = date;
		}

	}

	static int candidateN, inputN;
	static Student[] students;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		candidateN = Integer.parseInt(in.readLine());
		inputN = Integer.parseInt(in.readLine());

		students = new Student[101];

		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int date = 1; date <= inputN; date++) {
			int stu = Integer.parseInt(st.nextToken());

			if (students[stu] != null) {
				students[stu].cnt++;
			}
			else {
				//오래된거 없애고
				if (candidateN == 0) {
					candidateN++;
					deleteStu();
				}
				//츄가
				students[stu] = new Student(1, date);
				candidateN--;
			}
		}

		//print
		StringBuffer sb = new StringBuffer();
		for (int i = 1; i <= 100; i++) {
			if (students[i] == null) continue;
			sb.append(i).append(" ");
		}
		System.out.println(sb);
	}

	private static void deleteStu() {
		int deleteIdx = 0;
		int minCnt = Integer.MAX_VALUE;
		int minDate = Integer.MAX_VALUE;
		
		for (int i = 1; i < 101; i++) {
			if (students[i] == null) continue;
			if (students[i].cnt < minCnt) {
				minCnt = students[i].cnt;
				minDate = students[i].date;
				deleteIdx = i;
			}
			else if (students[i].cnt == minCnt && students[i].date < minDate) {
				minDate = students[i].date;
				deleteIdx = i;
			}
		}
		students[deleteIdx] = null;
	}


}
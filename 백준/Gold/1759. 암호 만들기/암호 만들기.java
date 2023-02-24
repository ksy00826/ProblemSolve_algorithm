import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	/*
	 * 주어진 C개의 문자 중 L개를 중복 없이, 순서 없이(정렬형태로 출력) 선택하는 문제
	 * 단, 모음은 최소 하나, 자음은 최소 두 개로 제한(조건)
	 * => 조합!!(조건을 만족할 때 결과 리스트에 추가)
	 */
	
	static int C, L;
	static char[] input;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(in.readLine());
		input = new char[C];
		for (int i = 0; i < C; i++) {
			input[i] = st.nextToken().charAt(0);
		}
		
		//logic
		//조합 : idx, cnt, numbers(selected로 대체)
		Arrays.sort(input); //이걸 정렬하면 결과 코드 내부는 정렬 안해도 됨.
		selected = new boolean[C];
		combination(0, 0);
		
		//print
//		Collections.sort(result);
		for (String code : result) {
			System.out.println(code);
		}
	}
	
	static boolean[] selected;
	static List<String> result = new ArrayList<>();
	
	private static void combination(int idx, int cnt) {
		if (cnt == L) {
			//최소 자음 2개, 모음 1개
			if(check()) {
				String str = getCode();
				result.add(str);
			}
			return;
		}
		
		for (int i = idx; i < C; i++) {
			selected[i] = true;
			combination(i+1, cnt+1);
			selected[i] = false;
		}
	}
	
	private static String getCode() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < C; i++) {
			if (selected[i]) sb.append(input[i]);
		}
		return sb.toString();
	}
	
	private static boolean check() {
		int moCnt = 0; //모음 수
		int zaCnt = 0; //자음 수
		
		for (int i = 0; i < C; i++) {
			if (selected[i]) {
				if (input[i] == 'a' ||input[i] == 'e' ||input[i] == 'i' ||
						input[i] == 'o' ||input[i] == 'u') moCnt++;
				else zaCnt++;
			}
		}
		return moCnt >= 1 && zaCnt >= 2;
	}
}
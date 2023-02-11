import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static boolean[] selected;
	static int N;
	static int maxVal = Integer.MIN_VALUE;
	static String[] expression;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		expression = in.readLine().split("");
		selected = new boolean[N];
		
		if (expression.length == 1) maxVal = Integer.parseInt(expression[0]);
		else search(1);
		
		System.out.println(maxVal);
	}

	private static void search(int start) {
		if (start >= N) {
			return;
		}
		
		for (int i = start; i < N; i+=2) {
			if (!selected[i-1] && !selected[i+1] && !selected[i]){ //선택가능한 연산자 - 이 조건 바꾸니까 맞음...
				selected[i] = true;
				selected[i-1] = true;
				selected[i+1] = true;

				//괄호를 적절히 추가..
				int val = getMax(selected.clone());
				if (val > maxVal) maxVal = val;
				
				search(i + 2);
				
				selected[i] = false;
				selected[i-1] = false;
				selected[i+1] = false;
			}
		}
	}

	private static int getMax(boolean[] select) {
		List<String> rest = new ArrayList<>();
		
		for (int i = 0; i < expression.length; i++) {
			if (i % 2 == 0) { //숫자. 선택되지 않은.
				if (!select[i]) rest.add(expression[i]);
			}
			else {			//연산자
				if (select[i]) {
					int num1 = Integer.parseInt(expression[i-1]);
					int num2 = Integer.parseInt(expression[i+1]);
					int res = compute(num1, num2, expression[i]);
					rest.add(Integer.toString(res));
					i++; ///
				}
				else rest.add(expression[i]);
			}
		}

//		for (String re : rest) {
//			System.out.print(re + " ");
//		}
		//나머지 차례로 계산
		int num1 = 0;
		int num2 = 0;
		for (int i = 0; i < rest.size(); i++) {
			if (i % 2 == 0) { //숫자
				num1 = Integer.parseInt(rest.get(i));
			}
			else {
				num2 = Integer.parseInt(rest.get(i+1));
				int res = compute(num1, num2, rest.get(i));
				rest.set(i+1, Integer.toString(res));
			}
		}
//		System.out.println(" = " +num1);
		return num1;
	}

	private static int compute(int num1, int num2, String cmd) {
		int res = 0;
		switch(cmd) {
		case "+": res = num1 + num2; break;
		case "-": res = num1 - num2; break;
		case "*": res = num1 * num2; break;
		//default : System.out.println("error : " + cmd);
		}
		return res;
	}
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

	//+ 다 하고 - 수행?
	static char[] input;
	static Queue<String> addQ = new ArrayDeque<>();
	static Queue<Integer> subQ = new ArrayDeque<>();
	
	static String line;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		line = in.readLine();
		String[] minusSplit = null;
		//minus 기준
		if (line.contains("-")) 
			minusSplit = line.split("-");
		else 
			minusSplit = new String[] {line};
		
		//plus 기준
		String[] plusSplit = null;
		if (minusSplit[0].contains("+"))
			plusSplit = minusSplit[0].split("\\+");
		else
			plusSplit = new String[] {minusSplit[0]};
		
		int result = add(plusSplit);
		
		for (int i = 1; i < minusSplit.length; i++) {
			if (minusSplit[i].contains("+"))
				plusSplit = minusSplit[i].split("\\+");
			else
				plusSplit = new String[] {minusSplit[i]};
			
			result -= add(plusSplit);
		}
		System.out.println(result);
	}

	private static int add(String[] plusSplit) {
		int sum = 0;
		for (String num : plusSplit) {
			int n = Integer.parseInt(num);
			sum += n;
		}
		return sum;
	}

}
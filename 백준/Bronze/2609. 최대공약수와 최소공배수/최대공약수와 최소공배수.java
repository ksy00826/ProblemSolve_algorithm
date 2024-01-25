import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int maxVal = getMaxVal(a, b);
        int minVal = getMinVal(a, b);

        System.out.println(maxVal);
        System.out.println(minVal);
    }

    private static int getMinVal(int a, int b) {
        //최소공배수
        int maxNum = Math.max(a, b);
        while(true){
            if (maxNum % a == 0 && maxNum % b == 0) return maxNum;
            maxNum++;
        }
    }

    private static int getMaxVal(int a, int b) {
        //최대공약수
        int minNum = Math.min(a, b);
        for (int val = minNum; val >= 1; val--){
            if (a % val == 0 && b % val == 0) return val;
        }
        return 1;
    }
}
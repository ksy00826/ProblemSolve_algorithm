import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static final int r = 31;
    static final int M = 1234567891;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        //r 전처리
        long[] rArr = new long[26];
        rArr[0] = 1;
        for (int i = 1; i < 26; i++){
            rArr[i] = (rArr[i-1] * r) % M;
        }

        int L = Integer.parseInt(in.readLine());
        char[] str = in.readLine().toCharArray();
        long sum = 0;
        for (int i = 0; i < L; i++){
            long val = ((str[i] - 'a' + 1) * rArr[i]) % M;
            sum = (sum + val) % M;
        }
        System.out.println(sum);
    }
}
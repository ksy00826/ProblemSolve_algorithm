import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int P = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        //n 팩토리얼 구하기
        long[] factorial = new long[N+1];
        factorial[0] = 1;
        for (int i = 1; i < N+1; i++){
            factorial[i] = factorial[i-1] * i % P;
        }
        long ans = (factorial[N] * power((factorial[K] * factorial[N-K]) % P, P-2)) % P;
        System.out.println(ans);
    }

    private static long power(long n, long r) {
        long ans = 1;
        while(r > 0){
            if (r % 2 == 1){
                ans *= n;
                ans %= P;
            }
            n *= n;
            n %= P;
            r /= 2;
        }
        return ans;
    }
}
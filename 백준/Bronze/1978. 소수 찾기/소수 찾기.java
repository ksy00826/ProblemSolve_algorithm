import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /*
    소수 조건
    1. 1은 소수 아니고, 2는 소수
    2. 3 이상의 경우에는, 2~루트(숫자) 까지의 값으로 나누어 떨어지지 않으면 소수
     */
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(in.readLine());
        int cnt = 0;
        StringTokenizer st = new StringTokenizer(in.readLine());
        while(N-- > 0){
            int num = Integer.parseInt(st.nextToken());

            if (isPrime(num)) cnt++;
        }
        System.out.println(cnt);
    }

    private static boolean isPrime(int num) {
        if (num == 1) return false;
        else if (num == 2) return true;

        for (int i = 2; i <= Math.sqrt(num)+1; i++){
            if (num % i == 0) return false;
        }
        return true;
    }
}
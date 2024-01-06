import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(in.readLine());

        //1 <= N <= 100 이므로 100까지 미리 계산
        int N = 100;
        long[] P = new long[N + 1]; //N = 1~100까지 유효한 값
        P[0] = 0;
        P[1] = 1;
        P[2] = 1;
        P[3] = 1;
        P[4] = 2;
        for (int n = 5; n <= 100; n++){
            P[n] = P[n-1] + P[n-5];
        }

        StringBuilder sb = new StringBuilder();
        while(T-- > 0){
            int n = Integer.parseInt(in.readLine());
            sb.append(P[n]).append("\n");
        }
        System.out.println(sb);
    }
}
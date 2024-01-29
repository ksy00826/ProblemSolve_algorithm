import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] fact = new int[N+1];
        fact[0] = 1;
        for (int i = 1; i < N+1; i++){
            fact[i] = fact[i-1] * i;
        }

        System.out.println(fact[N]/(fact[N-K] * fact[K]));
    }
}
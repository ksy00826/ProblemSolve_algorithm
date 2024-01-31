import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());

        for (int n = 1; n < N; n++){
            int saveN = n;
            int div = (int) Math.pow(10, String.valueOf(n).length()-1);
            int sum = n;
            while(true){
                if (saveN == 0 || div == 0) break;
                sum += (saveN / div);
                saveN -= ((saveN / div) * div);
                div /= 10;
            }
            if (sum == N){
                System.out.println(n);
                return;
            }
        }
        System.out.println(0);
    }
}
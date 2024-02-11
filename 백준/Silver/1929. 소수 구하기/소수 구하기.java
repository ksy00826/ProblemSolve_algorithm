import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        List<Integer> prime = new ArrayList<>();
        for (int i = M; i <= N; i++){
            if (i == 1) continue;
            if (i == 2){
                prime.add(i);
                continue;
            }
            boolean isPrime = true;
            for (int num = 2; num <= Math.sqrt(i)+1; num++){
                if (i % num == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) prime.add(i);
        }
        StringBuilder sb = new StringBuilder();
        for (int num : prime){
            sb.append(num).append("\n");
        }
        System.out.println(sb);
    }
}
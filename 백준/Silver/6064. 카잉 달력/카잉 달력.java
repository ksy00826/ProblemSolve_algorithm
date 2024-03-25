import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(in.readLine());
        while(T-- > 0){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            //해 구하기
            int year = 0;
            while(true){
                int minValue = Math.min(x, y);
                x -= minValue;
                y -= minValue;
                year += minValue;
                if (year > M*N){
                    year = -1;
                    break;
                }
                if (x == 0 && y == 0) break;
                if (x == 0) x = M;
                else y = N;

            }
            sb.append(year).append("\n");
        }
        System.out.println(sb);
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int total = Integer.parseInt(in.readLine());
        int N = Integer.parseInt(in.readLine());
        int ans = 0;
        while(N-- > 0){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ans += (a * b);
        }
        System.out.println((ans == total)? "Yes" : "No");
    }
}
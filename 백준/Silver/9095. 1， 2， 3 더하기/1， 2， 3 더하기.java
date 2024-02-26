import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(in.readLine());
        StringBuilder sb = new StringBuilder();
        while(T-- > 0){
            int n = Integer.parseInt(in.readLine());

            cnt = 0;
            dfs(n);
            sb.append(cnt).append("\n");
        }
        System.out.println(sb);
    }

    static int cnt;
    private static void dfs(int n) {
        for (int i = 1; i <= 3; i++){
            if (n-i == 0) cnt++;
            else if (n-i > 0) dfs(n-i);
        }
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Map<String, String> save = new HashMap<>();
        while(N-- > 0){
            st = new StringTokenizer(in.readLine());
            save.put(st.nextToken(), st.nextToken());
        }
        StringBuilder sb = new StringBuilder();
        while(M-- > 0){
            sb.append(save.get(in.readLine())).append("\n");
        }
        System.out.println(sb);
    }
}
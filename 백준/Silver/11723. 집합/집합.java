import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        Set<Integer> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        while(N-- > 0){
            StringTokenizer st = new StringTokenizer(in.readLine());
            String cmd = st.nextToken();
            switch(cmd){
                case "add":
                    set.add(Integer.parseInt(st.nextToken()));
                    break;
                case "remove":
                    set.remove(Integer.parseInt(st.nextToken()));
                    break;
                case "check":
                    sb.append((set.contains(Integer.parseInt(st.nextToken())))? 1 : 0).append("\n");
                    break;
                case "toggle":
                    int n = Integer.parseInt(st.nextToken());
                    if (set.contains(n)) set.remove(n);
                    else set.add(n);
                    break;
                case "all":
                    set.clear();
                    for (int i = 1; i <= 20; i++){
                        set.add(i);
                    }
                    break;
                case "empty":
                    set.clear();
                    break;
            }
        }
        System.out.println(sb);
    }
}
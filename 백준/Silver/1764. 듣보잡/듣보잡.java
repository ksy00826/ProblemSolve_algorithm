import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < N+M; i++){
            String name = in.readLine();
            int cnt = map.getOrDefault(name, 0);
            map.put(name, cnt+1);
        }

        List<String> names = new ArrayList<>();
        for (String key : map.keySet()){
            if (map.get(key) == 2){
                names.add(key);
            }
        }
        Collections.sort(names);
        StringBuilder sb = new StringBuilder();
        sb.append(names.size()).append("\n");
        for (String name : names){
            sb.append(name).append("\n");
        }
        System.out.println(sb);
    }
}
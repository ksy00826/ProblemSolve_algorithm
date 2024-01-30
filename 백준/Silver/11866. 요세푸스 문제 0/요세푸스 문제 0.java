import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        List<Integer> ls = new ArrayList<>();
        for (int i = 1; i <= N; i++){
            ls.add(i);
        }

        List<Integer> arr = new ArrayList<>();
        int idx = 0;

        while(!ls.isEmpty()){
            idx = (idx + K - 1) % ls.size();
            arr.add(ls.get(idx));
            ls.remove(idx);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        for (int i = 0; i < arr.size()-1; i++){
            sb.append(arr.get(i)).append(", ");
        }
        sb.append(arr.get(arr.size()-1)).append(">");
        System.out.println(sb);
    }
}
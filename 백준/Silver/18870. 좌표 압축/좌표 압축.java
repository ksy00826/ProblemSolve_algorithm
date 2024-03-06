import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        Set<Integer> set = new HashSet<>();
        StringTokenizer st = new StringTokenizer(in.readLine());
        int[] saveArr = new int[N];
        for (int i = 0; i < N; i++){
            int n = Integer.parseInt(st.nextToken());
            set.add(n);
            saveArr[i] = n;
        }

        N = set.size();
        int[] arr = new int[N];
        int idx = 0;
        for (int n : set){
            arr[idx++] = n;
        }

        Arrays.sort(arr);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++){
            map.put(arr[i], i);
        }

        StringBuilder sb = new StringBuilder();
        for (int n : saveArr){
            sb.append(map.get(n)).append(" ");
        }
        System.out.println(sb);
    }
}
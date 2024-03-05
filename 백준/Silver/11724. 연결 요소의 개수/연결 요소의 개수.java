import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static ArrayList<ArrayList<Integer>> graph;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        for (int i = 0; i < N+1; i++){
            graph.add(new ArrayList<Integer>());
        }

        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        visited = new boolean[N+1];
        int cnt = 0;
        for (int i = 1; i <= N; i++){
            if (visited[i]) continue;

            cnt++;
            visited[i] = true;
            dfs(i);
        }
        System.out.println(cnt);
    }

    private static void dfs(int i) {
        for (int adj : graph.get(i)){
            if (visited[adj]) continue;

            visited[adj] = true;
            dfs(adj);
        }
    }
}
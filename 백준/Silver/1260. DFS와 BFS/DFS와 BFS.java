import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

public class Main {

    static int N, M, start;
    static List<List<Integer>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        for (int i = 0; i < N+1; i++){
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        for (int i = 0; i < N+1; i++){
            Collections.sort(graph.get(i));
        }

        visited = new boolean[N+1];
        visited[start] = true;
        sb.append(start).append(" ");
        dfs(start);
        sb.append("\n");
        sb.append(start).append(" ");
        bfs();
        System.out.println(sb);
    }

    private static void bfs() {
        Queue<Integer> pq = new ArrayDeque<>();
        boolean[] visited = new boolean[N+1];
        pq.add(start);
        visited[start] = true;

        while(!pq.isEmpty()){
            int cur = pq.poll();

            for (int adj : graph.get(cur)){
                if (visited[adj]) continue;

                visited[adj] = true;
                pq.offer(adj);
                sb.append(adj).append(" ");
            }
        }
    }

    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    private static void dfs(int start) {
        for (int adj : graph.get(start)){
            if (visited[adj]) continue;

            visited[adj] = true;
            sb.append(adj).append(" ");
            dfs(adj);
        }
    }


}
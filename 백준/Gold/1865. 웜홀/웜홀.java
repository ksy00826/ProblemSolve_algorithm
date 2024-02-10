import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static class Edge{
        int start, end;
        int cost;
        public Edge(int start, int end, int cost){
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }
    static int N, M, W;
    static List<Edge> edges;
    static long[] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        StringBuilder sb = new StringBuilder();
        while(T-- > 0){
            StringTokenizer st = new StringTokenizer(in.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            edges = new ArrayList<>();
            dist = new long[N+1];
            for (int i = 0; i < M; i++){
                st = new StringTokenizer(in.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                edges.add(new Edge(start, end, cost));
                edges.add(new Edge(end, start, cost));
            }

            for (int i = M; i < M+W; i++){
                st = new StringTokenizer(in.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                edges.add(new Edge(start, end, (-1) * cost));
            }

            sb.append((bellmanFord())? "YES" : "NO").append("\n");
        }
        System.out.println(sb);
    }

    static long INF = Long.MAX_VALUE - 100001;

    private static boolean bellmanFord() {
        Arrays.fill(dist, INF);
        dist[1] = 0;

        boolean isCycle = false;
        for (int i = 0; i < N; i++){
            for (Edge edge : edges){
//                if (dist[edge.start] == INF) continue;

                if (dist[edge.end] > dist[edge.start] + edge.cost){
                    if (i == N-1) isCycle = true;
                    dist[edge.end] = dist[edge.start] + edge.cost;
                }
            }
        }
        return isCycle;
    }
}
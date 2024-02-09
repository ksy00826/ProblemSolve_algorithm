import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static class Edge {
        int start;
        int end;
        int cost;
        public Edge(int start, int end, int cost){
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }
    static int N, M;
    static List<Edge> graph;
    static long[] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        //음수 순환되면 첫째줄에 -1
        //아니면 N개의 줄에 각각 도시로 가는 시간을 순서대로 출력. 해당 도시로 가는 경로가 없다면 -1 출력.

        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i < M ;i++){
            st = new StringTokenizer(in.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.add(new Edge(start, end, cost));
        }

        //밸만포드
        dist = new long[N+1];
        Arrays.fill(dist, INF);
        dist[1] = 0;
        if (bellmanFord()){
            System.out.println(-1);
        }
        else{
            StringBuilder sb = new StringBuilder();
            for (int i = 2; i < N+1; i++){
                sb.append((dist[i] == INF)? -1 : dist[i]).append("\n");
            }
            System.out.println(sb);
        }
    }
    static Long INF = Long.MAX_VALUE - 10001;

    private static boolean bellmanFord() {
        //N-1번 반복
        for (int i = 0; i < N-1; i++){
            //모든 간선을 다 보면서 dist 업데이트
            for (Edge e : graph){
                if (dist[e.start] == INF) continue;
                if (dist[e.end] > dist[e.start] + e.cost){
                    dist[e.end] = dist[e.start] + e.cost;
                }
            }
        }

        //한 번 반복해서 순환하는지
        boolean isCycle = false;
        for (Edge e : graph){
            if (dist[e.start] == INF) continue;
            
            if (dist[e.end] > dist[e.start] + e.cost){
                isCycle = true;
            }
        }
        return isCycle;
    }
}
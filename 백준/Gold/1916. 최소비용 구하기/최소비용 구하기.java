import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node{
        int no;
        int cost;
        public Node(int no, int cost){
            this.no = no;
            this.cost = cost;
        }
    }
    static int N, M;
    static ArrayList<Node>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        M = Integer.parseInt(in.readLine());
        graph = new ArrayList[N+1]; // 1~N
        for (int i = 0; i < N+1; i++){
            graph[i] = new ArrayList<>();
        }
        while(M -- > 0){
            StringTokenizer st = new StringTokenizer(in.readLine());

            int start = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[start].add(new Node(dest, cost));
        }

        StringTokenizer st = new StringTokenizer(in.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        dijkstra(start);
        System.out.println(dist[end]);
    }

    static int INF = Integer.MAX_VALUE - 100000;
    static int[] dist;
    private static void dijkstra(int start) {
        //dist, visited, pq
        boolean[] visited = new boolean[N+1];
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.cost - o2.cost;
            }
        });

        //init
        dist = new int[N+1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if (visited[cur.no]) continue;
            visited[cur.no] = true;

            for (Node adj : graph[cur.no]){
                if (dist[adj.no] > dist[cur.no] + adj.cost){
                    dist[adj.no] = dist[cur.no] + adj.cost;
                    pq.offer(new Node(adj.no, dist[adj.no]));
                }
            }
        }
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node{
        int no, cost;
        public Node(int no, int cost){
            this.no = no;
            this.cost = cost;
        }
    }
    static int N, M;
    static List<List<Node>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        M = Integer.parseInt(in.readLine());
        graph = new ArrayList<>();
        for (int i = 0; i < N+1; i++){
            graph.add(new ArrayList<>());
        }

        while(M-- > 0){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, cost));
        }

        int[][] dist = new int[N+1][N+1];
        for (int i = 0; i < N+1; i++){
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        for (int i = 1; i < N+1; i++){
            dijkstra(i, dist[i]);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < N+1; i++){
            for (int j = 1; j < N+1; j++){
                sb.append((dist[i][j] == Integer.MAX_VALUE)? 0 : dist[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void dijkstra(int start, int[] dist) {
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.cost - o2.cost;
            }
        });
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            for (Node adj : graph.get(cur.no)){
                if (dist[adj.no] > dist[cur.no] + adj.cost){
                    dist[adj.no] = dist[cur.no] + adj.cost;
                    pq.offer(new Node(adj.no, dist[adj.no]));
                }
            }
        }
    }
}
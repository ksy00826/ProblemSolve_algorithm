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
    static int V, E, start;
    static List<Node>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(in.readLine());

        graph = new ArrayList[V+1]; // 정점은 1~V번
        for (int i = 0; i < V+1; i++){
            graph[i] = new ArrayList<>();
        }
        while(E-- > 0){
            st = new StringTokenizer(in.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[v1].add(new Node(v2, cost));
        }

        //logic
        Dijkstra();

        for (int i = 1; i < V+1; i++){
            if (dist[i] == INF) System.out.println("INF");
            else System.out.println(dist[i]);
        }
    }

    static int[] dist;
    static int INF = Integer.MAX_VALUE - 100;
    private static void Dijkstra() {
        //최단 경로값 배열, 방문 배열, 우선순위 큐
        boolean[] visited = new boolean[V+1];
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.cost - o2.cost;
            }
        });

        dist = new int[V+1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if (visited[cur.no]) continue;

            visited[cur.no] = true;
            //인접한 노드를 보면서 거리 값 갱신
            for (Node adj : graph[cur.no]) {
                if (dist[adj.no] > dist[cur.no] + adj.cost) {
                    dist[adj.no] = dist[cur.no] + adj.cost;
                    pq.offer(new Node(adj.no, dist[adj.no])); //갱신된 값으로 넣어줘야 함
                }
            }
        }
    }
}
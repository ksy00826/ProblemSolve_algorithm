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
    static int N, E;
    static ArrayList<Node>[] graph;
    static int mid1, mid2;
    static int[][] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N+1]; //1~N
        for (int i = 0; i < N+1; i++){
            graph[i] = new ArrayList<>();
        }
        while(E-- > 0){
            st = new StringTokenizer(in.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[start].add(new Node(end, cost));
            graph[end].add(new Node(start, cost)); //양방향
        }
        st = new StringTokenizer(in.readLine());
        mid1 = Integer.parseInt(st.nextToken());
        mid2 = Integer.parseInt(st.nextToken());

        //logic : dijkstra 3번
        dist = new int[3][N+1];
        dijkstra(1, 0);
        dijkstra(mid1, 1);
        dijkstra(N, 2);

        //print
        long ans1 = dist[0][mid1] + dist[1][mid2] + dist[2][mid2]; //1 -> mid1 -> mid2 -> N
        long ans2 = dist[0][mid2] + dist[1][mid2] + dist[2][mid1]; //1 -> mid2 -> mid1 -> N
        if ((ans1 >= INF || ans1 < 0) && (ans2 >= INF || ans2 < 0)) System.out.println("-1");
//        else if (ans1 >= INF || ans1 < 0) System.out.println(ans2);
//        else if (ans2 >= INF || ans2 < 0) System.out.println(ans1);
        else{
            System.out.println((ans1 > ans2)? ans2 : ans1);
        }
    }

    static int INF = 987654321;
    private static void dijkstra(int start, int i) {
        //pq, visited
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.cost - o2.cost;
            }
        });
        boolean[] visited = new boolean[N+1];

        //init
        Arrays.fill(dist[i], INF);
        dist[i][start] = 0;
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if (visited[cur.no]) continue;
            visited[cur.no] = true;

            for (Node adj : graph[cur.no]){
                if (dist[i][adj.no] > dist[i][cur.no] + adj.cost){
                    dist[i][adj.no] = dist[i][cur.no] + adj.cost;
                    pq.offer(new Node(adj.no, dist[i][adj.no]));
                }
            }
        }
    }
}
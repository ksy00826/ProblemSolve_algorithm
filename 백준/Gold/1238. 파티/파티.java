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
    static int N, M, X;
    static ArrayList<Node>[] graph;
    static int[][] distToX;
    static int[] distToOthers;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        //그래프 입력
        graph = new ArrayList[N+1];
        for (int i = 0; i < N+1; i++){
            graph[i] = new ArrayList<>();
        }
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[start].add(new Node(end, cost));
        }

        //다익스트라
        //1. 각 모든 노드들 ~ 다른 모든 노드들. N번의 다익스트라 시작.
        distToX = new int[N+1][N+1];
        for (int start = 1; start <= N; start++){
            dijkstra(distToX[start], start);
        }
        //2. X에서 ~ 모든 노드
        distToOthers = new int[N+1];
        dijkstra(distToOthers, X);

        //모든 학생들이 왔다갔다 하는 비용의 최댓값
        int maxVal = 0;
        for (int student = 1; student <= N; student++){
            maxVal = Math.max(maxVal, distToX[student][X] + distToOthers[student]);
        }
        System.out.println(maxVal);
    }

    static int INF = Integer.MAX_VALUE - 1000;
    private static void dijkstra(int[] dist, int start) {
        //pq, visited, dist
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.cost - o2.cost;
            }
        });
        boolean[] visited = new boolean[N+1];

        //init
        pq.offer(new Node(start, 0));
        Arrays.fill(dist, INF);
        dist[start] = 0;

        //logic
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
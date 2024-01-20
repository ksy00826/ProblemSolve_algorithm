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
    static ArrayList<Node>[] reverseGraph;
    static int[] distFromX;
    static int[] distToX;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        //그래프 입력
        graph = new ArrayList[N+1];
        reverseGraph = new ArrayList[N+1];
        for (int i = 0; i < N+1; i++){
            graph[i] = new ArrayList<>();
            reverseGraph[i] = new ArrayList<>();
        }
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[start].add(new Node(end, cost));
            reverseGraph[end].add(new Node(start, cost));
        }

        //다익스트라
        distFromX = new int[N+1];
        distToX = new int[N+1];
        //1. X에서 다른 모든 노드까지 다익스트라 1번
        dijkstra(distFromX, graph, X);
        //2. 간선을 뒤집고 X에서 다른 모든 노드까지 다익스트라 1번
        //간선 뒤집기
        dijkstra(distToX, reverseGraph, X);


        //모든 학생들이 왔다갔다 하는 비용의 최댓값
        int maxVal = 0;
        for (int student = 1; student <= N; student++){
            maxVal = Math.max(maxVal, distToX[student] + distFromX[student]);
        }
        System.out.println(maxVal);
    }

    static int INF = Integer.MAX_VALUE - 1000;
    private static void dijkstra(int[] dist, List<Node>[] graph, int start) {
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
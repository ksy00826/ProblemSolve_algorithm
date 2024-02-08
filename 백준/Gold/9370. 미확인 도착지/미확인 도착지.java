import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    /*
    1. 출발지에서 모든 지점까지 다익스트라
        + 경로를 저장하거나 dist 배열과 크기가 같은 배열을 만들어서 g와 h 사이의 도로를 거치는 지 확인하며..
    2. g랑 h 사이를 거치는 후보 지점들에 대해 오룸차순으로 출력
     */
    static class Node{
        int no;
        int cost;
        public Node(int no, int cost){
            this.no = no;
            this.cost = cost;
        }
    }

    static int N, M, targetT;
    static int start, g, h;
    static List<List<Node>> graph;
    static int[] targetNodes;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        StringBuilder sb = new StringBuilder();
        while(T-- > 0){
            StringTokenizer st = new StringTokenizer(in.readLine());
            N = Integer.parseInt(st.nextToken()); //교차로(노드) 개수
            M = Integer.parseInt(st.nextToken()); //도로 개수
            targetT = Integer.parseInt(st.nextToken()); //목적지 후보 개수

            st = new StringTokenizer(in.readLine());
            start = Integer.parseInt(st.nextToken()); //시작 위치
            g = Integer.parseInt(st.nextToken()); //중간 지점1
            h = Integer.parseInt(st.nextToken()); //중간 지점2

            //노드들의 정보를 입력받자
            graph = new ArrayList<>(); //1~N
            for (int i = 0; i < N+1; i++){
                graph.add(new ArrayList<>());
            }

            int midL = 0;
            for (int i = 0; i < M; i++){
                st = new StringTokenizer(in.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                graph.get(a).add(new Node(b, d));
                graph.get(b).add(new Node(a, d));
                if ((a == g && b == h) || (a == h && b == g)) midL = d;
            }

            //목적지 후보들을 입력받자
            targetNodes = new int[targetT];
            for (int i = 0; i < targetT; i++){
                targetNodes[i] = Integer.parseInt(in.readLine());
            }

            //logic
            /*
            1. S -> 목적지 후보들 -- 다익스트라
            2. S -> G -> H -> 목적지후보들
                2.1 S -> G
                2.2 G -> H
                2.3 H -> 목적지후보들 -- 다익스트라
            3. S -> H -> G -> 목적지후보들
                2.1 S -> H
                2.2 H -> G
                2.3 G -> 목적지 후보들 -- 다익스트라

            =>다익스트라 세 번 수행
             */

            int[] distFromStoAll = new int[N+1]; //1~N
            int[] distFromHtoAll = new int[N+1]; //1~N
            int[] distFromGtoAll = new int[N+1]; //1~N
            dijkstra(distFromStoAll, start);
            dijkstra(distFromHtoAll, h);
            dijkstra(distFromGtoAll, g);

            Arrays.sort(targetNodes);
            for (int target : targetNodes){
                if (distFromStoAll[target] == (distFromStoAll[g] + midL + distFromHtoAll[target])
                || distFromStoAll[target] == (distFromStoAll[h] + midL + distFromGtoAll[target])) sb.append(target).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    static int INF = 1000*20000;

    private static void dijkstra(int[] dist, int start) {
        //pq, dist, (?)
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.cost - o2.cost;
            }
        });
        Arrays.fill(dist, INF);
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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int V, E;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        int INF = Integer.MAX_VALUE / 2;
        int[][] dist = new int[V+1][V+1];
        for (int i = 0; i < V+1; i++){
            Arrays.fill(dist[i], INF);
        }
        for (int i = 0; i < E; i++){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            dist[a][b] = cost;
        }

        //플로이드 워샬 - 모든 노드에서 모든 노드로 가는 최솟값

        for (int k = 1; k <= V; k++){
            for (int i = 0; i <= V; i++){
                for (int j = 0; j <= V; j++){
                    dist[i][j] = Math.min(dist[i][k] + dist[k][j], dist[i][j]);
                }
            }
        }

//        for (int i = 1; i < V+1; i++){
//            System.out.println(Arrays.toString(dist[i]));
//        }

        int minCost = INF;
        for (int start = 1; start <= V; start++){
            for (int mid = 1; mid <= V; mid++){
                if (start == mid) continue;

                minCost = Math.min(minCost, dist[start][mid] + dist[mid][start]);
            }
        }
        System.out.println((minCost == INF)? -1 : minCost);
    }
}
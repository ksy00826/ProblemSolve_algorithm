import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] graph;
    static int INF = Integer.MAX_VALUE / 2 -1;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int M = Integer.parseInt(in.readLine());
        graph = new int[N+1][N+1];
        for (int i = 0; i < N+1; i++){
            Arrays.fill(graph[i], INF);
            graph[i][i] = 0; //자기자신은 0
        }
        while(M -- > 0){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[a][b] = Math.min(graph[a][b], cost); //노선이 여러개일 수 있다고 했으니까
        }

        //floyd-warshall
        //경유지
        for (int k = 1; k <= N; k++){
            for (int i = 0; i <= N; i++){
                for (int j = 0; j <= N; j++){
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++){
            for (int j = 1; j <= N; j++){
                sb.append((graph[i][j] == INF)? 0 : graph[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
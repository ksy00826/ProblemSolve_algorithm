import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        int INF = Integer.MAX_VALUE / 2 -1;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int[][] graph = new int[N][N];
        for (int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int j = 0; j < N ;j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] == 0) graph[i][j] = INF;
            }
        }

        for (int k = 0; k < N; k++){
            for (int i = 0; i < N; i++){
                for (int j = 0; j < N; j++){
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                sb.append((graph[i][j] == INF)? 0 : 1).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
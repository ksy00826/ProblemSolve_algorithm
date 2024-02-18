import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int INF = Integer.MAX_VALUE / 2;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] graph = new int[N+1][N+1];
        for (int i = 0; i < N+1; i++){
            Arrays.fill(graph[i], INF);
        }
        while(M-- > 0){
            st  = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a][b] = 1;
            graph[b][a] = 1;
        }

        for (int k = 1; k < N+1; k++){
            for (int i = 1; i < N+1; i++){
                for (int j = 1; j < N+1; j++){
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        int sumIdx = 0;
        int sumValue = Integer.MAX_VALUE;
        for (int i = 1; i < N+1; i++){
            int sum = 0;
            for (int j = 0; j < N+1; j++){
                sum += graph[i][j];
            }
            if (sumValue > sum){
                sumValue = sum;
                sumIdx = i;
            }
        }
        System.out.println(sumIdx);
    }
}
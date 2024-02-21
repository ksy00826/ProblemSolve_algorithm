import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int M = Integer.parseInt(in.readLine());
        boolean[][] graph = new boolean[N+1][N+1];
        while(M-- > 0){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a][b] = true;
            graph[b][a] = true;
        }

        boolean[] visited = new boolean[N+1];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(1);
        visited[1] = true;

        int cnt = 0;
        while(!q.isEmpty()){
            int cur = q.poll();

            for (int adj = 2; adj < N+1; adj++){
                if (!graph[cur][adj] || visited[adj]) continue;

                visited[adj] = true;
                q.offer(adj);
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
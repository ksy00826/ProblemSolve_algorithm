import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Pos{
        int no;
        int time;
        public Pos(int no, int time){
            this.no = no;
            this.time = time;
        }
    }
    static int M = 100001;
    static int[] preNode;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        boolean[] visited = new boolean[M];
        visited[N] = true;

        Queue<Pos> q = new ArrayDeque<>();
        List<Integer> init = new ArrayList<>();
        init.add(N);
        q.offer(new Pos(N, 0));
        preNode = new int[M];
        preNode[N] = -1;
        while(!q.isEmpty()){
            Pos cur = q.poll();

            if (cur.no == K) {
                System.out.println(cur.time);
                findRoot(cur.no);
                break;
            }

            if (cur.no-1 >= 0 && cur.no-1 < M && !visited[cur.no-1]){
                visited[cur.no-1] = true;
                preNode[cur.no-1] = cur.no;
                q.offer(new Pos(cur.no-1, cur.time+1));
            }
            if (cur.no+1 >= 0 && cur.no+1 < M && !visited[cur.no+1]){
                visited[cur.no+1] = true;
                preNode[cur.no+1] = cur.no;
                q.offer(new Pos(cur.no+1, cur.time+1));
            }
            if (cur.no*2 >= 0 && cur.no*2 < M && !visited[cur.no*2]){
                visited[cur.no*2] = true;
                preNode[cur.no*2] = cur.no;
                q.offer(new Pos(cur.no*2, cur.time+1));
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = nodeLs.size()-1; i >= 0; i--){
            sb.append(nodeLs.get(i)).append(" ");
        }
        System.out.println(sb);
    }

    static List<Integer> nodeLs = new ArrayList<>();
    private static void findRoot(int no) {
        if (no == -1) return;
        nodeLs.add(no);
        findRoot(preNode[no]);
    }
}
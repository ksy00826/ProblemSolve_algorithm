import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Pos{
        int idx1, idx2;
        int depth;
        public Pos(int idx1, int idx2, int depth){
            this.idx1 = idx1;
            this.idx2 = idx2;
            this.depth = depth;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= T; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            String str1 = st.nextToken();
            String str2 = st.nextToken();
            String result = st.nextToken();

//            boolean canMake = bfs(str1, str2, result);

            Queue<Pos> q = new ArrayDeque<>();
            boolean[][] visited = new boolean[str1.length()+1][str2.length()+1];
            q.offer(new Pos(0, 0, 0));

            boolean canMake = false;
            while(!q.isEmpty()){
                Pos cur = q.poll();

                if (visited[cur.idx1][cur.idx2]) continue;
                visited[cur.idx1][cur.idx2] = true;

                if (cur.depth == result.length()){
                    canMake = true;
                    break;
                }

                //idx1이 1 증가된 경우
                if (cur.idx1 < str1.length() && (str1.charAt(cur.idx1) == result.charAt(cur.depth))){
                    q.offer(new Pos(cur.idx1+1, cur.idx2, cur.depth+1));
                }
                //idx2가 1 증가된 경우
                if (cur.idx2 < str2.length() && (str2.charAt(cur.idx2) == result.charAt(cur.depth))){
                    q.offer(new Pos(cur.idx1, cur.idx2+1, cur.depth+1));
                }
            }

            sb.append("Data set ").append(i).append(": ").append((canMake)? "yes" : "no").append("\n");
        }
        System.out.println(sb);
    }


}
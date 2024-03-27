import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Pair {
        int num;
        String cmd;
        public Pair(int num, String cmd){
            this.num = num;
            this.cmd = cmd;
        }
    }
    static int N = 10000;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        StringBuilder sb = new StringBuilder();
        while(T-- > 0){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            Queue<Pair> q = new ArrayDeque<>();
            boolean[] visited = new boolean[N];
            q.offer(new Pair(A, ""));
            visited[A] = true;

            while(!q.isEmpty()){
                Pair pair = q.poll();

                if (pair.num == B){
                    sb.append(pair.cmd).append("\n");
                    break;
                }

                //D : 2배 % N
                int d = (pair.num*2) % N;
                if (!visited[d]){
                    visited[d] = true;
                    q.offer(new Pair(d, pair.cmd + "D"));
                }

                //S : +N -1 % N
                int s = (pair.num + N - 1) % N;
                if (!visited[s]){
                    visited[s] = true;
                    q.offer(new Pair(s, pair.cmd + "S"));
                }

                String str = String.valueOf(pair.num);
                while(str.length() < 4) str = "0" + str;
                //L : 왼쪽 회전
                String l = str.substring(1, 4) + str.charAt(0);
                int lnum = Integer.parseInt(l);
                if (!visited[lnum]){
                    visited[lnum] = true;
                    q.offer(new Pair(lnum, pair.cmd + "L"));
                }

                //R : 오른쪽 회전
                String r = str.substring(3, 4) + str.substring(0, 3);
                int rnum = Integer.parseInt(r);
                if (!visited[rnum]){
                    visited[rnum] = true;
                    q.offer(new Pair(rnum, pair.cmd + "R"));
                }
            }
        }
        System.out.println(sb);
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Docs{
        int no;
        int priority;
        public Docs(int no, int priority){
            this.no = no;
            this.priority = priority;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        StringBuilder sb = new StringBuilder();
        while(T-- > 0){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(in.readLine());
            Deque<Docs> q = new ArrayDeque<>();
            for (int i = 0; i < N; i++){
                q.offer(new Docs(i, Integer.parseInt(st.nextToken())));
            }

            int printCnt = 1;
            while(true){
                //출력 시뮬레이션
                Docs first = q.peekFirst();
                boolean isPrint = true;
                for (Docs doc : q){
                    if (first.priority < doc.priority){
                        isPrint = false;
                        break;
                    }
                }

                if (isPrint){
                    //프린트한다
                    q.pollFirst();
                    if (first.no == M){
                        sb.append(printCnt).append("\n");
                        break;
                    }
                    printCnt++;
                }
                else{
                    //뒤로 재배치한다.
                    q.pollFirst();
                    q.offerLast(first);
                }
            }

        }
        System.out.println(sb);
    }
}
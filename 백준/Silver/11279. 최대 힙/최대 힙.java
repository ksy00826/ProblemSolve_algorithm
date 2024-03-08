import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); //내림차순
        StringBuilder sb = new StringBuilder();
        while(N-- > 0){
            int cmd = Integer.parseInt(in.readLine());
            if (cmd == 0){
                sb.append((pq.isEmpty())? 0 : pq.poll()).append("\n");
            }
            else{
                pq.offer(cmd);
            }
        }
        System.out.println(sb);

    }
}
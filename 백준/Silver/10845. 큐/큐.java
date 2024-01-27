import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(in.readLine());
        Deque<Integer> q = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        while(N-- > 0){
            StringTokenizer st = new StringTokenizer(in.readLine());
            String cmd = st.nextToken();

            switch(cmd){
                case "push" :
                    q.offerLast(Integer.parseInt(st.nextToken()));
                    break;
                case "pop" :
                    if (q.isEmpty()) sb.append("-1").append("\n");
                    else sb.append(q.pollFirst()).append("\n");
                    break;
                case "size" :
                    sb.append(q.size()).append("\n");
                    break;
                case "empty" :
                    if (q.isEmpty()) sb.append("1").append("\n");
                    else sb.append("0").append("\n");
                    break;
                case "front" :
                    if (q.isEmpty()) sb.append("-1").append("\n");
                    else sb.append(q.peekFirst()).append("\n");
                    break;
                case "back" :
                    if (q.isEmpty()) sb.append("-1").append("\n");
                    else sb.append(q.peekLast()).append("\n");
                    break;
            }
        }
        System.out.println(sb);
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        Deque<Integer> dec = new ArrayDeque<>();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());

        StringBuilder sb = new StringBuilder();
        while(N-- > 0){
            StringTokenizer st = new StringTokenizer(in.readLine());
            String cmd = st.nextToken();
            switch(cmd){
                case "push_front":
                    dec.offerFirst(Integer.parseInt(st.nextToken()));
                    break;
                case "push_back" :
                    dec.offerLast(Integer.parseInt(st.nextToken()));
                    break;
                case "pop_front" :
                    if (dec.isEmpty()) sb.append(-1);
                    else sb.append(dec.pollFirst());
                    sb.append("\n");
                    break;
                case "pop_back" :
                    if (dec.isEmpty()) sb.append(-1);
                    else sb.append(dec.pollLast());
                    sb.append("\n");
                    break;
                case "size" :
                    sb.append(dec.size()).append("\n");
                    break;
                case "empty" :
                    sb.append((dec.isEmpty())? 1 : 0).append("\n");
                    break;
                case "front" :
                    if (dec.isEmpty()) sb.append(-1);
                    else sb.append(dec.peekFirst());
                    sb.append("\n");
                    break;
                case "back" :
                    if (dec.isEmpty()) sb.append(-1);
                    else sb.append(dec.peekLast());
                    sb.append("\n");
                    break;
            }
        }
        System.out.println(sb);
    }
}
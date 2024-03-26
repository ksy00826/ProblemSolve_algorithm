import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(in.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            int N = Integer.parseInt(in.readLine());

            PriorityQueue<Integer> minQ = new PriorityQueue<>();
            PriorityQueue<Integer> maxQ = new PriorityQueue<>(Collections.reverseOrder());

            Map<Integer, Integer> existMap = new HashMap<>(); //큐에 넣는 숫자마다 총 개수를 저장
            while (N-- > 0) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                String cmd = st.nextToken();
                int value = Integer.parseInt(st.nextToken());

                if (cmd.equals("I")) {
                    //삽입
                    int cnt = existMap.getOrDefault(value, 0);
                    existMap.put(value, cnt+1);
                    maxQ.offer(value);
                    minQ.offer(value);
                } else if (value == 1) {
                    //최댓값 삭제
                    while(!maxQ.isEmpty()){
                        int removeVal = maxQ.poll();
                        if (existMap.containsKey(removeVal)){
                            //삭제된 원소가 아니라면
                            int cnt = existMap.get(removeVal);
                            if (cnt == 1) existMap.remove(removeVal);
                            else existMap.put(removeVal, cnt-1);
                            break;
                        }
                    }
                } else {
                    //최솟값 삭제
                    while(!minQ.isEmpty()){
                        int removeVal = minQ.poll();
                        if (existMap.containsKey(removeVal)){
                            //삭제된 원소가 아니라면
                            int cnt = existMap.get(removeVal);
                            if (cnt == 1) existMap.remove(removeVal);
                            else existMap.put(removeVal, cnt-1);
                            break;
                        }
                    }
                }
            }
            if (existMap.isEmpty()) {
                sb.append("EMPTY").append("\n");
            } else {
                //최댓값 얻기
                while(!maxQ.isEmpty()){
                    int val = maxQ.poll();
                    if (existMap.containsKey(val)){
                        //삭제된 원소가 아니라면
                        sb.append(val).append(" ");
                        break;
                    }
                }
                //최솟값 얻기
                while(!minQ.isEmpty()){
                    int val = minQ.poll();
                    if (existMap.containsKey(val)){
                        //삭제된 원소가 아니라면
                        sb.append(val).append("\n");
                        break;
                    }
                }
            }
        }
        System.out.println(sb);
    }

}
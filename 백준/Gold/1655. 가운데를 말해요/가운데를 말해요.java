import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    //작은 수를 저장하는 PQ - 높은 값이 먼저 나옴
    static Queue<Integer> minQ = new PriorityQueue<>(Collections.reverseOrder());
    //큰 수를 저장하는 PQ
    static Queue<Integer> maxQ = new PriorityQueue<>();
    static int midNum = -10001;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(in.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++){
            int num = Integer.parseInt(in.readLine());

            //Q에 삽입
            if (midNum == -10001 || midNum >= num){
                minQ.add(num);
            }
            else {
                maxQ.add(num);
            }

            //Q 업데이트
            if (i % 2 != 0) {
                while(minQ.size() != maxQ.size()){
                    if (minQ.size() > maxQ.size()){
                        maxQ.add(minQ.poll());
                    }
                    else{
                        minQ.add(maxQ.poll());
                    }
                }
            }
            else{
                while(minQ.size() != maxQ.size() + 1){
                    if (minQ.size() > maxQ.size()){
                        maxQ.add(minQ.poll());
                    }
                    else{
                        minQ.add(maxQ.poll());
                    }
                }
            }

            midNum = minQ.peek();
            sb.append(midNum).append("\n");
        }
        System.out.println(sb);
    }

}
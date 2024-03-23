import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(in.readLine());
        StringBuilder sb = new StringBuilder();
        while(T-- > 0){
            String cmds = in.readLine();
            int N = Integer.parseInt(in.readLine());
            Deque<Integer> nums = new ArrayDeque<>();
            String arrString = in.readLine();
            String[] arr = arrString.substring(1, arrString.length()-1).split(",");
//            System.out.println(Arrays.toString(arr));
            if (N != 0){
                for (int i = 0; i < arr.length; i++){
                    nums.offer(Integer.parseInt(arr[i]));
                }
            }
            boolean isError = false;
            boolean isReverse = false;//정방향 포인터 시작
            for (char cmd : cmds.toCharArray()){
                if (cmd == 'R') isReverse = !isReverse;
                else if (cmd == 'D'){
                    if (nums.isEmpty()){
                        isError = true;
                        break;
                    }
                    if (!isReverse) nums.pollFirst();
                    else nums.pollLast();
                }
            }
            if (isError){
                sb.append("error").append("\n");
            }
            else{
                sb.append("[");
                if (isReverse){
                    while(nums.size() > 1){
                        sb.append(nums.pollLast()).append(",");
                    }
                }
                else{
                    while(nums.size() > 1){
                        sb.append(nums.pollFirst()).append(",");
                    }
                }
                if (!nums.isEmpty()) sb.append(nums.poll());
                sb.append("]").append("\n");
            }
        }
        System.out.println(sb);
    }
}
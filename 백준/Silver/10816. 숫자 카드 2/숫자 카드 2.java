import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int DIFF = 10000000;
        int N = Integer.parseInt(in.readLine());
        int[] nums = new int[10000000 + 1 + DIFF];
        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++){
            nums[Integer.parseInt(st.nextToken()) + DIFF]++;
        }

        int M = Integer.parseInt(in.readLine());
        st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();
        while(M-- > 0){
            sb.append(nums[Integer.parseInt(st.nextToken()) + DIFF]).append(" ");
        }
        System.out.println(sb);
    }
}
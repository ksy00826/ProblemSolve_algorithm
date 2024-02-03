import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int[] nums = new int[N];
        for (int i = 0; i < N; i++){
            nums[i] = Integer.parseInt(in.readLine());
        }
        Arrays.sort(nums);
        StringBuilder sb = new StringBuilder();
        for (int n : nums){
            sb.append(n).append("\n");
        }
        System.out.println(sb);
    }
}
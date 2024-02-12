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

        int diff = (int) Math.round(N * 0.15);
        int sum = 0;
        for (int i = diff; i < N-diff; i++){
            sum += nums[i];
        }
        System.out.println(Math.round(sum / (double)(N-diff*2)));
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        String input = st.nextToken();
        int[] nums = new int[input.length()];
        for (int i = 0; i < nums.length; i++){
            nums[i] = input.charAt(i) - '0';
        }
        N = Integer.parseInt(st.nextToken());

        dfs(nums.clone(), 0, 0);
        System.out.println(maxValue);
    }

    static int maxValue = -1;
    private static void dfs(int[] nums, int target, int cnt) {
        if (cnt == N){
//            System.out.println(Arrays.toString(nums));
            StringBuilder sb = new StringBuilder();
            for (int no : nums){
                sb.append(no);
            }
            int val = Integer.parseInt(sb.toString());
            maxValue = Math.max(maxValue, val);
            return;
        }
        for (int i = target+1; i < nums.length; i++){
            if (target == 0 && nums[i] == 0) {
                dfs(nums.clone(), target+1, cnt); //안바꿈
                continue;
            }

            int[] clone = nums.clone();
            int tmp = clone[i];
            clone[i] = clone[target];
            clone[target] = tmp;
            if (target == nums.length-2){
                dfs(clone.clone(), target, cnt+1); //바꿈
            }
            else{
                dfs(clone.clone(), target+1, cnt+1); //바꿈
                dfs(nums.clone(), target+1, cnt); //안바꿈
            }
        }
    }
}
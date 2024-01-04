import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(in.readLine());
        int[] nums = new int[n];
        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }
        int x = Integer.parseInt(in.readLine());

        //logic
        Arrays.sort(nums);
        int i = 0;
        int j = n-1;
        int cnt = 0;
        while(i < j){
            //1 2 3 5 7 9 10 11 12
            int res = nums[i] + nums[j];
            if (res == x){
                cnt++;
                i++; j--;
            }
            else if (res < x){
                i++;
            }
            else if (res > x) {
                j--;
            }
        }
        System.out.println(cnt);
    }
}
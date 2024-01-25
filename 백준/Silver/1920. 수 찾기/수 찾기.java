import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[] nums;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(in.readLine());
        nums = new int[N];
        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);

        int M = Integer.parseInt(in.readLine());
        st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();
        while(M-- > 0) {
            int num = Integer.parseInt(st.nextToken());
//            System.out.println(num);
            sb.append((binarySearch(0, N-1, num)) ? 1 : 0).append("\n");
        }
//        System.out.println(Arrays.toString(nums));
        System.out.println(sb);
    }

    private static boolean binarySearch(int start, int end, int num) {
//        System.out.println(start + " " + end);
//        System.out.println("$" + nums[start] + " " + nums[end]);

        int mid = (start + end) / 2;
        if (nums[mid] == num){
            return true;
        }
        if (start >= end) return false;
        else if (nums[mid] > num) {
            end = mid;
        }
        else{
            start = mid+1;
        }
        return binarySearch(start, end, num);
    }
}
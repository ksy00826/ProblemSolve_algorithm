import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[] nums;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        nums = new int[N];
        for (int i = 0; i < N; i++){
            nums[i] = Integer.parseInt(in.readLine());
        }

        System.out.println(getAvg());
        System.out.println(getMid());
        System.out.println(getMostFreq());
        System.out.println(getDiff());
    }

    private static int getDiff() {
        return nums[N-1] - nums[0];
    }

    private static int getMostFreq() {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums){
            int cnt = freq.getOrDefault(num, 0);
            freq.put(num, cnt+1);
        }
        int maxCnt = 0;
        List<Integer> ls = new ArrayList<>();
        for (int key : freq.keySet()){
            int cnt = freq.get(key);
            if (maxCnt < cnt){
                ls.clear();
                maxCnt = cnt;
                ls.add(key);
            }
            else if (maxCnt == cnt){
                ls.add(key);
            }
        }
        Collections.sort(ls);
        if (ls.size() == 1) return ls.get(0);
        else return ls.get(1);
    }

    private static int getMid() {
        Arrays.sort(nums);
        return nums[N/2];
    }

    private static int getAvg() {
        double sum = 0;
        for (int i = 0; i < N; i++){
            sum += nums[i];
        }
        return (int)Math.round(sum / N);
    }
}
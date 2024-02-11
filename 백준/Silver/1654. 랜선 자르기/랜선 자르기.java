import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int K, N;
    static int[] lines;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        lines = new int[K];
        for (int i = 0; i < K; i++){
            lines[i] = Integer.parseInt(in.readLine());
        }

        long len = binarySearch(1, Integer.MAX_VALUE);

        System.out.println(len);
    }

    private static long binarySearch(long start, long end) {
        if (start > end) return start-1;
        long mid = (start + end) / 2;

        long cnt = 0;
        for (int i = 0; i < K; i++){
            cnt += (lines[i] / mid);
        }

        if (cnt >= N) start = mid+1;
        else end = mid-1;

        return binarySearch(start, end);
    }
}
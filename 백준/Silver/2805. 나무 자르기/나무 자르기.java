import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] trees;
    static long maxHeight = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        trees = new int[N];
        st = new StringTokenizer(in.readLine());
//        int maxH = 0;
//        int minH = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++){
            trees[i] = Integer.parseInt(st.nextToken());
//            maxH = Math.max(maxH, trees[i]);
//            minH = Math.min(minH, trees[i]);
        }

        binarySearch(0, 1000000000);
        System.out.println(maxHeight);
    }

    private static void binarySearch(long minH, long maxH) {
        if (minH >= maxH) return;

        long mid = (minH + maxH) / 2;
        long tree = getTree(mid);
//        System.out.println(mid + " " + tree);
        if (tree >= M){
//            System.out.println(mid);
            //더 위로 올려도 된다
            maxHeight = Math.max(maxHeight, mid);
            binarySearch(mid+1, maxH);
        }
        else{
            //내려야 한다
            binarySearch(minH, mid);
        }

    }

    private static long getTree(long cutting) {
        long amount = 0;
        for (int tree : trees){
            long cut = tree - cutting;
            if (cut > 0) amount += cut;
        }
        return amount;
    }
}
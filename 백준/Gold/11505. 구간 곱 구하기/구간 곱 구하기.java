import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static int[] arr;
    static long[] tree;
    static long P = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N];
        for (int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(in.readLine());
        }
        initTree();
        fillTree(1, 0, N-1);
//        System.out.println();
//        System.out.println(Arrays.toString(tree));

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M+K; i++){
            st = new StringTokenizer(in.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            switch(cmd){
                case 1: //b번째 수를 c로 바꾼다
                    int changeIdx = Integer.parseInt(st.nextToken())-1;
                    int changeVal = Integer.parseInt(st.nextToken());
                    changeValue(1, 0, N-1, changeIdx, arr[changeIdx], changeVal);
                    break;
                case 2: //b부터 c까지의 곱을 구한다
                    int left = Integer.parseInt(st.nextToken())-1;
                    int right = Integer.parseInt(st.nextToken())-1;
                    long mul = getMul(1, 0, N-1, left, right);
                    sb.append(mul).append("\n");
                    break;
            }
//            System.out.println(Arrays.toString(tree));
        }
        System.out.println(sb);
    }

    private static long getMul(int nodeIdx, int start, int end, int left, int right) {
        if (start > right || end < left) return 1;

        if (left <= start && end <= right) return tree[nodeIdx];

        return (getMul(nodeIdx*2, start, (start+end)/2, left, right)
                * getMul(nodeIdx*2+1, (start+end)/2+1, end, left, right)) % P;
    }

    private static long changeValue(int nodeIdx, int start, int end, int changeIdx, int preVal, int changeVal) {
        if (changeIdx < start || end < changeIdx) return tree[nodeIdx];

        if (start == end) {
            return tree[nodeIdx] = changeVal;
        }

        return tree[nodeIdx] =
                (changeValue(nodeIdx*2, start, (start+end)/2, changeIdx, preVal, changeVal)
                * changeValue(nodeIdx*2+1, (start+end)/2+1, end, changeIdx, preVal, changeVal)) % P;
    }

    private static long fillTree(int nodeIdx, int start, int end) {
        if (start == end) return tree[nodeIdx] = arr[start];

        return tree[nodeIdx] = (
                fillTree(nodeIdx*2, start, (start+end)/2)
                * fillTree(nodeIdx*2+1, (start+end)/2+1, end)
                ) % P;
    }

    private static void initTree() {
        int h = (int) Math.ceil(Math.log(N)/Math.log(2));
        int treeSize = (int) Math.pow(2, h+1);
        tree = new long[treeSize];
    }
}
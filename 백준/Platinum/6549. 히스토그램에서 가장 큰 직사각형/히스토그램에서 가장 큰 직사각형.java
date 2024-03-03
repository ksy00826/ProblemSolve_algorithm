import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /*
    구간에 대해 최대 높이를 저장하고 있다면,
    구간의 길이와 거기서의 최소 높이를 곱한다면, 너비를 구할 수 있지 않을까?

    구간의 길이 1~100000(10만) * log(10만)
    1. 각 구간의 길이마다 모든 곳을 순회한다
    2. 순회한 각각에서는 최댓값의 최솟값을 얻는다
    3. 최솟값 중 최댓값을 구하여 area값의 최댓값을 구한다.
     */
    static int N;
    static int[] arr;
    static int[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        while(true){
            StringTokenizer st = new StringTokenizer(in.readLine());

            N = Integer.parseInt(st.nextToken());
            if (N == 0) break;
            arr = new int[N];
            for (int i = 0; i < N; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }

            initTree();
            fillTree(1, 0, N-1);

            long maxArea = divide(0, N-1);
            sb.append(maxArea).append("\n");
        }
        System.out.println(sb);
    }

    private static long divide(int left, int right) {
        if (left > right) return 0;
        int minHIdx = getMinHIdx(1, 0, N-1, left, right);

        long area = (long) arr[minHIdx] * (right-left+1);
//        System.out.println(left + " " + right + " " + minHIdx + " " + area);
        return Math.max(
                area,
                Math.max(
                        divide(left, minHIdx-1),
                        divide(minHIdx+1, right)
                )
        );
    }

    private static int getMinHIdx(int nodeIdx, int start, int end, int left, int right) {
        if (start > right || end < left) return -1;

        if (left <= start && end <= right) return tree[nodeIdx];

        int idx1 = getMinHIdx(nodeIdx*2, start, (start+end)/2, left, right);
        int idx2 = getMinHIdx(nodeIdx*2+1, (start+end)/2+1, end, left, right);

        if (idx1 != -1 && idx2 != -1)
            return (arr[idx1] < arr[idx2])? idx1 : idx2;
        else if (idx1 == -1) return idx2;
        else return idx1;
    }

    private static int fillTree(int nodeIdx, int start, int end) {
        if (start == end) return tree[nodeIdx] = start;

        int idx1 = fillTree(nodeIdx*2, start, (start+end)/2);
        int idx2 = fillTree(nodeIdx*2+1, (start+end)/2+1, end);

        //구간의 최소 높이를 갖는 idx를 저장
        return tree[nodeIdx] = (arr[idx1] < arr[idx2])? idx1 : idx2;
    }

    private static void initTree() {
        int h = (int) Math.ceil(Math.log(N) / Math.log(2));
        int treeSize = (int) Math.pow(2, h+1);
        tree = new int[treeSize];
    }
}
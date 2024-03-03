import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] arr;
    static int[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        for (int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(in.readLine());
        }

        //세그먼트 트리 생성
        initTree();
        fillTree(1, 0, N-1);

        StringBuilder sb = new StringBuilder();
        while(M-- > 0){
            //구간의 합 구하기
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            int minVal = getMin(1, 0, N-1, a, b);
            sb.append(minVal).append("\n");
        }
        System.out.println(sb);
    }

    private static int getMin(int nodeIdx, int start, int end, int a, int b) {
        if (start > b || end < a) return Integer.MAX_VALUE;

        if (a <= start && end <= b) return tree[nodeIdx];

        return Math.min(
                getMin(nodeIdx*2, start, (start+end)/2, a, b),
                getMin(nodeIdx*2+1, (start+end)/2+1, end, a, b)
        );
    }

    private static int fillTree(int nodeIdx, int start, int end) {
        if (start == end) return tree[nodeIdx] = arr[start];

        return tree[nodeIdx] = Math.min(
                fillTree(nodeIdx*2, start, (start+end)/2),
                fillTree(nodeIdx*2+1, (start+end)/2+1, end)
        );
    }

    private static void initTree() {
        int h = (int) Math.ceil(Math.log(N)/Math.log(2));
        int treeSize = (int) Math.pow(2, h+1);
        tree = new int[treeSize];
    }
}
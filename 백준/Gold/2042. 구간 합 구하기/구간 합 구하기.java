import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static long[] numbers;
    static long[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        numbers = new long[N];
        for (int i = 0; i < N; i++){
            numbers[i] = Long.parseLong(in.readLine());
        }

        initTree();
        fillTree(1, 0, N-1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M+K; i++){
            st = new StringTokenizer(in.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            switch(cmd){
                case 1: //변경
                    int changeIdx = Integer.parseInt(st.nextToken())-1;
                    long changeValue = Long.parseLong(st.nextToken());
                    long diff = changeValue - numbers[changeIdx];
                    numbers[changeIdx] = changeValue;
                    changeTree(1, 0, N-1, changeIdx, diff);
                    break;
                case 2: //구간합
                    int left = Integer.parseInt(st.nextToken())-1;
                    int right = Integer.parseInt(st.nextToken())-1;
                    long sum = getSum(1, 0, N-1, left, right);
                    sb.append(sum).append("\n");
                    break;
            }
        }
        System.out.println(sb);
    }

    private static long getSum(int nodeIdx, int start, int end, int left, int right) {
        if (start > right || end < left) return 0;

        if (left <= start && end <= right) return tree[nodeIdx];

        return getSum(nodeIdx*2, start, (start+end)/2, left, right)
                + getSum(nodeIdx*2+1, (start+end)/2+1, end, left, right);
    }

    private static void changeTree(int nodeIdx, int start, int end, int changeIdx, long diff) {
        //범위에 속하지 않으면 패스
        if (start > changeIdx || changeIdx > end) return;

        //범위에 속하면
        tree[nodeIdx] += diff;
        if (start != end){
            changeTree(nodeIdx*2, start, (start+end)/2, changeIdx, diff);
            changeTree(nodeIdx*2+1, (start+end)/2+1, end, changeIdx, diff);
        }
    }

    private static long fillTree(int nodeIdx, int start, int end) {
        //리프 노드
        if (start == end) return tree[nodeIdx] = numbers[start];

        //그 외 노드
        return tree[nodeIdx] =
                fillTree(nodeIdx*2, start, (start+end)/2)
                + fillTree(nodeIdx*2+1, (start+end)/2+1, end);
    }

    private static void initTree() {
        int h = (int) Math.ceil(Math.log(N) / Math.log(2));
        int treeSize = (int) Math.pow(2, h+1);
        tree = new long[treeSize];
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int[] arr;
    static int[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(in.readLine());
        }

        initTree();
        fillTree(1, 0, N-1); //최소 높이의 인덱스를 저장
        long area = divide(0, N-1); //전체 구간에 대해 분할 정복
        System.out.println(area);
    }

    private static long divide(int left, int right) {
        if (left > right) return 0; //종료조건
//        System.out.println(left + " " + right);

        int minIdx = getMinIdx(1, 0, N-1, left, right);
        if (minIdx == -1) return 0; //-1처리

        long area = (long) arr[minIdx] * (right-left+1);
        return Math.max(
                area,
                Math.max(
                        divide(left, minIdx-1),
                        divide(minIdx+1, right)
                )
        );
    }

    private static int getMinIdx(int nodeIdx, int start, int end, int left, int right) {
        if (left > end || right < start) return -1;

        if (left <= start && end <= right) return tree[nodeIdx];

        int idx1 = getMinIdx(nodeIdx*2, start, (start+end)/2, left, right);
        int idx2 = getMinIdx(nodeIdx*2+1, (start+end)/2+1, end, left, right);
        if (idx1 == -1) return idx2; //-1처리
        else if (idx2 == -1) return idx1;
        return (arr[idx1] < arr[idx2])? idx1 : idx2;
    }

    private static int fillTree(int nodeIdx, int start, int end) {
        if (start == end) return tree[nodeIdx] = start;

        int idx1 = fillTree(nodeIdx*2, start, (start+end)/2);
        int idx2 = fillTree(nodeIdx*2+1, (start+end)/2+1, end);
        return tree[nodeIdx] = (arr[idx1] < arr[idx2])? idx1 : idx2; //트리 대입하는 거 빼먹음
    }

    private static void initTree() {
        int h = (int) Math.ceil(Math.log(N)/Math.log(2));
        int treeSize = (int) Math.pow(2, h+1);
        tree = new int[treeSize];
    }
}
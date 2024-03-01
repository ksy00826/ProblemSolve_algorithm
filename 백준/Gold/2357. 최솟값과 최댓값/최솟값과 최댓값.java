import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] arr;
    static int[] maxTree, minTree;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        for (int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(in.readLine());
        }

        initTree();
        fillMaxTree(1, 0, N-1);
        fillMinTree(1, 0, N-1);

        StringBuilder sb = new StringBuilder();
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            //구간 내의 최댓값과 최솟값
            int max = findMax(1, 0, N-1, a, b);
            int min = findMin(1, 0, N-1, a, b);
            sb.append(min).append(" ").append(max).append("\n");
        }
        System.out.println(sb);
    }

    private static int findMin(int nodeIdx, int start, int end, int left, int right) {
        //구간을 완전히 벗어나면 MAX_VALUE 리턴
        if (start > right || end < left) return Integer.MAX_VALUE;

        //구간 안에 완전히 포함되면 tree값 리턴
        if (left <= start && end <= right) return minTree[nodeIdx];

        //일부 포함되면 자식 노드 확인
        return Math.min(
                findMin(nodeIdx*2, start, (start+end)/2, left, right),
                findMin(nodeIdx*2+1, (start+end)/2+1, end, left, right)
        );
    }

    private static int findMax(int nodeIdx, int start, int end, int left, int right) {
        //구간을 완전히 벗어나면 0 리턴
        if (start > right || end < left) return 0;

        //구간 안에 완전히 포함되면 tree값 리턴
        if (left <= start && end <= right) return maxTree[nodeIdx];

        //일부 포함되면 자식 노드 확인
        return Math.max(
                findMax(nodeIdx*2, start, (start+end)/2, left, right),
                findMax(nodeIdx*2+1, (start+end)/2+1, end, left, right)
        );

    }

    private static int fillMinTree(int nodeIdx, int start, int end) {
        //리프 노드라면 대입 후 리턴
        if (start == end) return minTree[nodeIdx] = arr[start];

        //중간 노드라면 왼쪽과 오른쪽 중 작은 값을 대입 후 리턴
        return minTree[nodeIdx] = Math.min(
                fillMinTree(nodeIdx*2, start, (start+end)/2),
                fillMinTree(nodeIdx*2+1, (start+end)/2+1, end)
        );
    }

    private static int fillMaxTree(int nodeIdx, int start, int end) {
        //리프 노드라면 대입 후 리턴
        if (start == end) return maxTree[nodeIdx] = arr[start];

        //중간 노드라면 왼쪽과 오른쪽 중 큰 값을 대입 후 리턴
        return maxTree[nodeIdx] = Math.max(
                fillMaxTree(nodeIdx*2, start, (start+end)/2),
                fillMaxTree(nodeIdx*2+1, (start+end)/2+1, end)
        );
    }


    private static void initTree() {
        int h = (int) Math.ceil(Math.log(N) / Math.log(2));
        int treeSize = (int) Math.pow(2, h+1);
        maxTree = new int[treeSize];
        minTree = new int[treeSize];
    }
}
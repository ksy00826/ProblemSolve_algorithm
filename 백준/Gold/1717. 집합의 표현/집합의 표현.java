import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N+1]; //0~N
        //우선은 각각 별개의 집합이므로 루트를 자기 자신으로 초기화
        for (int i = 0; i < N+1; i++) {
            parent[i] = i;
        }

        //logic
        StringBuilder sb = new StringBuilder();
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            //집합 합치기
            if (cmd == 0) union(a, b);
            //같은 집합인지 확인
            else {
                boolean isSame = (find(a) == find(b));
                sb.append((isSame)? "YES" : "NO").append("\n");
            }
        }
        System.out.println(sb);
    }

    private static int find(int a) { //루트를 찾아주는 함수
        if (a == parent[a]) return a;

        return parent[a] = find(parent[a]); //루트를 찾으며 루트로 부모를 갱신(트리의 높이 낮추기)
    }

    private static void union(int a, int b) { //집합을 합치는 함수
        int pa = find(a);
        int pb = find(b);

        if (pa != pb){
            //다른 집합인 경우 합치기
            parent[pa] = pb;
        }
    }
}
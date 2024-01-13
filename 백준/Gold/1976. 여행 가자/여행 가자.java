import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(in.readLine()); //도시의 수
        int M = Integer.parseInt(in.readLine()); //여행 계획에 속한 도시의 수

        parent = new int[N]; //0~N-1까지의 도시
        for (int i = 0; i < N; i++){
            parent[i] = i; //초기화. 각각 다른 집합.
        }
        for (int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++){
                int connect = Integer.parseInt(st.nextToken());
                if (connect == 1){
                    //집합을 합쳐준다.
                    union(i, j);
                }
            }
        }

        //여행계획. 모두 같은 집합이어야 함
        boolean isALlSame = true;
        StringTokenizer st = new StringTokenizer(in.readLine());
        int root = find(Integer.parseInt(st.nextToken())-1);
        for (int i = 1; i < M; i++){
            if (root != find(Integer.parseInt(st.nextToken())-1)){
                isALlSame = false;
                break;
            }
        }
        System.out.println((isALlSame)? "YES" : "NO");
    }

    private static int find(int i) {
        if (i == parent[i]) return i;

        return parent[i] = find(parent[i]);
    }

    private static void union(int i, int j) {
        int ip = find(i);
        int jp = find(j);

        if (ip != jp){
            parent[ip] = jp;
        }
    }
}
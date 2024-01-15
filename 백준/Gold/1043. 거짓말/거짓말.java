import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken())+1; //사람의 수
        M = Integer.parseInt(st.nextToken()); //파티의 수

        parent = new int[N];
        for (int i = 0; i < N; i++){
            parent[i] = i;
        }

        //진실을 아는 사람의 부모를 모두 -1로 잡는다
        st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++){
            parent[Integer.parseInt(st.nextToken())] = -1;
        }
        //파티를 돌면서
        //모든 부모를 첫 번째 사람으로 다 바꿈
        //만약 하나라도 -1이면 첫번째 사람의 부모를 -1로 바꿈
        List<List<Integer>> party = new ArrayList<>();
        for (int i = 0; i < M; i++){
            List<Integer> p = new ArrayList<>();

            st = new StringTokenizer(in.readLine());
            n = Integer.parseInt(st.nextToken());
            int first = Integer.parseInt(st.nextToken());
            p.add(first);
            boolean knowFlag = (parent[first] == -1)? true : false;
            for (int j = 0; j < n-1; j++){
                int people = Integer.parseInt(st.nextToken());
                if (parent[people] == -1) knowFlag = true;
//                parent[people] = first;
                union(people, first);
                p.add(people);
            }
            if (knowFlag) union(first, -1);

            party.add(p);
        }

        //모든 사람 다 find 해주고
        for (int i = 0; i < N; i++){
            if (parent[i] != -1) find(i);
        }
        //파티 다시 돌면서 -1 아닌 거 카운트
        int cnt = 0;
        for (List<Integer> p : party){
            boolean truth = false;
            for (int person : p){
                if(parent[person] == -1) {
                    truth = true;
                    break;
                }
            }
            if (!truth) cnt++;
        }

        System.out.println(cnt);

    }

    private static int find(int i) {
        if (i == -1) return -1;
        else if (parent[i] == i) return i;

        return parent[i] = find(parent[i]);
    }

    private static void union(int i, int j){
        int pi = find(i);
        int pj = find(j);

        if (pi != pj){
            if (pi != -1) parent[pi] = pj;
            else if (pj != -1) parent[pj] = pi;
        }
    }
}
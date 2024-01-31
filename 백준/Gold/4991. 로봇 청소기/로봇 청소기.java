import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    /*
    구상
    1. 더러운 칸 하나를 하나의 노드로 보고, 모든 더러운 칸들 사이의 거리를 구한다.
        1.1 여기서 아무 노드도 갈 수 없는 곳이 있으면(거리가 다 무한대) -1을 출력하고 끝낸다.
    2. 순열을 사용해서 더러운 칸들을 줄세운다.
        2.1 줄 다 세우면 그 순서대로 이동하는 데에 걸리는 비용을 계산하여, 최소를 저장한다
     */
    static class Pos{
        int r, c;
        int depth;
        public Pos(int r, int c){
            this.r = r;
            this.c = c;
        }
        public Pos(int r, int c, int depth){
            this.r = r;
            this.c = c;
            this.depth = depth;
        }
    }
    static int H, W;
    static char[][] map;
    static int[][] intMap;
    static Pos start;
    static List<Pos> dirty; //노드들 저장
    static int[] startDist; //시작점에서 노드들까지의 거리 저장
    static int[][] dist; //더러운 노드들 사이의 거리 저장
    static int INF = Integer.MAX_VALUE / 21;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        while(true){
            StringTokenizer st = new StringTokenizer(in.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            if (H == 0 && W == 0) break;

            map = new char[H][W];
            intMap = new int[H][W];
            dirty = new ArrayList<>();
            int idx = 0;
            for (int i = 0; i < H; i++){
                map[i] = in.readLine().toCharArray();
                Arrays.fill(intMap[i], -1);
                for (int j = 0; j < W; j++){
                    if (map[i][j] == 'o') start = new Pos(i, j);
                    else if (map[i][j] == '*') {
                        dirty.add(new Pos(i, j));
                        intMap[i][j] = idx++;
                    }
                }
            }

            //거리 구하기
            //dist 초기화
            startDist = new int[dirty.size()];
            dist = new int[dirty.size()][dirty.size()];
            Arrays.fill(startDist, INF);
            getDist(start, startDist); //start부터 다른 곳까지의 거리
            for (int i = 0; i < dirty.size(); i++){
                Arrays.fill(dist[i], INF);
                dist[i][i] = 0;

                Pos pos = dirty.get(i);
                getDist(pos, dist[i]);
            }

//            System.out.println(Arrays.toString(startDist));

            //순열로 줄세우기
            numbers = new int[dirty.size()];
            used = new boolean[dirty.size()];
            minCost = Integer.MAX_VALUE;
            permutation(dirty.size(), 0); //총 숫자, cnt
            sb.append((minCost >= INF)? -1 : minCost).append("\n");
        }
        System.out.println(sb);
    }

    static int[] numbers;
    static boolean[] used;
    private static void permutation(int size, int cnt) {
        if (cnt == size){
            calculateCost();
            return;
        }
        for (int i = 0; i < size; i++){
            if (used[i]) continue;

            used[i] = true;
            numbers[cnt] = i;
            permutation(size, cnt+1);
            used[i] = false;
        }
    }

    static int minCost;
    private static void calculateCost() {
        int cost = startDist[numbers[0]];
        for (int i = 0; i < numbers.length-1; i++){
            cost += dist[numbers[i]][numbers[i+1]];
        }
        minCost = Math.min(minCost, cost);
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    private static void getDist(Pos start, int[] dist) {
        //bfs를 수행하여 구하기
        Queue<Pos> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[H][W];

        q.offer(start);
        visited[start.r][start.c] = true;

        while(!q.isEmpty()){
            Pos cur = q.poll();

            for (int di = 0; di < 4; di++){
                int nr = cur.r + dr[di];
                int nc = cur.c + dc[di];
                if (nr < 0 || nr >= H || nc < 0 || nc >= W || visited[nr][nc] || map[nr][nc] == 'x') continue;

                visited[nr][nc] = true;
                if (intMap[nr][nc] != -1){
                    //더러운 곳임
                    dist[intMap[nr][nc]] = cur.depth + 1;
                }
                q.offer(new Pos(nr, nc, cur.depth+1));
            }
        }
    }
}
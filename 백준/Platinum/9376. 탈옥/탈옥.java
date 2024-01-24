import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Pos{
        int r, c;
        public Pos(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    static int H, W;
    static char[][] map;
    static int INF = Integer.MAX_VALUE / 3 - 1;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());

        StringBuilder sb = new StringBuilder();
        while(T-- > 0){
            StringTokenizer st = new StringTokenizer(in.readLine());
            H = Integer.parseInt(st.nextToken())+2;
            W = Integer.parseInt(st.nextToken())+2; //바깥쪽에 공간을 상하좌우 한 줄씩 만들어준다.

            //상하좌우 한 줄씩 만들어주면서 map 입력받기
            Pos in1 = null;
            Pos in2 = null;
            map = new char[H][W];
            for (int i = 0; i < H; i++){
                map[i][0] = '.';
                map[i][W-1] = '.';
            }
            for (int j = 0; j < W; j++){
                map[0][j] = '.';
                map[H-1][j] = '.';
            }
            for (int i = 1; i < H-1; i++){
                String str = in.readLine();
                for (int j = 1; j < W-1; j++){
                    map[i][j] = str.charAt(j-1);
                    if (map[i][j] == '$') {
                        if (in1 == null) in1 = new Pos(i, j);
                        else in2 = new Pos(i, j);
                    }
                }
            }
            Pos out = new Pos(0, 0);

//            System.out.println(in1.r + " " + in1.c);
//            System.out.println(in2.r + " " + in2.c);

//            for (int i = 0; i < H; i++){
//                System.out.println(Arrays.toString(map[i]));
//            }
            //세명 모두를 시작으로 zeroOneBfs 를 돌려서 dist 배열에 각각 저장
            int[][][] dist = new int[3][H][W];
            for (int i = 0; i < H; i++){
                Arrays.fill(dist[0][i], INF);
                Arrays.fill(dist[1][i], INF);
                Arrays.fill(dist[2][i], INF);
            }
            dist[0][out.r][out.c] = 0;
            dist[1][in1.r][in1.c] = 0;
            dist[2][in2.r][in2.c] = 0;
            zeroOneBfs(dist[0], out);
            zeroOneBfs(dist[1], in1);
            zeroOneBfs(dist[2], in2);


//            for (int i = 0; i < H; i++){
//                System.out.println(Arrays.toString(dist[0][i]));
//            }
//            System.out.println();
//            for (int i = 0; i < H; i++){
//                System.out.println(Arrays.toString(dist[1][i]));
//            }
//            System.out.println();
//            for (int i = 0; i < H; i++){
//                System.out.println(Arrays.toString(dist[2][i]));
//            }
//            System.out.println();

            int minValue = Integer.MAX_VALUE;
            for (int i = 0; i < H; i++){
                for (int j = 0; j < W; j++){
                    int val = dist[0][i][j] + dist[1][i][j] + dist[2][i][j] - ((map[i][j] == '#')? 2 : 0);
                    if (minValue > val) {
                        minValue = val;
                    }
                }
            }
            sb.append(minValue).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void zeroOneBfs(int[][] dist, Pos start) {
        //dec, dist
        Deque<Pos> dec = new ArrayDeque<>();
        dec.offer(start);

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while(!dec.isEmpty()){
            Pos cur = dec.pollFirst();

            for (int di = 0; di < 4; di++){
                int nr = cur.r + dr[di];
                int nc = cur.c + dc[di];
                if (nr < 0 || nr >= H || nc < 0 || nc >= W || map[nr][nc] == '*') continue;

                int cost = (map[nr][nc] == '#') ? 1 : 0;
                if (dist[nr][nc] > dist[cur.r][cur.c] + cost){
                    dist[nr][nc] = dist[cur.r][cur.c] + cost;
                    if (cost == 0) dec.addFirst(new Pos(nr, nc));
                    else dec.addLast(new Pos(nr, nc));
                }
            }
        }
    }

}
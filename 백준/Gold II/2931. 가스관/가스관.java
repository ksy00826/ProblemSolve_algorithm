import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static char[][] map;
    static int R, C;
    static int[] dr = {-1, 0, 1, 0}; // 상좌하우
    static int[] dc = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];

        int mR = 0, mC = 0, zR = 0, zC = 0;

        for (int i = 0; i < R; i++) {
            map[i] = in.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'M') {
                    mR = i;
                    mC = j;
                } else if (map[i][j] == 'Z') {
                    zR = i;
                    zC = j;
                }
            }
        }

//        Pos mid = move(new Pos(mR, mC));

        char block = 'x';
        for (int i = 0; i < R; i++){
            for (int j = 0; j < C; j++){
                if (map[i][j] == '.'){
                    block = getBlock(new Pos(i, j));
                    if (block != 'x'){
                        System.out.println((i + 1) + " " + (j + 1) + " " + block);
                        return;
                    }
                }
            }
        }

    }

    private static Pos move(Pos start) {
        int curR = start.r;
        int curC = start.c;
        int[][] visited = new int[R][C];
        visited[curR][curC]++;

        boolean isConnect = true;
        int preDir = -1;

        while (isConnect) {
            isConnect = false;
            boolean[] dirs = getDirs(map[curR][curC], preDir);
            int saveR = curR;
            int saveC = curC;

            for (int di = 0; di < 4; di++) {
                if (!dirs[di]) continue;

                int nr = curR + dr[di];
                int nc = curC + dc[di];

                if (nr < 0 || nr >= R || nc < 0 || nc >= C || visited[nr][nc] == 1 || (map[nr][nc] == '+' && visited[nr][nc] == 2))
                    continue;

                if (saveR == curR && saveC == curC){
                    saveR = nr;
                    saveC = nc;
                }

                if (isConnected(map[nr][nc], di)) {
                    visited[nr][nc]++;
                    isConnect = true;
                    curR = nr;
                    curC = nc;
                    preDir = di;
                    break;
                }
            }

            if (!isConnect) {
                curR = saveR;
                curC = saveC;
            }
        }

        return new Pos(curR, curC);
    }

    private static boolean[] getDirs(char block, int preDir) {
        boolean[] dirs = new boolean[4];

        switch (block) {
            case '|':
                dirs[0] = true;
                dirs[2] = true;
                break;
            case '-':
                dirs[1] = true;
                dirs[3] = true;
                break;
            case '+':
                if (preDir == 0 || preDir == 2) {
                    dirs[0] = true;
                    dirs[2] = true;
                } else {
                    dirs[1] = true;
                    dirs[3] = true;
                }
                break;
            case '1':
                dirs[2] = true;
                dirs[3] = true;
                break;
            case '2':
                dirs[0] = true;
                dirs[3] = true;
                break;
            case '3':
                dirs[0] = true;
                dirs[1] = true;
                break;
            case '4':
                dirs[1] = true;
                dirs[2] = true;
                break;
            case 'M':
            case 'Z':
                dirs[0] = true;
                dirs[1] = true;
                dirs[2] = true;
                dirs[3] = true;
                break;
        }
        return dirs;
    }

    private static boolean isConnected(char block2, int dir) {
        boolean[] dir2 = getDirs(block2, dir);
        return dir2[(dir + 2) % 4];
    }

    private static char getBlock(Pos mid) {
        boolean[] dir = new boolean[4];
        for (int di = 0; di < 4; di++) {
            int nr = mid.r + dr[di];
            int nc = mid.c + dc[di];
            if (nr < 0 || nr >= R || nc < 0 || nc >= C || map[nr][nc] == '.') continue;
            if (map[nr][nc] != 'M' && map[nr][nc] != 'Z' && isConnected(map[nr][nc], di)) dir[di] = true;
        }

        char block = 'x';
        if (dir[0] && !dir[1] && dir[2] && !dir[3]) block = '|';
        else if (!dir[0] && dir[1] && !dir[2] && dir[3]) block = '-';
        else if (dir[0] && dir[1] && !dir[2] && !dir[3]) block = '3';
        else if (!dir[0] && !dir[1] && dir[2] && dir[3]) block = '1';
        else if (dir[0] && !dir[1] && !dir[2] && dir[3]) block = '2';
        else if (!dir[0] && dir[1] && dir[2] && !dir[3]) block = '4';
        else if (dir[0] && dir[1] && dir[2] && dir[3]) block = '+';
        return block;
    }
}
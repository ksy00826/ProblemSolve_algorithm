import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //4등분하는 함수(r1, c1, r2, c2)
        divider(0, 0, N, N);
        System.out.println(whiteCnt);
        System.out.println(blueCnt);
    }

    static int whiteCnt = 0;
    static int blueCnt = 0;
    private static void divider(int r1, int c1, int r2, int c2) {
        //다 같은 색이면 카운트, 리턴
        int color = map[r1][c1];
        boolean isSame = true;
        for (int i = r1; i < r2; i++){
            for (int j = c1; j < c2; j++){
                if (color != map[i][j]){
                    isSame = false;
                    break;
                }
            }
            if (!isSame) break;
        }

        if (isSame){
            if (color == 0) whiteCnt++;
            else blueCnt++;
        }
        else{
            //다른 색이 있으면 4분할
            int midR = (r1 + r2) / 2;
            int midC = (c1 + c2) / 2;
            divider(r1, c1, midR, midC);
            divider(r1, midC, midR, c2);
            divider(midR, c1, r2, midC);
            divider(midR, midC, r2, c2);
        }

    }

}
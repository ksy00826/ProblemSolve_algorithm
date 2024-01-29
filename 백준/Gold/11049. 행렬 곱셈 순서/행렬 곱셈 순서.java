import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static class Matrix{
        int r, c;
        int cost;
        public Matrix(int r, int c, int cost){
            this.r = r;
            this.c = c;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());

        Matrix[] matrix = new Matrix[N];
        for (int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            matrix[i] = new Matrix(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
        }
        Matrix[][] dp = new Matrix[N][N];
        for (int j = 0; j < N; j++){
            for (int i = j; i >= 0; i--){
                if (i == j){ //대각선엔 자기 자신으로 채워주고 끝내기
                    dp[i][j] = new Matrix(matrix[i].r, matrix[i].c, 0);
                    continue;
                }
                //다른 곳에서는 계산! : i와 j사이의 행렬 곱 연산의 합의 최솟값
                dp[i][j] = new Matrix(-1, -1, Integer.MAX_VALUE); //임의의 값으로 초기화
                for (int k = i; k < j; k++){ //i~j-1
                    Matrix newMat = multiple(dp[i][k], dp[k+1][j]);
                    newMat.cost += dp[i][k].cost + dp[k+1][j].cost;
                    if (dp[i][j].cost > newMat.cost){
                        dp[i][j] = newMat;
                    }
                }
            }
        }
        System.out.println(dp[0][N-1].cost);
    }

    private static Matrix multiple(Matrix mat1, Matrix mat2) {
        return new Matrix(mat1.r, mat2.c, mat1.r * mat1.c * mat2.c);

    }
}
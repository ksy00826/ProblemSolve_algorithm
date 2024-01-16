import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] A;
    static long B;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());
        A = new int[N][N];
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] res = power();
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                System.out.print(res[i][j] + " ");
            }
            System.out.println();
        }

    }

    private static int[][] power() {
        int[][] result = new int[N][N];
        for (int i = 0; i < N; i++){
            result[i][i] = 1;
        }

        while(B >  0){
            if (B % 2 == 1){
                result = multiple(result, A);
            }
            A = multiple(A, A);
            B /= 2;
        }
        return result;
    }

    private static int[][] multiple(int[][] a, int[][] b) {
        int[][] arr = new int[N][N];
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                for (int k = 0; k < N; k++){
                    arr[i][j] += (a[i][k] * b[k][j]);
                }
                arr[i][j] %= 1000;
            }
        }
        return arr;
    }
}
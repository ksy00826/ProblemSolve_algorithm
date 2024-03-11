import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.StringTokenizer;

public class Main {
    static int N = 1501;
    static boolean[][] used = new boolean[N][N];
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        if ((A + B + C) % 3 != 0) System.out.println(0);
        else System.out.println((move(A, B, C))? 1 : 0);
    }

    private static boolean move(int a, int b, int c) {
//        System.out.println(a + " " + b + " " + c);
        if (a == b && b == c) return true;

        boolean isOk = false;
        if (a != b){
            if (a > b && notVisited(a-b, b+b, c)){
                used[a-b][b+b] = true;
                isOk = move(a-b, b+b, c);
            }
            else if (a < b && notVisited(a+a,b-a,c)){
                used[a+a][b-a] = true;
                isOk = move(a+a, b-a, c);
            }
        }
        if (isOk) return isOk;

        if (a != c){
            if (a > c && notVisited(a-c,b,c+c)){
                used[a-c][b] = true;
                isOk = move(a-c, b, c+c);
            }
            else if (a < c && notVisited(a+a, b, c-a)){
                used[a+a][b] = true;
                isOk = move(a+a, b, c-a);
            }
        }
        if (isOk) return isOk;

        if (b != c){
            if (b > c && notVisited(a, b-c, c+c)){
                used[a][b-c] = true;
                isOk = move(a, b-c, c+c);
            }
            else if (b < c && notVisited(a, b+b, c-b)){
                used[a][b+b] = true;
                isOk = move(a, b+b, c-b);
            }
        }
        return isOk;
    }

    private static boolean notVisited(int a, int b, int c) {
        return !used[a][b] && !used[a][c] &&!used[b][a] &&!used[b][c] &&!used[c][a] &&!used[c][b];
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while(true){
            StringTokenizer st = new StringTokenizer(in.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (a == 0 && b == 0 && c == 0) break;

            boolean isRight = false;
            if (a < c && b < c) isRight = isRight(a, b, c);
            else if (a < b && c < b) isRight = isRight(a, c, b);
            else if (b < a && c < a) isRight = isRight(b, c, a);

            sb.append((isRight)? "right" : "wrong").append("\n");
        }
        System.out.println(sb);
    }

    private static boolean isRight(int a, int b, int c) {
        if (a * a + b * b == c * c) return true;
        return false;
    }
}
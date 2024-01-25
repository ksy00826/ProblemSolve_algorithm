import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N, M;
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(in.readLine());
        int[] cards = new int[N];
        for (int i = 0; i < N; i++){
            cards[i] = Integer.parseInt(st.nextToken());
        }

        int maxVal = 0;
        for (int i = 0; i < N; i++){
            for (int j = i+1; j < N; j++){
                for (int k = j+1; k < N; k++){
                    int val = cards[i] + cards[j] + cards[k];
                    if (val <= M){
                        maxVal = Math.max(val, maxVal);
                    }
                }
            }
        }

        System.out.println(maxVal);
    }
}
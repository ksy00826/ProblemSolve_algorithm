import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int[] numbers = new int[N];
        for (int i = 0; i < N; i++){
            numbers[i] = Integer.parseInt(in.readLine());
        }

        Arrays.sort(numbers);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++){
            sb.append(numbers[i]).append("\n");
        }
        System.out.println(sb);
    }
}
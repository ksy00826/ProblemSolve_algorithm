import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int fiveCnt = 0;
        for (int i = 1; i < N+1; i++){
            int num = i;
            while(num % 5 == 0){
                num /= 5;
                fiveCnt++;
            }
        }
        System.out.println(fiveCnt);
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        while(true){
            String num = in.readLine();
            if (num.equals("0")) break;

            int n = num.length();
            boolean isNotEqual = false;
            for (int i = 0; i < n/2+1; i++){
                if (num.charAt(i) != num.charAt(n - 1 - i)){
                    isNotEqual = true;
                    break;
                }
            }

            sb.append((isNotEqual)? "no" : "yes").append("\n");
        }
        System.out.println(sb);
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());

        int number = 666;
        int no = 1;
        while(true){
            if (no == N) break;

            number++;
            String str = String.valueOf(number);
            for (int i = 0; i < str.length()-2; i++){
                if (str.charAt(i) == '6' && str.charAt(i+1) == '6' && str.charAt(i+2) == '6'){
                    no++;
                    break;
                }
            }
        }
        System.out.println(number);
    }
}
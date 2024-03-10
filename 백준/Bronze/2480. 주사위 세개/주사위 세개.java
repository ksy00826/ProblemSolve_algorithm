import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int num1 = Integer.parseInt(st.nextToken());
        int num2 = Integer.parseInt(st.nextToken());
        int num3 = Integer.parseInt(st.nextToken());

        if (num1 == num2 && num2 == num3){
            System.out.println(10000+num1*1000);
        }
        else if (num1 == num2){
            System.out.println(1000+num1*100);
        }
        else if (num1 == num3){
            System.out.println(1000+num1*100);
        }
        else if (num2 == num3){
            System.out.println(1000+num2*100);
        }
        else{
            System.out.println(Math.max(num1, Math.max(num2, num3))*100);
        }
    }
}
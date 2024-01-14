import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int hour = Integer.parseInt(st.nextToken());
        int minu = Integer.parseInt(st.nextToken());

        int plus = Integer.parseInt(in.readLine());
        minu += plus;
        int plusH = minu / 60;
        int plusM = minu % 60;

        hour += plusH;
        minu = plusM;
        hour %= 24;
        System.out.println(hour + " " + minu);
    }
}
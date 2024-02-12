import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static class Person{
        int w, h;
        public Person(int w, int h){
            this.w = w;
            this.h = h;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(in.readLine());
        Person[] people = new Person[N];
        for (int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            people[i] = new Person(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++){
            int cnt = 0;
            for (int j = 0; j < N; j++){
                if (i == j) continue;
                if (people[i].h < people[j].h && people[i].w < people[j].w) cnt++;
            }
            sb.append(cnt+1).append(" ");
        }
        System.out.println(sb);
    }
}
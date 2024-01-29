import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static class Pos{
        int r, c;
        public Pos(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        Pos[] pos = new Pos[N];
        for (int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            pos[i] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(pos, new Comparator<Pos>() {
            @Override
            public int compare(Pos o1, Pos o2) {
                if (o1.r != o2.r) return o1.r - o2.r;
                else return o1.c - o2.c;
            }
        });
        for (Pos p : pos){
            System.out.println(p.r + " " + p.c);
        }
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static class Class{
        int start;
        int end;
        public Class(int start, int end){
            this.start = start;
            this.end = end;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        Class[] classes = new Class[N];
        for (int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            classes[i] = new Class(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(classes, new Comparator<Class>() {
            @Override
            public int compare(Class o1, Class o2) {
                if (o1.end != o2.end){
                    return o1.end - o2.end;
                }
//                else if ((o1.end-o1.start) != (o2.end-o2.start)){
//                    return (o1.end-o1.start) - (o2.end-o2.start);
//                }
                else{
                    return o1.start - o2.start;
                }
            }
        });


        int cnt = 0;
        int endTime = 0;
        for (Class item : classes){
//            System.out.println(item.start + " " + item.end);
            if (endTime <= item.start){
//                System.out.println("push");
                cnt++;
                endTime = item.end;
            }
        }
        System.out.println(cnt);
    }
}
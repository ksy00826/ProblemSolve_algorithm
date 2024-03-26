import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(in.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            int N = Integer.parseInt(in.readLine());
            TreeMap<Integer, Integer> treeMap = new TreeMap<>();
            while (N-- > 0) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                String cmd = st.nextToken();
                int value = Integer.parseInt(st.nextToken());

                if (cmd.equals("I")) {
                    //삽입
                    treeMap.put(value, treeMap.getOrDefault(value, 0) + 1);
                } else {
                    if (treeMap.isEmpty()) continue;

                    int removeVal = (value == 1)? treeMap.lastKey() : treeMap.firstKey();
                    int cnt = treeMap.get(removeVal);

                    if (cnt == 1) treeMap.remove(removeVal); //이제 없어지면 삭제
                    else treeMap.put(removeVal, cnt-1);
                }
//                System.out.println(treeMap);
            }
            sb.append((treeMap.isEmpty())? "EMPTY\n" : treeMap.lastKey() + " " + treeMap.firstKey() + "\n");
        }
        System.out.println(sb);
    }

}
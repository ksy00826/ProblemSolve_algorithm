import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        Set<String> words = new HashSet<>();
        while(N-- > 0){
            words.add(in.readLine());
        }

        List<String> ans = new ArrayList<>();
        for (String word : words){
            ans.add(word);
        }
        Collections.sort(ans, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() != o2.length()) return o1.length() - o2.length();
                else return o1.compareTo(o2);
            }
        });
        for (String str : ans){
            System.out.println(str);
        }
    }
}
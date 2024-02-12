import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        int minValue = 256;
        int maxValue = 0;
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                minValue = Math.min(minValue, map[i][j]);
                maxValue = Math.max(maxValue, map[i][j]);
            }
        }

        int minCost = Integer.MAX_VALUE;
        int maxH = 0;
        for (int num = minValue; num <= maxValue; num++){
            int cost = 0;
            int b = B;
            for (int i = 0; i < N; i++){
                for (int j = 0; j < M; j++){
                    if (num < map[i][j]){ //빼야 함
                        b += (map[i][j] - num);
                        cost += (map[i][j] - num)*2;
                    }
                }
            }

            boolean pass = false;
            for (int i = 0; i < N; i++){
                if (pass) break;
                for (int j = 0; j < M; j++){
                    if (num > map[i][j]){ //쌓아야 함
                        b -= (num - map[i][j]);
                        if (b < 0){
                            pass = true;
                            break;
                        }
                        cost += (num - map[i][j]);
                    }
                }
            }
            if (pass) continue;
            if (minCost >= cost){
                minCost = cost;
                maxH = num;
            }
        }
        System.out.println(minCost + " " + maxH);
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    static ArrayList<Integer> nums;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(in.readLine());
        nums = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++){
            int number = Integer.parseInt(in.readLine());
            insertionSort(number, i);
            sb.append(nums.get((i == 0)? 0 : i / 2)).append("\n");
        }
        System.out.println(sb);
    }

    private static void insertionSort(int number, int i) {
        //1. 이분탐색
        int idx = binarySearch(number, 0, i);
//        System.out.println(idx);

        //2. 해당 위치에 삽입
        nums.add(idx, number);
//        System.out.println(nums);
    }

    private static int binarySearch(int number, int start, int end){
//        System.out.println(start + " " + end);
        if (start >= end) return end;

        int mid = (start + end) / 2;
        if (number <= nums.get(mid)){
            end = mid;
        }
        else{
            start = mid+1; //+1을 뺴먹으면 무한루프
        }
        return binarySearch(number, start, end);
    }
}
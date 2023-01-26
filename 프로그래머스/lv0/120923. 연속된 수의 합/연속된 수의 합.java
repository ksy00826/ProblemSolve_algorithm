class Solution {
    public int[] solution(int num, int total) {
        int[] answer = new int[num];
        if (num % 2 == 0){
            int idx = 0;
            int mid = total / num;
            int leftN = num / 2;
            for (int i = leftN - 1; i > 0; i--){
                answer[idx++] = mid - i;
            }
            for (int i = 0; i <= leftN; i++){
                answer[idx++] = mid + i;
            }
        }
        else{
            int idx = 0;
            int mid = total / num;
            int leftN = (num - 1) / 2;
            for (int i = leftN; i > 0; i--){
                answer[idx++] = mid - i;
            }
            
            for (int i = 0; i <= leftN; i++){
                answer[idx++] = mid + i;
            }
        }
        return answer;
    }
}
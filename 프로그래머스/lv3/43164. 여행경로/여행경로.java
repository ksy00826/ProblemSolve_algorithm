import java.util.*;

class Solution {
    static List<String[]> resultLs = new ArrayList<>();
    static String[] result;
    static boolean[] visited;
    static int N;
    
    public String[] solution(String[][] tickets) {
        String[] answer = {};
        
        Arrays.sort(tickets, new Comparator<String[]>(){
            @Override
            public int compare(String[] arr1, String[] arr2){
                return arr1[1].compareTo(arr2[1]);
            }
        });
        
        //init
        N = tickets.length;
        visited = new boolean[N];
        result = new String[2*N];
        result[0] = "ICN";
        dfs(1, "ICN", tickets);
        
        List<String> res = new ArrayList<>();
        for (String str : resultLs.get(0)){
            if (str == null) break;
            res.add(str);
        }
        return res.toArray(new String[0]);
    }
    
    static public void dfs(int cnt, String cur, String[][] tickets){
        if (cnt == N+1){
            //이용권 다 씀
            // System.out.println(Arrays.toString(result));
            resultLs.add(result.clone());
        }
        
        for (int i = 0; i < N; i++){
            if (!visited[i] && cur.equals(tickets[i][0])){
                result[cnt] = tickets[i][1];
                
                visited[i] = true;
                dfs(cnt+1, tickets[i][1], tickets);
                visited[i] = false;
            }
        }
    }
}
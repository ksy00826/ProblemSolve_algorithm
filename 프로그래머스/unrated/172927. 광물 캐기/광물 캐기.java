class Solution {
    
    static int gocN;
    static int mineN;
    static int res;
    
    static String[] mineral;
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        
        mineN = minerals.length;
        
        mineral = minerals;
        //픽을 순열.. 로 돌려서 광물을 5개씩 캐면서 값을 더해줌
        
        perm(picks[0], picks[1], picks[2], 0, 0); //  피로도, idx
        
        System.out.println(minValue);
        return minValue;
    }
    static int minValue = Integer.MAX_VALUE;
    
    static public void perm(int dia, int iron, int stone, int bad, int idx){
        if (idx >= mineN || (dia == 0 && iron == 0 && stone == 0)){
            //다 고름
            minValue = Math.min(minValue, bad);
            // System.out.println("??");
            return;
        }
        
        if (dia > 0){
            int newBad = bad;
            for (int i = idx; i < Math.min(idx+5, mineN); i++){
                newBad++;
            }
            perm(dia-1, iron, stone, newBad, idx+5);
        }
        if (iron > 0){
            int newBad = bad;
            for (int i = idx; i < Math.min(idx+5, mineN); i++){
                switch(mineral[i]){
                    case "diamond":
                        newBad += 5; break;
                    case "iron":
                        newBad += 1; break;
                    case "stone":
                        newBad += 1; break;
                }
            }
            perm(dia, iron-1, stone, newBad, idx+5);
        }
        if (stone > 0){
            int newBad = bad;
            for (int i = idx; i < Math.min(idx+5, mineN); i++){
                switch(mineral[i]){
                    case "diamond":
                        newBad += 25; break;
                    case "iron":
                        newBad += 5; break;
                    case "stone":
                        newBad += 1; break;
                }
            }
            perm(dia, iron, stone-1, newBad, idx+5);
            
        }
    }
}
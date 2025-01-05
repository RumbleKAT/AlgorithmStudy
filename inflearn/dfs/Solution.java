import java.util.*;

class Solution {
    static int [] arr;
    static boolean [] visited;
    static int slevel;
    static int size;
    static int [][] cCans;
    static int min;

	public int solution(int[][] cans){
		int answer = 0;
        min = Integer.MAX_VALUE;
        cCans = cans;
        arr = new int[cans.length+1];
        visited = new boolean [cans.length+1];
		slevel = cans.length/2+1;
        size = cans.length;

        dfs(0, 1);
        answer = min;

		return answer;
	}
    public void dfs(int idx, int level){
        if(level == slevel){
            int wSum = 0;
            int bSum = 0;
            ArrayList<Integer> A = new ArrayList<>();
            ArrayList<Integer> B = new ArrayList<>();
            for(int i=0;i<size;i++){
                if(visited[i]){
                    A.add(i);
                }else{
                    B.add(i);
                }
            }
            // pick up the 3 cans
            for(int i=0;i<cCans.length/2;i++){
                wSum += cCans[A.get(i)][0];
                bSum += cCans[B.get(i)][1];
            }
            min= Math.min(Math.abs(wSum-bSum),min);
            return;
        }

        for(int i=0;i<size;i++){
            if(!visited[i]){
                visited[i] = true;
                arr[level] = i;
                dfs(i+1,level+1);
                visited[i] = false;
            }
        }
    }
	
	public static void main(String[] args){
		Solution T = new Solution();
		System.out.println(T.solution(new int[][]{{87, 84}, {66, 78}, {94, 94}, {93, 87}, {72, 92}, {78, 63}}));
		System.out.println(T.solution(new int[][]{{10, 20}, {15, 25}, {35, 23}, {55, 20}}));
		System.out.println(T.solution(new int[][]{{11, 27}, {16, 21}, {35, 21}, {52, 21}, {25, 33},{25, 32}, {37, 59}, {33, 47}}));
	}
}

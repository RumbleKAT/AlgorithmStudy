import java.util.*;
class Solution {

    static int [][] map;
    static boolean [] visited;
    static int cnt;
    static int [] result;
    static Stack<Integer> pm;

	public int solution(int[][] fight){
		int answer = 0;
        map = new int[8][8];
        visited = new boolean[8];
        result = new int[8];
        pm = new Stack<>();
        cnt = 0;
        for(int i=0;i<fight.length;i++){
            int x = fight[i][0];
            int y = fight[i][1];
            map[x][y] = 1;
            map[y][x] = 1;
        }

        dfs(1,1);
        answer = cnt;
		return answer;
	}

    public static void dfs(int idx, int depth){
        if(depth == 8){
            cnt += 1;
            return; 
        }
        for(int i=1;i<=7;i++){
            if(!pm.isEmpty() && map[pm.peek()][i] == 1) continue;
            if(!visited[i]){
                visited[i] = true;
                result[depth] = i;
                pm.push(i);
                dfs(i, depth +1);
                visited[i] = false;
                pm.pop();
            }
        }

    }

		
	public static void main(String[] args){
		Solution T = new Solution();
		System.out.println(T.solution(new int[][]{{1, 3}, {5, 7}, {4, 2}}));
		System.out.println(T.solution(new int[][]{{3, 2}, {3, 5}, {5, 2}, {7, 3}}));
		System.out.println(T.solution(new int[][]{{1, 2}, {1, 5}, {1, 7}, {1, 3}}));
		System.out.println(T.solution(new int[][]{{1, 7}}));
		System.out.println(T.solution(new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}}));
	}
}

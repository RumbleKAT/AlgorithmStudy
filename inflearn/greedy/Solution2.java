import java.util.*;
class Solution2 {
	public int solution(int n, int[] nums){
        int answer =0;
        int [][] line = new int [nums.length + 1][2];
        for(int i=0;i<=n;i++){
            line[i][0] = Math.max(0, i-nums[i]);
            line[i][1] = Math.min(n,i+nums[i]);
        }
        Arrays.sort(line, (a,b) -> a[0] - b[0]);
        int start =0;
        int end = 0;
        int i= 0;
        while(i< line.length){
            while(i < line.length && line[i][0] <= start){
                end = Math.max(end, line[i][1]);
                i++;
            }
            answer++;
            if(end == n) return answer;
            if(start == end) return -1;
            start = end;
        }
        return answer;
	}

	public static void main(String[] args){
		Solution2 T = new Solution2();
		System.out.println(T.solution(8, new int[]{1, 1, 1, 2, 1, 1, 2, 1, 1}));
		System.out.println(T.solution(4, new int[]{1, 2, 2, 0, 0}));		
		System.out.println(T.solution(5, new int[]{2, 0, 0, 0, 0, 2}));
		System.out.println(T.solution(11, new int[]{1, 2, 3, 1, 2, 1, 1, 2, 1, 1, 1, 1}));
	}
}
class Node implements Comparable<Node>{
    public int start;
    public int end;

    Node(int start, int end){
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Node o) {
        return this.start - o.start;
    }    
}
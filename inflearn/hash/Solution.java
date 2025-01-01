import java.util.*;
class Solution {
    public int solution(int[] nums, int m){
		int answer = 0;
		int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // sum = m
        for(int i= 0;i<nums.length;i++){
            sum += nums[i];
            if(map.containsKey(sum-m)) answer += map.get(sum-m);
            map.put(sum, map.getOrDefault(sum, 0)+1);
        }
        // sum - m / m;

		return answer;
	}

	public static void main(String[] args){
		Solution T = new Solution();
		System.out.println(T.solution(new int[]{2, 2, 3, -1, -1, -1, 3, 1, 1}, 5));	
		System.out.println(T.solution(new int[]{1, 2, 3, -3, 1, 2, 2, -3}, 5));	
		System.out.println(T.solution(new int[]{1, 2, 3, -3, 1, 2}, 3));	
		System.out.println(T.solution(new int[]{-1, 0, 1}, 0));	
		System.out.println(T.solution(new int[]{-1, -1, -1, 1}, 0));	
	}
}
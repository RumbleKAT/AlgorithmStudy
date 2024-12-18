import java.util.*;
class Main {
	public int[] solution(int[] nums){
		int[] answer = new int[nums.length / 2];

        HashMap<Integer, Integer> map = new HashMap<>();
        Arrays.sort(nums);

        for(int i: nums){
            map.put(i, map.getOrDefault(i, 0)+1);
        }
        int idx = 0;
        for(int i: nums){
            if(map.get(i) != null && map.get(i) > 0){
                map.put(i, map.get(i*1)-1);
                if(map.get(i) >= 0){
                    answer[idx] = i;
                    idx++;
                }
            }
            if(map.get(i*2) != null && map.get(i*2) > 0){
                map.put(i*2, map.get(i*2)-1);
            }
        }
    
		return answer;
	}

	public static void main(String[] args){
		Main T = new Main();
		System.out.println(Arrays.toString(T.solution(new int[]{1, 10, 2, 3, 5, 6})));
		System.out.println(Arrays.toString(T.solution(new int[]{1, 1, 6, 2, 2, 7, 3, 14})));
		System.out.println(Arrays.toString(T.solution(new int[]{14, 4, 2, 6, 3, 10, 10, 5, 5, 7, 7, 14})));
	}
}
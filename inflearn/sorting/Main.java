import java.util.*;
class Main {
	public int solution(int[] nums, int k){
		int answer = 0;
	
        int [] arrDesc = Arrays.stream(nums)
                            .boxed()
                            .sorted(Collections.reverseOrder())
                            .mapToInt(Integer::intValue)
                            .toArray();
        PriorityQueue<Integer> diff =  new PriorityQueue<Integer>(Collections.reverseOrder());
        int sum = 0;
        for(int i= 0;i<arrDesc.length;i+=2){
            diff.add(arrDesc[i] - arrDesc[i+1]);
            sum += arrDesc[i+1];
        }

        for(int i=0;i<k;i++){
            int cur = diff.poll();
            sum += cur;
        }
        answer = sum;
		return answer;
	}

	public static void main(String[] args){
		Main T = new Main();
		System.out.println(T.solution(new int[]{7, 8, 5, 12, 3, 1, 3, 1, 1, 12}, 2));
		System.out.println(T.solution(new int[]{8, 2, 12, 12, 12, 12, 2, 2}, 2));
		System.out.println(T.solution(new int[]{3, 7, 12, 3, 3, 5, 7, 8, 9, 11, 23, 4, 6, 7}, 3));
		System.out.println(T.solution(new int[]{12, 34, 56, 23, 22, 34, 55, 45, 24, 23, 45, 55, 55, 23, 11, 12, 23, 12}, 3));
		System.out.println(T.solution(new int[]{14, 15, 20, 11, 10, 20, 20, 12, 9, 22, 27, 25, 30, 19}, 3));
	}
}
import java.util.*;

class Main {
    public int solution(int[] score, int k){
		int answer = 0;
        
        Arrays.sort(score);
    
        for(int i=0;i<score.length;i++){
            if(i+k-1 >= score.length) break;
            int lastIdx = i + k - 1;
            int firstIdx = i;
            if(score[lastIdx] - score[firstIdx] <= 10){
                long sum = 0;
                for(int j=firstIdx;j<=lastIdx;j++){
                    sum += score[j];
                }
                return (int)(sum/k); //sliding window
            }
        }

		return answer;
	}

	public static void main(String[] args){
		Main T = new Main();
		System.out.println(T.solution(new int[]{99, 97, 80, 91, 85, 95, 92}, 3));
		System.out.println(T.solution(new int[]{92, 90, 77, 91, 70, 83, 89, 76, 95, 92}, 4));
		System.out.println(T.solution(new int[]{77, 88, 78, 80, 78, 99, 98, 92, 93, 89}, 5));
		System.out.println(T.solution(new int[]{88, 99, 91, 89, 90, 72, 75, 94, 95, 100}, 5));
	}
}
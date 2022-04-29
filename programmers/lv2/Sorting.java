import java.util.*;
class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        
        Arrays.sort(citations);
        
        int left = 0; //최소
        int right = 10000; //최대
        
        int max = 0;
       
        while(left <= right){
            int mid = (left+right)/2;
            
            boolean cnt = getCount(citations,mid);
            
            if(cnt){
                max = Math.max(mid,max);
                left = mid+1;
            }else{
                right = mid-1;
            }
        }
            
        answer = max;
        return answer;
    }

    public boolean getCount(int [] citations, int stdNum){
        int cnt = 0;
        int rest = 0;
        for(int i : citations){
            if(i >= stdNum){
                cnt++;
            }
            if(i <= stdNum){
                rest++;
            }
        }
        
        if(cnt >= stdNum && rest <= stdNum) return true;
        return false;
    }
}
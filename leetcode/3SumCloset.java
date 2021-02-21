class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        
        int sum = target;
        int ansSum = 0;
        int difference = Integer.MAX_VALUE;
        
        for(int i=0;i<nums.length-2;i++){
            int left = i+1;
            int right = nums.length-1;
            
            while(left < right){
                int currentSum = nums[i] + nums[left] + nums[right];
                if(currentSum == sum){
                    return sum;
                }else{
                    if(currentSum < sum){
                        left++;
                    }else{
                        right--;
                    }
                    
                    int temp = Math.abs(currentSum - sum);
                    if(temp < difference){
                        difference = temp;
                        ansSum = currentSum;
                    }
                }
                
            }
        }
        return ansSum;
        
  
    }
}


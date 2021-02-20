class Solution {
    public boolean checkPossibility(int[] nums) {
        int temp = -1;
        int cnt = 0;
        int cntA = 0;
        
        if(nums.length == 1) return true;
        
        for(int i=1;i<nums.length;i++){
            if(nums[i-1] > nums[i]){
                if(temp != -1) return false;
                else temp = i;
            }
        }        
        return (temp == -1 || temp == 1 || temp == nums.length-1 || nums[temp-2] <= nums[temp] || nums[temp-1] <= nums[temp+1] );
    }
}
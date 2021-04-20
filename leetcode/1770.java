class Solution {
    
    public int maximumScore(int[] nums, int[] multipliers) {
        
        int result = sol(nums, multipliers, 0, nums.length-1,0,new Integer[multipliers.length][multipliers.length]);
        return result;
    }
    
    public int sol(int [] nums, int [] mult, int s, int e, int idx, Integer [][] mat){
        if(idx == mult.length) return 0;
        if(mat[s][idx] != null) return mat[s][idx];
        
        int sol1 = nums[s] * mult[idx] + sol(nums, mult, s+1,e,idx+1,mat);
        int sol2 = nums[e] * mult[idx] + sol(nums, mult, s,e-1,idx+1,mat);
        mat[s][idx] = Math.max(sol1, sol2);
        return mat[s][idx];
    }
    
}
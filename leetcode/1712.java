class Solution {
    public int waysToSplit(int[] nums) {
        int [] prefix = new int [nums.length];
        prefix[0] = nums[0];
        int sum =nums[0];
        for(int i=1;i<nums.length;i++){
            prefix[i] = prefix[i-1] + nums[i];
            sum += nums[i];
        }


        long res = 0;
        int mod = 1000000007;
    
        
        for(int i=0;i<nums.length;i++){
            int left = prefix[i];
            
            int l= i+1;
            int r = nums.length-2;
            int leftbound = -1;
            int rightbound = -1;
            
            while(l<=r){
                int m = l+(r-l)/2;
                int mid = prefix[m] - left;
                if(mid >= left){
                    leftbound = m;
                    r = m-1;
                }else{
                    l = m+1;
                }
            }
            
            l = i+1;
            r = nums.length-2;
            
            while(l<=r){
                int m = l+(r-l)/2;
                int mid = prefix[m] - left;
                int right = sum - mid - left;
                if(mid <= right){
                    rightbound = m;
                    l = m+1;
                }else{
                    r = m-1;
                }
            }
            if(leftbound == -1 || rightbound == -1 || leftbound > rightbound) continue;
            int diff = rightbound - leftbound+1;
            res += diff;
            res %= mod;
        }

            
        return (int)res;
    }
}
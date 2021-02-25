class Solution{
    public static int minSubArray(int [] nums, int p){
        int n = nums.length;
        long [] ps = new long[n+1];
        for(int i=1;i<=n;i++){
            ps[i] = ps[i-1] + nums[i-1];
        }
        long rem = ps[n] % p;
        if(rem == 0) return 0;

        Map<Long, Integer> map = new HashMap<>();
        map.put(0L,n+1);

        int ans = n;
        long sum = 0;

        for(int i=n;i>=0;i--) {
            long reminder = ps[i] % p;
            long required = 0;
            if(!(reminder == 0)){
                required = p - reminder;
            }
            if(map.containsKey(required)){
                ans = Math.min(ans,map.get(required)-i-1);
            }
            if(i>0) sum += nums[i-1];
            map.put(sum % p, i);
        }
        return ans == n ? -1 : ans;
    }
}
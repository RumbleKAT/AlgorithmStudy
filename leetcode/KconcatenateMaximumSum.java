class Solution {
    long mod = 1000000007;
    
    public long kadene(int [] arr){
        long cur = arr[0];
        long max = arr[0];
        for(int i=1;i<arr.length;i++){
            if(cur >= 0 ) cur += arr[i];
            else cur = arr[i];
            
            max = Math.max(max, cur);
        }
        
        return max;
    }
    
    public long kadeneTwo(int [] arr){
        int [] narr = new int[arr.length*2];
        for(int j=0;j<arr.length;j++){
            narr[j] = arr[j];
        }
        for(int j=0;j<arr.length;j++){
            narr[j+arr.length] = arr[j];
        }
        
        return kadene(narr);
    }
    
    public int kConcatenationMaxSum(int[] arr, int k) {
        long sum = sum(arr);
        long res = 0;
        if(k == 1)  res = kadene(arr);
        else if( sum < 0)  res = kadeneTwo(arr);
        else res = kadeneTwo(arr) + (k-2) * sum;
        
        if(res < 0) return 0;
        return (int)(res % mod);
    }
    
    public long sum(int [] arr){
        long an = 0;
        
        for(int temp : arr){
            an += temp;
        }
        
        return an;
    }
}
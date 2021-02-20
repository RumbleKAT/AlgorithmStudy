class Solution {
    public String fractionToDecimal(int n, int d) {
        if(n == 0) return  "0";
        StringBuilder ans = new StringBuilder();
        if((n < 0 && d < 0) || (n> 0 && d>0) ){
            
        }else{
            ans.append("-");
        }
        
        long q = n / d;
        long r = n % d;
        q = Math.abs(q);
        ans.append(q);
        r = Math.abs(r);
        
        if(r == 0){
            return ans.toString();
        }else{
            ans.append(".");
            
            HashMap<Long, Integer> map = new HashMap<>();
            n = Math.abs(n);
            d = Math.abs(d);
            
            while(r != 0){
                if(map.containsKey(r)){
                    int pos = map.get(r);
                    ans.insert(pos, "(");
                    ans.append(")");
                    break;
                }else{
                    map.put(r,ans.length());
                    r *= 10;
                    q = r / d;
                    r = r % d;
                    ans.append(Math.abs(q));
                }                   
            }
        }
        
        return ans.toString();
      
    }
    
}
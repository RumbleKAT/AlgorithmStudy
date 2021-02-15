class Solution {
    class StrComparator implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            String s1s2 = s1 + "" + s2;
            String s2s1 = s2 + "" + s1;
            return s1s2.compareTo(s2s1);
        }
    }
    
    public String largestNumber(int[] nums) {
        String [] str = new String[nums.length];
        for(int i=0;i<nums.length;i++){
            str[i] = nums[i] + "";
        }
        Arrays.sort(str, new StrComparator());
        String result= "";
        
        for (int i = 0; i < str.length; i ++) {
            // System.out.println(str[i]);
            result = str[i] + result;
        }
        
        if(result.charAt(0) == '0'){
            return "0";
        }
        
        return result;
    }
}
import java.util.*;

class Solution {
    public boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        String tmp = s.toLowerCase();
        for(char c: tmp.toCharArray()){
            if(Character.isAlphabetic(c) || Character.isDigit(c)){
                sb.append(c);
            }
        }
        String cur = sb.toString();
        int left = 0;
        int right = cur.length()-1;
        if(cur.length() == 1) return true;

        while(left <= right){
            if(cur.charAt(left) == cur.charAt(right)){
                left++;
                right--;
                continue;
            }else{
                return false;
            }
        }
        return true;
    }
}
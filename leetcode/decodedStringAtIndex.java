class Solution {
    public String decodeAtIndex(String S, int K) {
        long size = 0; //전체 길이를 먼저 구하고
        for(int i=0;i<S.length();i++){
            char ch = S.charAt(i);
            if(Character.isDigit(ch)){
                size *= ch-'0';
            }else{
                size++;
            }
        }
        
        for(int i=S.length()-1;i>=0;i--){
            char ch = S.charAt(i);
            if(Character.isDigit(ch)){
                size /= ch - '0'; //숫자가 나오면 나누고 
                K %= size; //숫자가 나온 나머지를 K에 넣는다.
            }else{
                if(K == 0 || K == size){ //K가 0 이거나 K == size인경우 케릭터 리턴 
                    return ch+"";
                }
                size--;
            }
        }
        
        return "";
    }
}
class Solution {
    public String reverseWords(String s) {        
        String temp = s.trim();
        
        char [] arr = temp.toCharArray();
        char [] ans = new char[arr.length];
        
        boolean check = false;
        
        ArrayList<String> str = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<arr.length;i++){
            if(arr[i] == ' '){
                if(sb.length() > 0){
                    str.add(sb.toString());
                    sb.setLength(0);
                }  
            }else{
                sb.append(arr[i]);
                check = false;
            }
        }
        
        if(sb.length() > 0){
            str.add(sb.toString());
            sb.setLength(0);
        }        
        
        for(int i=str.size()-1;i>=1;i--){
            sb.append(str.get(i) + " ");
        }
        sb.append(str.get(0));
        
        return sb.toString();
    }
}
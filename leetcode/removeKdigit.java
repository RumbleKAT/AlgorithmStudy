class Solution {
    public  String removeKdigits(String num, int k) {
        //0이 나오면 스택에 넣지 않고 , 자신보다 큰수가 나오면 K개 만큼 pop을 한다.
        Stack<Integer> st = new Stack<>();
        if(num.length() <= k) return "0";

        for(int i=0;i<num.length();i++){
            int cur = num.charAt(i) - '0';

            if(st.empty()){
                if(cur != 0)st.add(cur);
            }else{
                if(st.peek() > cur){
                    while(!st.empty() && k > 0 && st.peek() > cur){
                        st.pop();
                        k--;
                    }
                }else if(cur == 0) {
                    while (!st.empty() && k > 0) {
                        st.pop();
                        k--;
                    }
                }

                if(cur != 0 && k > 0){
                    st.add(cur);
                }
                else if(k == 0){
                    if(st.isEmpty() && cur == 0) continue;
                    st.add(cur);
                }
            }
        }
        
        while(k!=0){
            if(!st.isEmpty())st.pop(); k--;
        }

        StringBuilder sb = new StringBuilder();
        while(!st.empty()) {
            sb.append(st.pop() + "");
        }
        String tmp = sb.reverse().toString();

        if(tmp.equals("")){
            return "0";
        }
        return tmp;
    }
}
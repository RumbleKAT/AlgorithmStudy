
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println(checkValidString("("));
    }
    public static boolean checkValidString(String s) {
        Stack<Integer> st = new Stack<>();
        Stack<Integer>star = new Stack<>();

        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if(ch == '('){
                st.push(i);
            }else if(ch == '*'){
                star.push(i);
            }else{
                if(st.isEmpty() && star.isEmpty()) return false;
                else if(st.isEmpty()) star.pop();
                else{
                    st.pop();
                }
            }
        }

        while(!st.isEmpty() && !star.isEmpty()){
            if(st.peek() > star.peek()) break;
            st.pop();
            star.pop();
        }

        return st.isEmpty();
    }
}

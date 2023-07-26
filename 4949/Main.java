import java.util.*;
import java.io.*;

class Main{
    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            String tmp = br.readLine();
            if(tmp.charAt(0) =='.'){
                break;
            }
            System.out.println(getBalance(tmp));
        }
    }
    static String getBalance(String tmp){
        Stack<Character> tmp1 = new Stack<>();
        
        for(int i=0;i<tmp.toCharArray().length;i++){
            char cur = tmp.charAt(i);
            if(cur == '(' || cur == '[') tmp1.push(cur);
            else if(cur == ')'){
                if(tmp1.isEmpty() || tmp1.peek() != '('){
                    return "no";
                }else{
                    tmp1.pop();
                }
            }
            else if(cur == ']'){
                if(tmp1.isEmpty() || tmp1.peek() != '['){
                    return "no";
                }else{
                    tmp1.pop();
                }
            }
        }
        if(!tmp1.isEmpty()){
            return "no";
        }
        return "yes";
    }
}
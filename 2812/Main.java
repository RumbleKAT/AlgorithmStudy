import java.util.*;
import java.io.*;

class Main{

    static int N,K;
    static String str;
    static int [] dp;
    static int [] arr;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(token.nextToken());

        token = new StringTokenizer(br.readLine());

        str = " " + token.nextToken();

        Stack<Integer>st = new Stack<>();

        int backUp = K;

        for(int i=1;i<str.length();i++){
            int cur = str.charAt(i)-'0';
            if(st.isEmpty()){
               st.push(cur);
               continue;
            }

            if(st.peek() > cur){
                //스택의 최상위 값보다 작은경우 넣는다.
                st.push(cur);
            }else if (st.peek() < cur){
                //peek 값보다 큰 경우 바꿔준다.
                st.pop();
                st.push(cur);
                K-=1;
            }
        }
        StringBuilder sb = new StringBuilder();

        for(int i=0;i<N-K;i++){
            sb.append(st.get(i));
        }

        System.out.println(sb.toString());

    }
}

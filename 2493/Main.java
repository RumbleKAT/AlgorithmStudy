import java.util.*;
import java.io.*;

class Main{

    static int N;
    static long [] arr;
    static Stack<Node> st;

    public static void main(String [] args) throws Exception{
        // System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(token.nextToken());
        
        st = new Stack<>();
        arr = new long [N+1];

        StringBuilder sb = new StringBuilder();
        token = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++){
            arr[i] = Integer.parseInt(token.nextToken());            
            //입력을 받으면서 스택에 존재하는 값을 비교한다.
            if(st.isEmpty()){
                st.add(new Node(i,arr[i]));
                sb.append("0 ");            
            }else{
                while(!st.isEmpty()){
                    if(st.peek().val >= arr[i]){
                        sb.append(st.peek().idx + " ");
                        break;
                    }else{
                        st.pop();
                    }
                }
            }
          
            if(st.isEmpty())sb.append("0 ");            
            st.add(new Node(i, arr[i]));
        }
        System.out.println(sb.toString().trim());
    }
}
class Node{
    public int idx;
    public long val;

    Node(int idx, long val){
        this.idx = idx;
        this.val = val;
    }
}
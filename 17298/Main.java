import java.util.*;
import java.io.*;

class Main{
    
    static int N;
    static int [] arr;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(token.nextToken());
        arr = new int[N+1];
        int [] res = new int[N+1];
        token = new StringTokenizer(br.readLine());
        for(int i =1;i<=N;i++){
            arr[i] = Integer.parseInt(token.nextToken());
            res[i] = -1;
        }

        Stack<Integer> st = new Stack<>();

        for(int i=N;i>=1;i--){
            while(!st.isEmpty() && arr[st.peek()] <= arr[i]){
                st.pop();
            }
            if(!st.isEmpty()){
                res[i] = arr[st.peek()];
            }
            st.add(i);
        }
        for(int i=1;i<=N;i++){
            System.out.print(res[i] + " ");
        }
        System.out.println();
        
    }
}
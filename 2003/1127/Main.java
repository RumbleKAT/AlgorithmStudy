import java.util.*;
import java.io.*;

class Main{

    static int N;
    static long M;
    static long [] arr;

    public static void main(String [] args) throws Exception{
        // System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        arr = new long[N+1];
        int left = 1;
        int right = 1;

        Deque<Long>deque = new LinkedList<>();
        long sum = 0;
        int cnt = 0;
        token = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++){
            arr[i] = Long.parseLong(token.nextToken());
            
            sum += arr[i];
            deque.addLast(arr[i]);


            if(!deque.isEmpty()){
                while(sum > M){
                    sum -= deque.pollFirst();
                }
                if(sum == M){
                    cnt++;
                }
            }
            
        }

        System.out.println(cnt);

    }
}
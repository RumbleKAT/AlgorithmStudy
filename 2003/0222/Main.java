import java.io.*;
import java.util.*;

public class Main {
    static long [] arr;
    static int N,M;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        arr = new long[N+1];
        token = new StringTokenizer(br.readLine());

        for(int i=1;i<=N;i++){
            arr[i] = Long.parseLong(token.nextToken());
        }

        int cnt = 0;

        int left = 1;
        int right = 1;
        
        long sum = 0;    

        while(true){
            if(sum >= M) sum -= arr[left++];
            else if(right == N+1) break;
            else sum += arr[right++];
            if(sum == M) cnt++;
        }
        
        System.out.println(cnt);
    }
}

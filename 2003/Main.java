import java.io.*;
import java.util.*;

class Main{

    static int N;
    static long M;
    static int count = 0;
    static long [] arr;

    public static void main(String [] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Long.parseLong(token.nextToken());

        arr = new long[N+1];
        token = new StringTokenizer(br.readLine());
        
        for(int i =1;i<=N;i++){
            arr[i] = Integer.parseInt(token.nextToken());
        }

        int s = 1;
        int e = 1;
        long sum = 0;
        int count = 0;

        while(true){
            if(sum >= M) sum -= arr[e++];
            else if(s > N) break;
            else sum += arr[s++];
            if(sum == M) count++;
        }

        System.out.println(count);
    }
}
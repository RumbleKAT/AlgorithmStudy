import java.util.*;
import java.io.*;

class Main {
    
    static int N,M;
    static long [] arr;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        
        arr = new long[100001];
        token = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++){
            arr[i] = Integer.parseInt(token.nextToken());
        }

        int s = 1;
        int e = 1;
        long sum = 0;
        int cnt = 0;

        while(e <= N+1){
            if(sum == M){
                cnt++;
            }
            if(sum >= M){
                sum -= arr[s];
                s++;
            }else{
                sum += arr[e];
                e++;
            }

        }
        System.out.println(cnt);
    }
}
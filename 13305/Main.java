import java.util.*;
import java.io.*;

class Main{

    static int N;
    
    static long [] dp;
    static long [] gas;
    static long [] dist;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());

        dp = new long [N+1];
        gas = new long[N+1];
        dist = new long [N];

        Arrays.fill(dp, Integer.MAX_VALUE);

        token = new StringTokenizer(br.readLine());
        for(int i =1;i<N;i++){
            dist[i] = Long.parseLong(token.nextToken());
        }

        token = new StringTokenizer(br.readLine());
        for(int i =1;i<=N;i++){
            gas[i] = Long.parseLong(token.nextToken());
        }

        dp[1] = gas[1] * dist[1];

        int idx = 1;
        for(int i=2;i<N;i++){
            dp[i] = dp[i-1] + (gas[i] * dist[i]);
            if(dp[i] < dp[i-1] + (gas[idx]*dist[i])){
                idx = i;
            }else{
                dp[i] = dp[i-1] + (gas[idx]*dist[i]);
            }
        }
        System.out.println(dp[N-1]);
    }
}
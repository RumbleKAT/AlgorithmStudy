import java.util.*;
import java.io.*;

class Main{

    static int N;
    static int M;
    static int [] arr;
    static int [] dp;
    static int INF = 10001;
    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        arr = new int[N+1];

        int max = 0;

        for(int i=1;i<=N;i++){
            token = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(token.nextToken());
            max = Math.max(max,arr[i]);
        }
        dp = new int[M+1];

        Arrays.fill(dp,INF);
        dp[0] = 0;

        for(int i=1;i<=N;i++){
            for(int j=arr[i];j<=M;j++){
                if(dp[j-arr[i]] != INF){
                    dp[j] = Math.min(dp[j],dp[j-arr[i]]+1);
                }
            }
        }

        if(dp[M] == INF){
            System.out.println("-1");
            // return;
        }else{
            System.out.println(dp[M]);
        }
        // System.out.println(Arrays.toString(dp));
    }
}
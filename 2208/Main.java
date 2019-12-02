import java.util.*;
import java.io.*;

class Main{

    static int N,M;
    static int [] arr;
    static long [] dp;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        arr = new int [N+1];
        dp = new long [N+2]; //N개의 품목 중에서 M번의 수열의 연속합


        for(int i=1;i<=N;i++){
            token = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(token.nextToken());
            dp[i+1] = dp[i] + arr[i]; //전처리
        }

        System.out.println(Arrays.toString(dp));

        //M개 이상의 보석을 주울 경우랑 , 줍지 않을 경우

        //처음엔 무조건 보석을 주워야되는데,
        //언제까지 보석을 줍다가 갈것인지 
        long result = 0;
        long temp = 0;

        for(int i =M-1;i<=N;i++){
            System.out.println(dp[i-M+1]);
            temp = Math.min(temp,dp[i-M+1]);
            System.out.println((dp[i+1]-temp) + " "  + dp[i+1] + " " + temp);
            result = Math.max(result, dp[i+1]-temp);
        }

        System.out.println(result);

    }
}
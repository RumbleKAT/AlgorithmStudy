import java.util.*;
import java.io.*;

class Main{

    static int N;
    static int M;
    static long [][] dp;

    public static void main(String [] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        dp = new long[N+1][N+1];

        for(int i =1;i<=N;i++){
            for(int j=1;j<=N;j++){
                if(i == j) continue;
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        for(int i =1;i<=M;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());
            long val = Long.parseLong(token.nextToken());;

            dp[s][e] = Math.min(dp[s][e],val);
        }

        for(int k=1;k<=N;k++){
            for(int i =1;i<=N;i++){
                for(int j=1;j<=N;j++){
                    dp[i][j] = Math.min(dp[i][j],dp[i][k]+dp[k][j]);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i =1;i<=N;i++){
            for(int j =1;j<=N;j++){
                if(dp[i][j] == Integer.MAX_VALUE){
                    //System.out.print(0 + " ");
                    sb.append(0 + " ");
                }else{
                    sb.append(dp[i][j] + " ");
                }
            }
            if(i!=N)sb.append("\n");
        }

        System.out.print(sb.toString());
    }
}
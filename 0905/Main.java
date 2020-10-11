import java.util.*;
import java.io.*;

public class Main {
    
    static long [][] dp = new long [5001][5001];
    static long [][] ans = new long[5001][5001];
    static final long MOD = 1000000009;
    static int N,M;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        
        for(int i=1;i<=5000;i++){
            dp[i][0] = dp[i][i] = 1;
            for(int j=1;j<=i;j++){
                dp[i][j] = (dp[i-1][j] + dp[i-1][j-1])%MOD;
                // System.out.print(dp[i][j] + " ");
            }// }System.out.println();
        }

        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        
        long res = 0;
        int cnt = 5;

        for(int a=1;a<=cnt;a++){
            for(int i=0;i<=N;i++){
                for(int j=0;j<=M;j++){
                    if(i + j == a){
                        // System.out.println(a + " " +  i+ " " + j+ " " + " " + dp[i][j]);
                        res = (res + dp[i][j])%MOD;
                    }
                }
            }
        }
                

        System.out.println(res);

    }    
}
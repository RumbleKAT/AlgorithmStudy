import java.util.*;
import java.io.*;

public class Main {
    
    static int TC;
    static long [][] dp = new long[31][31];
    static final long MOD = 1000000007;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        
        TC = Integer.parseInt(token.nextToken());

        for(int i=0;i<=30;i++){
            dp[i][i] = dp[i][0] = 1;
            for(int j=1;j<=i;j++){
                dp[i][j] = (dp[i-1][j] + dp[i-1][j-1]);
            }
        }

        for(int t=1;t<=TC;t++){
            token = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(token.nextToken());
            int M = Integer.parseInt(token.nextToken());
            
            System.out.println(dp[M][N]);

        }
        
        
    }
}

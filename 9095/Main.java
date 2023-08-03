import java.util.*;
import java.io.*;

class Main{
    static int N;
    static int [] dp;
    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        dp = new int[12];

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for(int i=4;i<=11;i++){
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
        }

        for(int i=1;i<=N;i++){
            token = new StringTokenizer(br.readLine());
            System.out.println(dp[Integer.parseInt(token.nextToken())]);
        }

    }
}
import java.util.*;
import java.io.*;

class Main{

    static int N;
    static int [] dp;

    public static void main(String [] args) throws Exception{
        //System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());

        dp = new int[1001];
        dp[1] = 1;
        dp[2] = 3;
        if(N>=3){
            for(int i=3;i<=N;i++){
                dp[i] = (dp[i-1]%10007 + 2*dp[i-2]%10007)%10007;
            }        
        }
        System.out.println(dp[N]);
    }
}
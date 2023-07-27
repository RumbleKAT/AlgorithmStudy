import java.io.*;
import java.util.*;

class Main{

    static int N;
    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());

        int [] dp = new int[13];
        dp[0] = 1;
        dp[1] = 1;

        for(int i=2;i<=12;i++){
            dp[i] = i * dp[i-1];
        }
        System.out.println(dp[N]);

    }
}
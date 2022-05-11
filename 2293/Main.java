import java.util.*;
import java.io.*;

class Main{
    static int n,k;
    static int [] arr;
    static int [] dp;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        n = Integer.parseInt(token.nextToken());
        k = Integer.parseInt(token.nextToken());

        arr = new int[n+1];
        dp = new int[k+1];

        dp[0] = 1;
        for(int i=1;i<=n;i++){
            token = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(token.nextToken());
            
            for(int j=arr[i];j<=k;j++){
                dp[j] = dp[j] + dp[j-arr[i]];
            }
            // System.out.println(Arrays.toString(dp));
        }

        System.out.println(dp[k]);
    }
}
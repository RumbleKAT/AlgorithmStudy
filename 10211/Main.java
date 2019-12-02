import java.util.*;
import java.io.*;

class Main{

    static int TC;
    static int N;
    static int [] arr;
    static int [] dp;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        TC = Integer.parseInt(token.nextToken());

        for(int t=1;t<=TC;t++){
            token = new StringTokenizer(br.readLine());
            N = Integer.parseInt(token.nextToken());
            arr = new int [N+1];
            dp = new int [N+2];

            token = new StringTokenizer(br.readLine());
            for(int i=1;i<=N;i++){
                arr[i] = Integer.parseInt(token.nextToken());
                dp[i] = dp[i-1] + arr[i];
            }

            long result = Integer.MIN_VALUE;
            // for(int i=1;i<=N;i++){
            long p1 = 0;
            long p2 = 0;

            for(int j=1;j<=N;j++){
                p1 += arr[j];
                result = Math.max(p1 - p2, result);
                p2 = Math.min(p1,p2);
            }

            System.out.println(result);


        }

    }
}
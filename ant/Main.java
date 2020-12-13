import java.util.*;
import java.io.*;

class Main{

    static int N;
    static int [] arr;
    static int [] dp;
    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        arr = new int[N+1];
        dp = new int [N+1];

        token = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++){
            arr[i] = Integer.parseInt(token.nextToken());
        }

        dp[1] = arr[1];
        dp[2] = Math.max(dp[1],arr[2]); //첫번째 두번째 식량중 최대값을 조회한다.

        for(int i=3;i<=N;i++){
            dp[i] = Math.max(dp[i-1],dp[i-2] + arr[i]); //한칸 떨어진 것의 최대값 + 현재 값
        }

        System.out.println(dp[N]);
    }
}
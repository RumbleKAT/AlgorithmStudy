import java.util.*;
import java.io.*;


class Main{

    static int len;
    static int [] arr;
    static int [] dp;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        len = Integer.parseInt(token.nextToken());

        arr = new int[len];
        dp = new int [len];

        token = new StringTokenizer(br.readLine());
        for(int i=0;i<len;i++){
            arr[i] = Integer.parseInt(token.nextToken());
        }

        int max = 0;

        for(int i=0;i<len;i++){
            dp[i] = arr[i];
            for(int j=0;j<i;j++){
                if(arr[i] > arr[j] && dp[i] < dp[j] + arr[i]){
                    dp[i] = dp[j] + arr[i];
                }
            }
            max = Math.max(max, dp[i]);
        }

        System.out.println(max); 

    }
}
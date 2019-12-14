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

        // int max = 0;

        // for(int i=0;i<len;i++){
        //     dp[i] = arr[i];
        //     for(int j=0;j<i;j++){
        //         if(arr[i] > arr[j] && dp[i] < dp[j] + arr[i]){
        //             dp[i] = dp[j] + arr[i];
        //         }
        //     }
        //     max = Math.max(max, dp[i]);
        // }

        // System.out.println(max); 

        dp[0] = arr[0];

        int idx = 0;
        for(int i=1;i<len;i++){
            if(dp[idx] < arr[i]){
                dp[++idx] = arr[i];
            }else{
                // int ii = lowerbound(idx,arr[i]);
                dp[lowerbound(idx,arr[i])] = arr[i];
            }
            // System.out.println(Arrays.toString(dp));
        }

        System.out.println(Arrays.toString(dp));
    }

    static int lowerbound(int right, int key){
        int left = 0;
        while(left < right){
            int mid =(left+right)/2;
            
            if(dp[mid] >= key){
                right = mid;
            }else{
                left = mid+1;
            }
        }
        return right;
    }
}
import java.util.*;
import java.io.*;

class Main{

    static int N;
    static int [] arr;
    static long [] dp;
    static long result;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        arr = new int [N+1];
        dp = new long [N+1];
        result = Integer.MIN_VALUE;

        for(int i = 1;i<=N;i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
                
        for(int i =1;i<=N;i++){
            int min = arr[i];
            //System.out.println("Min : " + min);
            for(int j=i;j<=N;j++){
                //System.out.println(arr[j]);
                if(min > arr[j]){
                    //System.out.println(min + " " + arr[j]);
                    break;
                }else{
                    dp[i] += min; 
                }
            }
            result = Math.max(dp[i], result);
            //System.out.println("dp : " + dp[i]);
            //System.out.println();
        }
        System.out.println(result);
    }
}
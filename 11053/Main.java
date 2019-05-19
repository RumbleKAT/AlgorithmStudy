import java.util.*;
import java.io.*;

class Main{

    static int N;
    static int [] arr = new int[1001];
    static int [] dp = new int[10001];
    static int index = 0;
    
    public static void main(String [] args){
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();

        for(int i=1;i<=N;i++){
            arr[i] = scan.nextInt();            
        }

        int sol = 1;
        for(int i=1;i<=N;i++){
            dp[i] = 1;
            for(int j =1;j<=i;j++){
                if(arr[i] > arr[j]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
            sol = Math.max(sol,dp[i]);
        }
        System.out.println(sol);
    }
}
import java.util.*;
import java.io.*;

class Main{
    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String a1 = br.readLine();
        String b2 = br.readLine();

        int [][] dp = new int[a1.length()+1][b2.length()+1];

        char [] t1 = a1.toCharArray();
        char [] t2 = b2.toCharArray();

        int max = 0;

        for(int i=1;i<=t1.length;i++){
            for(int j=1;j<=t2.length;j++){
                if(t1[i-1] == t2[j-1]){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
                max = Math.max(dp[i][j], max);
            }
        }

        System.out.println(max);

    }
}
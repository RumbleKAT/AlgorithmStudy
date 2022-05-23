import java.util.*;
import java.io.*;

class Main{

    static int N;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(token.nextToken());
        
        int ans = 0;
        while( N >= 0){
            if(N % 5 == 0){
                ans += (N/5);
                System.out.println(ans);
                return;
            }
            N -= 3;
            ans++;
        }
        System.out.println(-1);
        
    }
}
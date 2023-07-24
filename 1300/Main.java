import java.util.*;
import java.io.*;

class Main{

    static int N;
    static int K;

    public static void main(String [] args) throws Exception{
        // System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        token = new StringTokenizer(br.readLine());
        K = Integer.parseInt(token.nextToken());
        
        long lo = 1;
        long hi = K;

        while(lo < hi){
            long mid = (lo + hi)/2;
            long count = 0;

            for(int i=1;i<=N;i++){
                count += Math.min(mid/i,N);
            }
            if(K<=count){
                hi = mid;
            }else{
                lo = mid + 1;
            }
        }
        System.out.println(lo);
    }
}
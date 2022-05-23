import java.util.*;
import java.io.*;

class Main{

    static int N;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(token.nextToken());
        
        int cnt = 0;

        //5로 나눈 나머지 
        int a = Integer.MAX_VALUE;
        int b = Integer.MAX_VALUE;

        if(N % 5 == 0){
            a = N / 5;
        }
        if(N % 3 == 0){
            b = N / 3;
        }

        int c = N / 5;
        int min_c = Integer.MAX_VALUE;
        for(int i =1;i<=c;i++){
            int d = N - (i*5);    
            if(d % 3 == 0){
                min_c = Math.min(min_c, i+(d/3));
            }
        }
        // System.out.println(min_c);
        // if(min_c == Integer.MAX_VALUE) min_c = 0;
 
        int e = N / 3;
        int min_e = Integer.MAX_VALUE;
        for(int i =1;i<=e;i++){
            int f = N - (i*3);    
            if(f % 5 == 0){
                min_e = Math.min(min_c, i+(f/5));
            }
        }
        // System.out.println(min_e);
        //3으로 나눈 나머지
        int ans = Math.min(Math.min(a,b),Math.min(min_c,min_e));
        if(ans == Integer.MAX_VALUE){
            System.out.println("-1");
        }else{
            System.out.println(ans);
        }
        
    }
}
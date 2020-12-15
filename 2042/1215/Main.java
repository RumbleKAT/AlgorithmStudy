import java.util.*;
import java.io.*;

public class Main {

    static long [] arr = new long [100001];
    static long [] tree = new long [100001];
    static int N,M,K;

    public static void main(String [] args) throws Exception {
       System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(token.nextToken());

        for(int i=1;i<=N;i++){
            token = new StringTokenizer(br.readLine());
            int temp = Integer.parseInt(token.nextToken());

            arr[i] = temp;
            update(i,arr[i]);
        }

        for(int i=1;i<=M+K;i++){
            token = new StringTokenizer(br.readLine());
            int o = Integer.parseInt(token.nextToken());
            int s = Integer.parseInt(token.nextToken());

            if(o == 1){
                long e = Long.parseLong(token.nextToken());
                update(s, e-arr[s]);
                arr[s] = e; //i번째 수를 value로 업데이트
            }else{
                int e = Integer.parseInt(token.nextToken());
                System.out.println(IntervalSum(s,e));
            }
        }

    }   
    static void update(int i, long diff){
        while(i<=N){
            tree[i] += diff;
            i += (i & -i);
        }
    }  
    static long prefixSum(int i){
        long result = 0;
        while(i>0){
            result += tree[i];
            i -= (i & -i);
        }
        return result;
    }
    static long IntervalSum(int start, int end){
        return prefixSum(end) - prefixSum(start-1);
    }
}

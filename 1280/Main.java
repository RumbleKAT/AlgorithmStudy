import java.util.*;
import java.io.*;

class Main{

    static long [] seg_c;
    static long [] seg_d;

    static long [] arr;
    static long [] dist;
    static long [] cnt;

    static long maxValue = -1;
    static final long mod = 1000000007;
    static int N;
    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());

        arr = new long [N+1];
        dist = new long[N+1];
        cnt = new long[N+1];

        seg_d = new long[N*4];
        seg_c = new long[N*4];

        for(int i = 0;i<N;i++){
            token = new StringTokenizer(br.readLine());
            arr[i] = Long.parseLong(token.nextToken());
            ++cnt[i];
            maxValue = Math.max(maxValue,arr[i]);
        }
        
        long ans = 1;

        for(int i = 0;i<N;i++){
            update(1, 0, maxValue, arr[i]);
        
            // System.out.println("-----");
            // System.out.println(Arrays.toString(seg_d));
            // System.out.println(Arrays.toString(seg_c));
            // System.out.println("-----");

            long left = sum_2(1, 0, maxValue, 0, arr[i])*arr[i] - sum(1, 0, maxValue, 0, arr[i]);
            long right = sum(1, 0, maxValue, arr[i]+1, maxValue) - sum_2(1, 0, maxValue, arr[i]+1, maxValue)*arr[i];
            
            //System.out.println(left + " " + right);
            long re = (((left%mod) + (right%mod))%mod);
            
            if(i >= 1)
            ans  = (re * ans)%mod ;
        }

        System.out.println(ans%mod);

    }

    static void update(int index, long left, long right, long arr_i){
        if(arr_i < left || arr_i > right) return;

        seg_d[index] += arr_i;
        seg_c[index] += 1;

        if(left == right){
            return;
        }

        long mid = (left+right)/2;
        update(index*2,left, mid, arr_i);
        update(index*2+1, mid+1, right, arr_i);
        
    }

    static long sum(int node, long start, long end, long left, long right){
        if(start > right || end < left) return 0;
        if(left <= start && end <= right){
            return seg_d[node];
        }else{
            long mid = (start + end)/2;
            return sum(node*2, start, mid, left, right) + sum(node*2+1, mid+1, end, left, right);
        }
    }

    static long sum_2(int node, long start, long end, long left, long right){
        if(start > right || end < left) return 0;
        if(left <= start && end <= right){
            return seg_c[node];
        }else{
            long mid = (start + end)/2;
            return sum_2(node*2, start, mid, left, right) + sum_2(node*2+1, mid+1, end, left, right);
        }
    }
}
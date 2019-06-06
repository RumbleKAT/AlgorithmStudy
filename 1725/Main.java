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

        for(int i = 1;i<N+1;i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        result = histogram(1, N+1);
        System.out.println(result);
               
    }

    static long histogram(int s, int e){
        if(s == e) return 0;
        if(s+1 == e) return arr[s];

        int mid = (s+e)/2;
        long result = Math.max(histogram(s,mid), histogram(mid, e));
    
        int w = 1;
        int h = arr[mid];
        int left  = mid;
        int right = mid;

        while(right - left +1 < e - s){
            int p = left > s ? Math.min(h, arr[left-1]) : -1;
            int q = right <  e - 1 ? Math.min(h, arr[right+1]) : -1;
            //System.out.println(p + " " + q + " " + s + " " + e);
            if(p >= q){
                h = p;
                left--;
            }
            else{
                h = q;
                right++;
            }
            result = Math.max(result, ++w*h);
        }
        return result;
    }
}
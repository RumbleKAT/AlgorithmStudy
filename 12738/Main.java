import java.util.*;
import java.io.*;

class Main {
    
    static long [] arr;
    static long [] seg;
    static int N;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        token = new StringTokenizer(br.readLine());

        arr = new long[N+1];
        seg = new long[N+1];

        long first = Long.parseLong(token.nextToken());
        int i = 0;
        int j = 0;
        arr[i] = seg[i] = first;
        i++;
        
        while(i < N){
            arr[i] = Long.parseLong(token.nextToken());
            
            if(seg[j] < arr[i]){
                seg[j+1] = arr[i];
                j++;
            }else{
                int e = lower_bound(0,j,arr[i]);
                seg[e-1] = arr[i];
            }
            i++;
        }  

        // System.out.println(Arrays.toString(seg));
        System.out.println(j+1); 

    }
    static int lower_bound(int s, int e, long target){
        int mid = 0;
        while(e - s > 0){
            mid = (s+e)/2;
            if(seg[mid] < target){
                s = mid +1;                        
            }else{
                e = mid;
            }
        }
        return e+1;
    }
}

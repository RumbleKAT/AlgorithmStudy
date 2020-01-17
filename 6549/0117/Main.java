import java.util.*;
import java.io.*;

class Main{


    static int N;
    static long [] seg;
    static long [] arr;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token;

        while(true){

            String str = br.readLine();
            token = new StringTokenizer(str);

            if(token.countTokens() == 1){
                break;
            }

            N = Integer.parseInt(token.nextToken());
            seg = new long [N*4+1];
            arr = new long [N+1];

            for(int i =0;i<N;i++){
                arr[i] = Long.parseLong(token.nextToken());
                update(1,i,arr[i],0,N-1);
            }

            // System.out.println(Arrays.toString(seg));
            // int mid = N/2+1;
            // int idx = Math.min(query(1, 1, mid+1, 1,N),query(1, mid+1, N, 1,N));
            // System.out.println(idx);
            // long area = N * arr[idx];    
            System.out.println(largest(0,N-1));
            
            // System.out.println(area);

        }

    }
    static long largest (int left, int right){
        if(left == right) return arr[left];
        int mid = (left+right)/2;
        long ret = Math.max(largest(left,mid),largest(mid+1,right));
        
        int lo = mid;
        int hi = mid+1;
        long height = Math.min(arr[lo],arr[hi]);

        ret = Math.max(ret,height*2);

        while(left < lo || hi < right){
            if(hi < right && (lo == left || arr[lo-1]<arr[hi+1])){
                hi++;
                height = Math.min(height,arr[hi]);
            }else{
                lo--;
                height = Math.min(height,arr[lo]);
            }
            ret = Math.max(ret, height * (hi-lo+1));
        }
        return ret;
    }

    static long update(int node, int index, long value, int left, int right){
        if(index < left || index > right) return seg[node];
        if(left == right){
            return seg[node] = value;
        }
        int mid = (left+right)/2;
        return seg[node] = update(node*2, index, value, left, mid) + update(node*2+1, index, value, mid+1, right);
    }
    
}
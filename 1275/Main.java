import java.util.*;
import java.io.*;

;public class Main {
    
    static long [] seg;
    static int [] arr;
    static int N,Q;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(token.nextToken());
        Q = Integer.parseInt(token.nextToken());

        token = new StringTokenizer(br.readLine());

        seg = new long [400004];
        arr = new int [N+1];
        
        for(int i=1;i<=N;i++){
            arr[i] = Integer.parseInt(token.nextToken());
        }

        init(1,1,N);

        for(int i=1;i<=Q;i++){
            token = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(token.nextToken());
            int y = Integer.parseInt(token.nextToken());
            int a = Integer.parseInt(token.nextToken());
            int b = Integer.parseInt(token.nextToken());
        
            if(x > y){
                int temp = x;
                x = y;
                y = temp;
            }

            System.out.println(sum(1, 1, N, x, y));
            update(1, a, b, 1, N);
        }

    }
    static long sum(int node, int start, int end, int left, int right){
        if(start > right || end < left) return 0;
        if(left <= start && end <= right) return seg[node];
        int mid = (start+end)/2;
        return sum(node*2, start, mid, left, right) + sum(node*2+1, mid+1, end, left, right);
    }
    static long update(int node, int index, int val, int left, int right){
        if(index < left || index > right) return seg[node];
        if(left == right){
            return seg[node] = val;
        }
        int mid = (left+right)/2;
        return seg[node] = update(node*2, index, val, left, mid) + update(node*2+1, index, val, mid+1, right);
    }
    static long init(int node, int left, int right){
        if(left == right){
            return seg[node] = arr[left];
        }
        int mid = (left+right)/2;
        return seg[node] = init(node*2, left, mid) + init(node*2+1,mid+1,right);
    }
}
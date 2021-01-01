import java.util.*;
import java.io.*;

public class Main {

    static int [] seg;
    static int [] arr;
    static int size;
    static int N,M;
    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        arr = new int[N+1];
        
        int i =1;
        for(i=1;i<=N;i*=2){}
        size = i;

        seg = new int[size*2];
        
        
        token = new StringTokenizer(br.readLine());
        for(i=1;i<=N;i++){
            arr[i] = Integer.parseInt(token.nextToken());
            // update(i, arr[i]);
            update(1,i,arr[i],1,N);
        }

        // System.out.println(Arrays.toString(seg));

        token = new StringTokenizer(br.readLine());
        M = Integer.parseInt(token.nextToken());
        
        for(i=1;i<=M;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());
            System.out.println(query(1, 1, N, s, e));       
        }

    }
    /*
        query 코드도 바뀌어야 됨 
    */
    static void update(int param, int val){
        int idx = size + param - 1;
        int tmp = seg[idx];

        while(idx != 0){
            seg[idx] = seg[idx] - tmp + val;
            idx /=2;
        }
    }    

    static int update(int node, int index, int val, int start, int end){
        if(index < start || index > end) return seg[node];
        if(start == end){
           return  seg[node] += val;
        }
        int mid = (start+end)/2;
        return seg[node] = update(node*2, index, val, start, mid) + update(node*2+1, index, val, mid+1, end);
    }
    static int query(int node, int start, int end, int left, int right){
        if(start > right || end < left) return 0;
        if(left <= start && end <= right) return seg[node];
        int mid = (start+ end)/2;
        return query(node*2, start, mid, left, right) + query(node*2+1, mid +1 , end, left, right);
    }
}

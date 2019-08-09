import java.util.*;
import java.io.*;

class Main{

    static int N,M,K;
    static long [] seg;
    static long [] lazy;
    static long [] arr;
    static int MAX = 1000001;
        
    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(token.nextToken());

        seg = new long [1000001*4];
        lazy = new long [1000001*4];
        arr = new long[MAX];

        for(int i = 1;i<=N;i++){
            token = new StringTokenizer(br.readLine());
            arr[i] = Long.parseLong(token.nextToken());
            update(i, arr[i], 1, 1, MAX);
        }

        for(int i =1;i<=M+K;i++){
            token = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(token.nextToken());
            
            if(order == 1){
                int a = Integer.parseInt(token.nextToken());
                int b = Integer.parseInt(token.nextToken());
                long c = Long.parseLong(token.nextToken());

                update_range(1, c, 1, MAX, a, b);
            }else{
                int a = Integer.parseInt(token.nextToken());
                int b = Integer.parseInt(token.nextToken());

                System.out.println(sum(1, 1, MAX, a, b));
            }
        }

    }
    static long update(int index, long val, int node, int left, int right){
        if(index < left || index > right) return seg[node];
        if(left == right) return seg[node] += val;
        int mid = (left+right)/2;
        return seg[node] = update(index, val, node*2, left, mid) + update(index, val, node*2+1, mid+1, right);
    }

    static void update_lazy(int node, int start, int end){
       if(lazy[node] == 0) return;
       seg[node] += lazy[node] * (end-start+1);
       if(start != end){
           lazy[node*2] += lazy[node];
           lazy[node*2+1] += lazy[node];
       }
       lazy[node] = 0;
    }
    static long update_range(int node, long val, int start, int end, int left, int right){
        update_lazy(node, start, end);
        if(start > right || end < left) return seg[node];
        if(left <= start && end <= right){
            seg[node] += val * (end -start+1);
            if(start != end){
                lazy[node*2] += val;
                lazy[node*2+1] += val;
            }
            return seg[node];
        }
        int mid = (start + end)/2;
        return seg[node] = update_range(node*2, val, start, mid, left, right) + update_range(node*2+1, val, mid+1, end, left, right);
    }

    static long sum(int node, int start, int end, int left, int right){
        update_lazy(node, start, end);
        if(start > right || end < left) return 0;
        if(left <= start && end <= right){
            return seg[node];
        }
        int mid = (start+end)/2;
        return sum(node*2, start, mid, left, right)+sum(node*2+1, mid+1, end, left, right);
    }
}
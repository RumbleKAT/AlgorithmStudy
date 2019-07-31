import java.util.*;
import java.io.*;

class Main{

    static long [] tree = new long[4000000];
    static long [] lazy = new long [4000000];
    static int N,M,K;

    static void propa(int node, int start, int end){
        tree[node] += lazy[node] * (end-start+1);
        if(start != end){
            lazy[node*2] += lazy[node];
            lazy[node*2+1] += lazy[node];
        }
        lazy[node] = 0;
    }

    static long get(int node, int start, int end, int left, int right){
        if(lazy[node] != 0 ) propa(node, start, end);
        if(start > right || end < left) return 0;
        if(left <= start && end <= right) return tree[node];
        int mid = (start + end)/2;
        return get(node*2, start, mid, left,right) + get(node*2+1, mid+1, end, left,right);
    }

    static void update(int node, long val, int start, int end, int left, int right){
        if(lazy[node] != 0 ) propa(node, start, end);
        if(start > right || end < left) return;
        if(left <= start && end <= right){
            tree[node] += val*(end-start+1);
            if(start != end){
                lazy[node*2] += val;
                lazy[node*2+1] += val;
            }
            return;
        }
        int mid = (start+end)/2;
        update(node*2, val, start, mid, left, right);
        update(node*2+1, val, mid+1, end, left, right);
        tree[node] = tree[node*2] + tree[node*2+1];
    }   

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(token.nextToken());

        for(int i = 1;i<=N;i++){
            token = new StringTokenizer(br.readLine());
            long node = Long.parseLong(token.nextToken());
            update(1, node,1, N, i, i);
        }

        for(int i = 1;i<=M+K;i++){
            token = new StringTokenizer(br.readLine());
            int flag = Integer.parseInt(token.nextToken());
            if(flag == 1){
                int start = Integer.parseInt(token.nextToken());
                int end = Integer.parseInt(token.nextToken());
                long val = Long.parseLong(token.nextToken());

                update(1, val, 1,N,start, end);
            }else{
                int start = Integer.parseInt(token.nextToken());
                int end = Integer.parseInt(token.nextToken());
                
                System.out.println(get(1, 1, N, start, end));
            }
        }
       

    }
    
}
import java.util.*;
import java.io.*;

class Main{

    static final int MAX = 65540;
    static long [] seg = new long [1000001];
    static int [] arr = new int [250001];
    static int N, K;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(token.nextToken());

        long sum = 0;
        for(int i = 1;i<=N;i++){
            token = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(token.nextToken());
            update(arr[i], 1, 1, 0, MAX);
            if(i >= K){
                if(i != K) update(arr[i-K], -1, 1, 0, MAX);
                long ans = get(1, 0, MAX, (K+1)/2);
                sum += ans;
            }
        }
        System.out.println(sum);

    }
    static long update(int index, long val, int node, int left, int right){
        if(index < left || index > right) return seg[node];
        if(left == right){
            return seg[node] += val;
        }
        int mid = (left+right)/2;
        return seg[node] = update(index, val, node*2, left, mid) + update(index, val, node*2+1, mid+1, right);
    }

    static long get(int node, int left, int right, long k){
        if(left == right) return left;
        int mid = (left+right)/2;
        if(k <= seg[node*2]) return get(node*2, left, mid, k);
        else return get(node*2+1, mid+1, right, k-seg[node*2]);
    }


}
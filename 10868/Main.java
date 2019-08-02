import java.util.*;
import java.io.*;

class Main{

    static int N,M;
    static long [] arr;
    static long [] seg;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        arr = new long [N+1];
        seg = new long [N*4];

        for(int i = 1;i<=N;i++){
            arr[i] = Integer.parseInt(br.readLine());
            update(i,arr[i],1,1,N);
        }

        for(int i =1 ;i<=M;i++){
            token = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(token.nextToken());
            int b = Integer.parseInt(token.nextToken());

            System.out.println(get(1,1,N,a,b));
        }

        //System.out.println(Arrays.toString(seg));

    }

    static long get(int node, int start, int end, int left, int right){
        if(start > right || end < left) return Integer.MAX_VALUE;//해당 범위를 넘어서는 것은 최대값을 추출
        if(left <= start && end <= right) return seg[node];
        int mid = (start+end)/2;
        return Math.min(get(node*2,start,mid, left, right),get(node*2+1,mid+1,end, left, right));
    }

    static long update(int index, long val, int node, int left, int right){
        if(index < left || index > right) return seg[node];
        if(left == right){
            return seg[node] = val;
        }
        int mid = (left+right)/2;
        return seg[node] = Math.min(update(index, val, node*2, left, mid), update(index, val, node*2+1, mid+1, right));
    }
}
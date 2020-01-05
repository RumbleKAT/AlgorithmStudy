import java.util.*;
import java.io.*;

class Main{

    static int N,M,K;
    static long [] seg;
    static long [] arr;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(token.nextToken());


        seg = new long [N*4];
        arr = new long [N+1];

        for(int i=1;i<=N;i++){
            token = new StringTokenizer(br.readLine());
            long temp = Long.parseLong(token.nextToken());
            arr[i] = temp;
        }

        init(1,1,N); //N번쨰 인덱스까지 추출

        for(int i =1;i<=M+K;i++){
            token = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(token.nextToken());
            int a = Integer.parseInt(token.nextToken());
            int b = Integer.parseInt(token.nextToken());


            if(order == 1){
                //update
                update(1,a,b,1,N);
            }else{
                //query
                System.out.println(sum(1,a,b,1,N));
            }
        }

    }

    static long init(int node, int left, int right){
        if(left == right){
           return seg[node] = arr[left]; 
        }
        int mid = (left+right)/2;
        return seg[node] = init(node*2,left, mid) + init(node*2+1,mid+1,right);
    }

    static long update(int node, int index, long val, int left, int right){
        if(index < left || index > right) return seg[node];
        if(left == right){
            return seg[node] = val;
        }
        int mid = (left+right)/2;
        return seg[node] = update(node*2,index,val, left, mid) + update(node*2+1,index,val, mid+1, right);
    }

    static long sum(int node, int left, int right, int start, int end){
        if(start > right || end < left) return 0;
        if(left <= start && end <= right) return seg[node];

        int mid = (start+end)/2;
        return sum(node*2,left, right, start,mid) + sum(node*2+1,left, right, mid+1,end);
    }

}
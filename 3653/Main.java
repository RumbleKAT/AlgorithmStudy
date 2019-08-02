import java.util.*;
import java.io.*;

class Main{
    static int TC;
    static int N, M;
    static int [] seg;
    static int [] segIdx;
    static int [] arr;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        TC = Integer.parseInt(token.nextToken());

        for(int t =1;t<=TC;t++){
            seg  = new int [4000004];
            segIdx = new int[4000004];
            arr = new int [400004];

            token = new StringTokenizer(br.readLine());
            N = Integer.parseInt(token.nextToken());
            M = Integer.parseInt(token.nextToken());

            int idx = M+1;
            for(int i = idx;i<=N+M;i++){
                update(i,1, 1, 1, N + M);
                arr[i-M] =i;
            }

            token = new StringTokenizer(br.readLine());
            
            idx = M;
            for(int j = 0;j<M;j++){
                //update로 1씩 
                int a = Integer.parseInt(token.nextToken());
                //System.out.println(arr[a]);
                System.out.print(query(1,1, N + M,1,arr[a]-1) + " ");
                update(arr[a], 0, 1, 1, N + M);
                //맨 앞으로 보냄
                arr[a] = idx--;
                update(arr[a], 1, 1, 1, N + M );
            }
            System.out.println();

        }
    }
    static int init(int node, int left, int right){
        if(left == right){
            segIdx[left] = node;

            return seg[left] = 1;
        }
        int mid = (left+right)/2;
        return seg[node] = init(node*2, left, mid) + init(node*2+1, mid+1, right);
    }

    static int update(int index, int val, int node, int left, int right){
        if(index < left || index > right) return seg[node];
        if(left == right){
            return seg[node] = val;
        }
        int mid = (left+right)/2;
        return seg[node] = update(index, val, node*2, left, mid) + update(index, val, node*2+1, mid+1, right);
    }

    static int query(int node, int start, int end, int left, int right){
        if(start > right || end < left) return 0;
        if(left <= start && end <= right){
            return seg[node];    
        }
        int mid = (start+end)/2;
        return seg[node] = query(node*2, start, mid, left, right) + query(node*2+1, mid+1, end, left, right);
    }
}
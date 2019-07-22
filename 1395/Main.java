import java.util.*;
import java.io.*;

class Main{
    
    static int N,M;
    static int [] seg;
    static int [] lazy;
    static int [] segIdx;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        seg = new int[N*4];
        segIdx = new int [N*4];
        lazy = new int[N*4];

        init(1,1,N);

        for(int i= 1;i<=M;i++){
            token = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(token.nextToken());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());

            if(order == 0){
                update_range(1, 1, N, s, e);
            }else{
                System.out.println(sum(1,N,1,s,e));
            }
        }

    }
    static int init(int node, int left, int right){
        if(left == right){
            return segIdx[left] = node;
        }
        int mid = (left+right)/2;
        return init(node*2, left, mid) + init(node*2+1,mid+1,right);
    }

    static void update_lazy(int node, int start,int end){
        if(lazy[node] == 0) return;//lazy가 0이면 굳이 바꿔줄 필요가 없음
        seg[node] = (end-start+1) - seg[node];
        //leaf 노드가 아닐시
        if(start != end){
            if(lazy[node*2] == 0){
                lazy[node*2] = 1;
            }else{
                lazy[node*2] = 0;
            }

            if(lazy[node*2+1] == 0){
                lazy[node*2+1] = 1;
            }else{
                lazy[node*2+1] = 0;
            }

        }
        lazy[node] = 0;
    }

    static int update_range(int node, int start, int end, int left, int right){
        update_lazy(node, start, end);

        if(start > right || end < left) return seg[node];
        if(left <= start && right >= end){
            if(lazy[node] == 0){
                lazy[node] = 1;
            }else{
                lazy[node] = 0;
            }
            update_lazy(node, start, end);
            return seg[node];
        }
        return seg[node] = update_range(node*2, start, (start+end)/2, left, right) + update_range(node*2+1, (start+end)/2+1, end, left, right);
    }

    static int update(int index, int val, int node, int left, int right){
        if(left > index || index > right) return seg[node];
        if(left == right){
            return seg[node] = val;
        }
        int mid = (left+right)/2;
        return seg[node] = update(index,val,node*2,left, mid) + update(index, val,node*2+1,mid+1,right);
    }

    static long sum(int start, int end, int node, int left, int right){
        update_lazy(node, start, end);
        if(start > right || end < left){            
            return 0;
        }
        if(left <= start && right >= end){
            return seg[node];
        }
        int mid = (start+end)/2;
        return sum(start,mid, node*2,left,right) + sum(mid+1,end, node*2+1,left,right); 
    }
}
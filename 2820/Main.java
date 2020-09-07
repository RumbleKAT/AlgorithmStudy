import java.util.*;
import java.io.*;

class Main{

    static int N,M;
    static long [] seg = new long [2000001];
    static long [] lazy = new long [2000001];
    static ArrayList<Node> [] nodes;
    static long [] arr;
    static boolean [] visited;
    static int [] left;
    static int [] right;
    static int cnt;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        nodes = new ArrayList[N+1];
        for(int i=1;i<=N;i++){
            nodes[i] = new ArrayList<>();
        }

        token = new StringTokenizer(br.readLine());
            
        long s = Long.parseLong(token.nextToken());
        arr = new long[N+1];

        arr[1] = s;
        visited = new boolean [N+1];
        cnt = 0;
        
        for(int i=2;i<=N;i++){
            token = new StringTokenizer(br.readLine());
            s = Long.parseLong(token.nextToken());
            int e = Integer.parseInt(token.nextToken());
        
            arr[i] = s;
            nodes[e].add(new Node(i));
        }

        left = new int[N+1];
        right = new int[N+1];

        dfs(1);
        // init(1,1,N);
        
        for(int i=1;i<=N;i++){
            update(1,(int)arr[i],1,N,left[i],left[i]);
        }

        /*
        for(int i=1;i<=N;i++){
            System.out.println(left[i] + " " + right[i]);
        }*/

        int t = -1;
        for(int i=1;i<=M;i++){
            token = new StringTokenizer(br.readLine());
            char temp = token.nextToken().charAt(0);
            t = Integer.parseInt(token.nextToken());

            if(temp == 'p'){
                int e = Integer.parseInt(token.nextToken());
                if(left[t] == right[t]) continue;
                update(1, e , 1, N , left[t]+1, right[t]);
            }else{
                System.out.println(query(1, 1, N, left[t], left[t]));                
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
    static void update_lazy(int node, int x, int y){
        if(lazy[node] == 0) return;
        seg[node] += lazy[node] * (y-x+1);
        if(x != y){
            lazy[node*2] += lazy[node];
            lazy[node*2+1] += lazy[node];
        }
        lazy[node] = 0;
    }
    static long update(int node, int val, int start, int end, int left, int right){
        update_lazy(node, start, end);
        if(start > right || end < left) return seg[node];
        if(left <= start && end <= right){
            lazy[node] += val;
            update_lazy(node, start, end);
            return seg[node];
        }
        int mid = (start+end)/2;
        return seg[node] = update(node*2, val, start, mid, left, right) + update(node*2+1, val, mid+1, end, left, right);
    }

    static long query(int node, int start, int end, int left, int right){
        update_lazy(node, start, end);
        if(start > right || end < left) return 0;
        if(left <= start && end <= right) return seg[node];
        int mid = (start+end)/2;
        return query(node*2, start, mid, left, right) + query(node*2+1, mid+1, end, left, right);
    }


/*
    부모랑 최대 깊게 들어갈수 있는 노드
*/

    static void dfs(int root){
        left[root] = ++cnt;

        for(Node next : nodes[root]){
            if(left[next.idx] == 0)
            dfs(next.idx);
        }

        right[root] = cnt;
    }
}
class Node{
    public int idx;
    
    Node(int idx){
        this.idx = idx;
    }
}
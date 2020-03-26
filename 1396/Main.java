import java.util.*;
import java.io.*;

class Main{

    static int [][] parent;
    static int [] depth;
    static Node [] arr;
    static LinkedList<NodeComp> [] aList;
    static boolean [] visited;
    static int [] root;
    static int [] size;
    static long [] distance;

    static int N,M;
    static int MAX = 200010;

    public static void main(final String [] args)throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        
        parent = new int [MAX][22];
        depth = new int [MAX];
        arr = new Node[MAX];
        aList = new LinkedList[MAX];
        visited = new boolean[MAX];
        root = new int [MAX];
        size = new int [MAX];
        distance = new long[MAX];

        for(int i=0;i<=N+M;i++){
            depth[i] = -1;
            size[i] = 1;
            root[i] = i;
            aList[i] = new LinkedList<>();
        }
        for(int i=0;i<M;i++){
            token = new StringTokenizer(br.readLine());
            final int s = Integer.parseInt(token.nextToken());
            final int e = Integer.parseInt(token.nextToken());
            final long val = Long.parseLong(token.nextToken());

            arr[i] = (new Node(s,e,val));
        }

        Arrays.sort(arr,0,M, new Comparator<Node>(){
            @Override
            public int compare(Node o1, Node o2) {
                return (int)(o1.dist - o2.dist);
            }
        });

        for(int i =N+1;i<=N+M;i++){
            int s = arr[i-N-1].s;
            int e = arr[i-N-1].e;
            
            int rootA = find(s);
            int rootB = find(e);

            if(rootA == rootB) continue;
            size[i] = size[rootA] + size[rootB];
            distance[i] = arr[i-N-1].dist;
            
            aList[e].add(new NodeComp(s));
            aList[s].add(new NodeComp(e));

            union(i,s);
            union(i,e);
        }

        for(int i=N+M;i>0;i--){
            if(depth[i] == -1){
                dfs(i, 0);
            }
        }

        for(int i =1;i<=N+M;i++){
            System.out.println(depth[i] + " " + size[i]);
        }

        token = new StringTokenizer(br.readLine());
        final int Q = Integer.parseInt(token.nextToken());

        for(int i=1;i<=Q;i++){
            token = new StringTokenizer(br.readLine());
            final int s = Integer.parseInt(token.nextToken());
            final int e = Integer.parseInt(token.nextToken());

            int rootA = find(s);
            int rootB = find(e);

            if(rootA != rootB){
                System.out.println("-1"); 
                continue;
            }

            final int ans = lca(s, e);

            System.out.println(distance[ans] + " "+ size[ans]);
        }

    }
    
    static int find(final int x){
        if(root[x] == x) return x;
        else return root[x] = find(root[x]);
    }

    static void union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);

        if(rootA == rootB) return;
        else root[rootB] = rootA;
    }

    static void dfs(int current, int prev){
       for(NodeComp node : aList[current]){
           if(node.idx == prev) continue;
           parent[node.idx][0] = current;
           depth[node.idx] = depth[current]+1;
           dfs(node.idx, current);
       }
    }

    static void setParent(){
        for(int j =1;j<=21;j++){
            for(int i=1;i<=N+M;i++){
                parent[i][j] = parent[parent[i][j-1]][j-1];
            }
        }
    }

    static int lca(int a, int b){
        if(depth[a] > depth[b]){
            final int temp = a;
            a = b;
            b = temp;
        }

        for(int i=21;i>=0;i--){
            if(depth[b] - depth[a] >= (1<<i)){
                b = parent[b][i];
            }
        }

        if(a == b) return a;

        for(int i=21;i>=0;i--){
            if(parent[a][i] != parent[b][i]){
                a = parent[a][i];
                b = parent[b][i];
            }
        }

        return parent[a][0];
    }
}
class Node{
    public int s;
    public int e;
    public long dist;

    Node( int s,  int e,  long dist){
        this.s = s;
        this.e = e;
        this.dist = dist;
    }

}
class NodeComp{
    public int idx;

    NodeComp(int idx){
        this.idx = idx;
    }
}
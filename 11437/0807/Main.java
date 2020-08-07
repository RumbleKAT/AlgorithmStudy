import java.util.*;
import java.io.*;

public class Main {

    static int [][] dp;
    static ArrayList<Integer> [] arr;
    static boolean [] visited;
    static int [][] parent;
    static int N,Q;
    static int [] depth;

    public static void main(String [] args) throws Exception{
        // System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());

        visited = new boolean[N+1];
        parent = new int[N+1][22];
        arr = new ArrayList[N+1];
        depth = new int [N+1];

        for(int i=1;i<=N;i++){
            arr[i] = new ArrayList<>();
        }

        for(int i=1;i<N;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());

            arr[s].add(e);
            arr[e].add(s);
        }

        //dfs로 자기 위에 있는 부모 체크
        bfs(1,0);

        for(int j=1;j<=21;j++){
            for(int i=1;i<=N;i++){
                parent[i][j] = parent[parent[i][j-1]][j-1];
            }
        }

        token = new StringTokenizer(br.readLine());
        Q = Integer.parseInt(token.nextToken());

        for(int i=1;i<=Q;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());
            int res = lca(s, e);
            System.out.println(res);
        }
        // System.out.println(Arrays.toString(depth));
    }
    static int lca(int a,int b){
        if(depth[a] > depth[b]){
            int temp = a;
            a = b;
            b = temp;
        }

        for(int j=21;j>=0;j--){
            if(depth[b]-depth[a] >= (1<<j)){
                b = parent[b][j];
            }
        }

        if(a == b) return a;

        for(int j=21;j>=0;j--){
            if(parent[a][j] != parent[b][j]){
                a = parent[a][j];
                b = parent[b][j];
            }
        }

        return parent[a][0];
    }
    static void bfs(int n, int d){        
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(n,d));
        visited[n] = true;

        while(!que.isEmpty()){
            Node cur = que.poll();

            for(int next : arr[cur.idx]){
                if(!visited[next]){
                    visited[next] = true;
                    depth[next] = cur.depth;
                    parent[next][0] = cur.idx;
                    que.add(new Node(next,cur.depth+1));
                }
            }
        }
    }    
}
class Node{
    public int idx;
    public int depth;

    Node(int idx, int depth){
        this.idx = idx;
        this.depth = depth;
    }
}
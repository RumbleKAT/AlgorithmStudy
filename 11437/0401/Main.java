import java.util.*;
import java.io.*;

class Main{

    static int N;
    static int M;

    static int [][] dp;
    static int [] depth;
    static boolean [] visited;
    static LinkedList <Node> [] nodes;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());

        dp = new int[N+1][22];
        depth = new int[N+1];
        visited = new boolean [N+1];
        nodes = new LinkedList[N+1];

        for(int i=1;i<=N;i++){
            nodes[i] = new LinkedList<>();
        }

        for(int i=1;i<N;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());
            nodes[s].add(new Node(e));
            nodes[e].add(new Node(s));

        }

        bfs(1);
        setParent();

        token = new StringTokenizer(br.readLine());
        M = Integer.parseInt(token.nextToken());

        for(int i=1;i<=M;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());

            System.out.println(lca(s, e));
        }

    }
    static void setParent(){
        for(int j=1;j<=21;j++){
            for(int i=1;i<=N;i++){
                dp[i][j] = dp[dp[i][j-1]][j-1];
            }
        }
    }

    static int lca(int a, int b){
        if(depth[a] > depth[b]){
            int temp = a;
            a = b;
            b = temp;
        }

        for(int j=21;j>=0;j--){
            if(depth[b] - depth[a] >= (1<<j)){
                b = dp[b][j];
            }
        }

        if(a == b) return a;
        
        for(int j=21;j>=0;j--){
            if(dp[a][j] != dp[b][j]){
                a = dp[a][j];
                b = dp[b][j];
            }
        } 

        return dp[a][0];
    }

    static void bfs(int idx){
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(idx,0));
        visited[idx] = true;

        while(!que.isEmpty()){
            Node current = que.poll();

            depth[current.idx] = current.depth;

            for(Node node : nodes[current.idx]){
                if(!visited[node.idx]){
                    visited[current.idx] = true;
                    dp[node.idx][0] = current.idx;
                    que.add(new Node(node.idx, depth[current.idx] + 1));
                }
            }
        }
    }

}
class Node{
    public int idx;
    public int depth;

    Node(int idx){
        this.idx = idx;
    }

    Node(int idx, int depth){
        this.idx = idx;
        this.depth = depth;
    }
}
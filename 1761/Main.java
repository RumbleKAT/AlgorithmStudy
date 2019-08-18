import java.util.*;
import java.io.*;

class Main{

    static int [][] dp;
    static int [] depth;
    static int [] dist;
    static int MAX = 21;
    static int MAX_N = 40004;
    static int N,M;
    static LinkedList <Node> [] arr;
    static boolean [] visited;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());

        arr = new LinkedList [N+1];
        for(int i =0;i<arr.length;i++){
            arr[i] = new LinkedList<>();
        }
        visited = new boolean[N+1];
        depth = new int [N+1];
        dp = new int[N+1][21];
        dist = new int [N+1];

        for(int i =1;i<=N-1;i++){
            token = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(token.nextToken());
            int b = Integer.parseInt(token.nextToken());
            int val = Integer.parseInt(token.nextToken());

            arr[a].add(new Node(b,val));
            arr[b].add(new Node(a,val));
        }

        dfs(1, 0, 0);
        setParent();
        
        token = new StringTokenizer(br.readLine());
        M = Integer.parseInt(token.nextToken());

        for(int i =1;i<=M;i++){
            token = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());

            int result = dist[s] + dist[e] - (dist[lca(s,e)]*2);
            System.out.println(result);
            
        }

    }

    static void dfs(int n, int d, int distance){
        visited[n] = true;
        depth[n] = d;
        dist[n] = distance;

        for(Node current : arr[n]){
            if(visited[current.next]) continue;
            dp[current.next][0] = n;
            dfs(current.next,d+1,distance+current.val);
        }
    }

    static void setParent(){
        for(int j =1;j<20;j++){
            for(int i=1;i<=N;i++){
                dp[i][j] = dp[dp[i][j-1]][j-1];
            }
        }
    }

    static int lca(int a, int b){
        if(depth[a] > depth[b]){
            int tmp = a;
            a = b;
            b = tmp;
        }

        // System.out.println(depth[a] + " " + depth[b]);

        for(int i=20;i>=0;i--){
            if(depth[b]-depth[a]>=(1<<i)){
                b = dp[b][i];
            }
        }
        if(a ==b ) return a;

        for(int i =20;i>=0;i--){
            if(dp[a][i]!= dp[b][i]){
                a = dp[a][i];
                b = dp[b][i];
            }
        }

        return dp[a][0];
    }

}
class Node{
    public int next;
    public int val;

    Node(int next, int val){
        this.next = next;
        this.val = val;
    }
}
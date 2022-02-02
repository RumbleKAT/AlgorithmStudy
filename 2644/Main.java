import java.util.*;
import java.io.*;

public class Main {

    static ArrayList<Node> [] nodes;
    static boolean [] visited;
    static int N;
    static int M;
    static int S;
    static int E;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        token = new StringTokenizer(br.readLine());

        S = Integer.parseInt(token.nextToken());
        E = Integer.parseInt(token.nextToken());
        ans = -1;
        token = new StringTokenizer(br.readLine());
        M = Integer.parseInt(token.nextToken());

        nodes = new ArrayList[N+1];
        for(int i=1;i<=N;i++){
            nodes[i] = new ArrayList<>();
        }
        visited = new boolean[N+1];

        for(int i=1;i<=M;i++){
            token = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(token.nextToken());
            int to = Integer.parseInt(token.nextToken());
            nodes[from].add(new Node(to,0));
            nodes[to].add(new Node(from,0));
        }

        dfs(S,0,E);

        System.out.println(ans);

    }
    static void dfs(int node, int dist, int dest){
        if(visited[node]) return;
        visited[node] = true;

        if(node == dest){
            ans = dist;
            return;
        }

        for(Node n : nodes[node]){
            if(!visited[n.idx]){
                dfs(n.idx,dist+1,dest);
            }
        }
    }
}
class Node{
    public int idx;
    public int dist;

    Node(int idx, int dist){
        this.idx = idx;
        this.dist = dist;
    }
}

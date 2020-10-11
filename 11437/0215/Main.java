import java.util.*;
import java.io.*;

class Main{

    static int MAX = 50001;
    static ArrayList<Node> [] arr;
    static int [][] parent;
    static boolean [] visited;
    static int [] depth;
    static int N;
    static int Q;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());

        depth = new int [MAX];
        parent = new int[MAX][22];
        visited = new boolean [MAX];
        arr = new ArrayList[MAX];
        for(int i =1;i<=N;i++){
            arr[i] = new ArrayList<>();
        }

        for(int i =1;i<N;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());

            arr[s].add(new Node(e));
            arr[e].add(new Node(s));
        }

        depth[1] = 0;
        visited[1] = true;
        dfs(1,0);
        setParent();

        token = new StringTokenizer(br.readLine());
        Q = Integer.parseInt(token.nextToken());

        for(int i=1;i<=Q;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());

            bw.write(lca(s,e)+"\n");
        }
        bw.flush();

    }
    static void dfs(int current, int d){   
        depth[current] = d;
        
        for(Node node : arr[current]){
            if(!visited[node.next]){
                visited[node.next] = true;
                parent[node.next][0] = current; 
                dfs(node.next, d+1);
            }
        }
    }
    static void setParent(){
        for(int j=1;j<=21;j++){
            for(int i=1;i<=N;i++){
                parent[i][j] = parent[parent[i][j-1]][j-1];
            }
        }
    }
    static int lca(int a, int b){
        if(depth[a] > depth[b]){
            int temp  = a;
            a = b;
            b = temp;
        }

        for(int i =21;i>=0;i--){
            if(depth[b] -depth[a] >= (1<<i)){
                b = parent[b][i];
            }
        }

        if(a == b) return a;

        for(int i =21;i>=0;i--){
            if(parent[a][i] != parent[b][i]){
                a = parent[a][i];
                b = parent[b][i];
            }
        }
        return parent[a][0];
    }
}

class Node{
    public int next;

    Node(int next){
        this.next = next;
    }
}   
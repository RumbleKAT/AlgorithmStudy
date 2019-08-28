import java.io.*;
import java.util.*;

class Main{

    static int N;
    static int M;
    static int MAX = 50001;
    static int [][] parent = new int [MAX][22];
    static int [] depth = new int [MAX];
    static ArrayList <Node>[] arr = new ArrayList[MAX];
    static boolean [] visited = new boolean[MAX];

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());

        for(int i=1;i<=N;i++){
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
        M = Integer.parseInt(token.nextToken());
        
        for(int i =1;i<=M;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());

            System.out.println(lca(s,e));
        }
    }
    static void dfs(int current, int d){
        depth[current] = d;

        for(Node nextNode : arr[current]){
            if(!visited[nextNode.nextNode]){
                visited[nextNode.nextNode] = true;
                parent[nextNode.nextNode][0] = current;
                dfs(nextNode.nextNode, d+1);
            }
        }
    }

    static void setParent(){
        for(int j =1;j<=21;j++){
            for(int i=1;i<=N;i++){
                parent[i][j] = parent[parent[i][j-1]][j-1];
            }
        }
    }

    static int lca(int a, int b){
        if(depth[a] > depth[b]){
            int tmp = a;
            a = b;
            b = tmp;
        }

        for(int i=21;i>=0;i--){
            if(depth[b]-depth[a]>=(1<<i)){
                b = parent[b][i];
            }
        }

        if(a == b) return a;

        for(int i=21;i>=0;i--){
            if(parent[a][i]!= parent[b][i]){
                a = parent[a][i];
                b = parent[b][i];
            }
        }
        return parent[a][0];
    }
}
class Node{
    public int nextNode;

    Node(int nextNode){
        this.nextNode = nextNode;
    }
}
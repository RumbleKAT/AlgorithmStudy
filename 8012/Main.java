import java.util.*;
import java.io.*;


class Main{
    static int N;
    static ArrayList <Node> [] arr;
    static int [] depth;
    static int [] dist;
    static int [][] parent;
    static boolean [] visited;
    static int M;
    
    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token;

        N = Integer.parseInt(br.readLine());

        arr = new ArrayList[N+1];
        parent = new int [N+1][21];
        depth = new int [N+1];
        visited = new boolean [N+1];
        dist = new int [N+1];

        for(int i = 0;i<arr.length;i++){
            arr[i] = new ArrayList<>();
        }

        for(int i= 1;i<N;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());

            arr[s].add(new Node(e,1));
            arr[e].add(new Node(s,1));
        }

        depth[1] = -1;
        dfs(1,0,0);

        //System.out.println(Arrays.toString(dist));

        setParent();

        token = new StringTokenizer(br.readLine());
        M = Integer.parseInt(token.nextToken());

        long result = 0;

        token = new StringTokenizer(br.readLine());
        int current = Integer.parseInt(token.nextToken());

        for(int i = 2;i<=M;i++){
            token = new StringTokenizer(br.readLine());
            int b = Integer.parseInt(token.nextToken());
            //System.out.println("current : " + current + " " + b);

            //System.out.println(lca(current,b));
            long curdist = depth[current] + depth[b] - (depth[lca(current,b)]*2);
            //System.out.println("dist : " + curdist + " " + current+ " " + b);
            result += curdist;
            current = b;

        }

        System.out.println(result);

    }

    static void dfs(int current, int d, int distance){
        visited[current] = true;
        depth[current] = d;
        dist[current] = distance;

        for(Node nextNode : arr[current]){
            if(visited[nextNode.e]) continue;
            parent[nextNode.e][0] = current; 
            dfs(nextNode.e,d+1, distance + nextNode.val);
        }
    }

    static void setParent(){
        for(int j = 1;j<=20;j++){
            for(int i=1;i<=N;i++){
                parent[i][j] = parent[parent[i][j-1]][j-1];
            }
        }
    }
    static int lca(int a, int b){
        if(depth[a] < depth[b]){
            int tmp = a;
            a = b;
            b = tmp;
        }

        for(int i = 20;i>=0;i--){
            if(depth[b]-depth[a] >= (1<<i)){
                b = parent[b][i];
            }
        }

        if(a == b) return a;

        for(int i = 20;i>=0;i--){
            if(parent[a][i] != parent[b][i]){
                a = parent[a][i];
                b = parent[b][i];
            }
        }

        return parent[a][0];
    }
}
class Node{
    public int e;
    public int val;

    Node(int e, int val){
        this.e = e;
        this.val = val;
    }
}
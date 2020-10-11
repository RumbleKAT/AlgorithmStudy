import java.util.*;
import java.io.*;

class Main{
    static int [][] parent;
    static boolean [] visited;
    static ArrayList<Node> [] arr;
    static int [] depth;
    static int [] dist;
    static int N,M;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        parent = new int[N+1][22];
        visited = new boolean[N+1];
        depth = new int [N+1];
        dist = new int [N+1];

        arr = new ArrayList[N+1];
        for(int i=1;i<=N;i++){
            arr[i] = new ArrayList<>();
        }

        for(int i=1;i<N;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());
            int val = Integer.parseInt(token.nextToken());

            arr[s].add(new Node(e,val,0));
            arr[e].add(new Node(s,val,0));
        }
        bfs(1);
        setParent();
        //안녕하세요 이문제에서 갔던 곳을 다시 갈수 있기 때문에 visited 배열은 사실 이 문제에선 아무런 의미를 가지지 않습니다. 무시하셔도 됩니다.
        
        
        token = new StringTokenizer(br.readLine());
        
        M = Integer.parseInt(token.nextToken());

        for(int i=1;i<=M;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());

            System.out.println((dist[s] + dist[e]- (dist[lca(s,e)])*2));
        }
        

    }
    static void bfs(int n){
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(n,0,0));
        
        while(!que.isEmpty()){
            Node cur = que.poll();

            visited[cur.idx] = true;
            depth[cur.idx] = cur.val;
            dist[cur.idx] = cur.dist;

            for(Node next : arr[cur.idx]){
                if(!visited[next.idx]){
                    parent[next.idx][0] = cur.idx;
                    que.add(new Node(next.idx,cur.val+1,cur.dist + next.val));
                }
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
        if(depth[a] < depth[b]){
            int temp = a; 
            a = b;
            b = temp;
        }

        for(int i=21;i>=0;i--){
            if(depth[a] - depth[b] >= (1<<i)){
                a = parent[a][i];
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
    public int idx;
    public int val;
    public int dist;

    Node(int idx, int val, int dist){
        this.idx = idx;
        this.val = val;
        this.dist = dist;
    }
}
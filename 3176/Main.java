import java.util.*;
import java.io.*;

public class Main {
    
    static int [] depth;
    static int [][] parent;
    static int [][] minParent;
    static int [][] maxParent;
    static ArrayList<Node> [] nodes;
    static boolean [] visited;
    static int N,K;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        depth = new int[N+1];
        parent = new int[N+1][22];
        minParent = new int[N+1][22];
        maxParent = new int[N+1][22];

        nodes = new ArrayList[N+1];
        for(int i=1;i<=N;i++){
            nodes[i] = new ArrayList<>();
        }
        visited=  new boolean[N+1];

        for(int i=1;i<N;i++){
            token= new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());
            int val = Integer.parseInt(token.nextToken());

            nodes[s].add(new Node(e,val));
            nodes[e].add(new Node(s,val));
        }

        //bfs 
        bfs(1);
        setParent();

        token = new StringTokenizer(br.readLine());
        K = Integer.parseInt(token.nextToken());

        for(int i=1;i<=K;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());
        
            Node ans = lca(s, e);
            System.out.println(ans.idx + " " + ans.val);
        }


    }
    static void bfs(int start){
        Queue <Node> que = new LinkedList<>();
        que.add(new Node(start,0));

        while(!que.isEmpty()){
            Node cur = que.poll();
            if(visited[cur.idx]) continue;
            visited[cur.idx] = true;

            depth[cur.idx] = cur.val;

            for(Node next : nodes[cur.idx]){
                if(!visited[next.idx]){
                    parent[next.idx][0] = cur.idx;
                    minParent[next.idx][0] = next.val;
                    maxParent[next.idx][0] = next.val;
                    que.add(new Node(next.idx,cur.val+1));
                }
            }
        }
    }
    static void setParent(){
        for(int j=1;j<=21;j++){
           for(int i=1;i<=N;i++){
               parent[i][j] = parent[parent[i][j-1]][j-1];
               minParent[i][j] = Math.min(minParent[i][j-1], minParent[parent[i][j-1]][j-1]);
               maxParent[i][j] = Math.max(maxParent[i][j-1], maxParent[parent[i][j-1]][j-1]);
           } 
        }
    }
    static Node lca(int a, int b){
        int tmpMax = Integer.MIN_VALUE;
        int tmpMin = Integer.MAX_VALUE;
        
        if(depth[a] > depth[b]){
            int temp = a;
            a = b;
            b = temp;
        }

        for(int j=21;j>=0;j--){
            if(depth[b] - depth[a] >= (1<<j)){
                tmpMax = Math.max(tmpMax, maxParent[b][j]);
                tmpMin = Math.min(tmpMin, minParent[b][j]);
                b = parent[b][j];
            }
        }

        if(a == b) return new Node(tmpMin, tmpMax);

        for(int j=21;j>=0;j--){
            if(parent[a][j] != parent[b][j]){
                tmpMax = Math.max(tmpMax, Math.max(maxParent[b][j],maxParent[a][j]));
                tmpMin = Math.min(tmpMin, Math.min(minParent[b][j],minParent[a][j]));
                a = parent[a][j];
                b = parent[b][j];
            }
        }

        tmpMax = Math.max(tmpMax, Math.max(maxParent[b][0],maxParent[a][0]));
        tmpMin = Math.min(tmpMin, Math.min(minParent[b][0],minParent[a][0]));

        return new Node(tmpMin, tmpMax);
    }
}
class Node{
    public int idx;
    public int val;

    Node(int idx, int val){
        this.idx = idx;
        this.val = val;
    }
}

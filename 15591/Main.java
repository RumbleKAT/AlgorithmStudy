import java.util.*;
import java.io.*;

class Main{
    static int N;
    static int Q;
    static ArrayList<Node> [] nodes;
    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        Q = Integer.parseInt(token.nextToken());
        nodes = new ArrayList [N+1];
        for(int i=1;i<=N;i++){
            nodes[i] = new ArrayList<>();
        }
        for(int i=1;i<=N-1;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());
            int val = Integer.parseInt(token.nextToken());

            nodes[s].add(new Node(e,val));
            nodes[e].add(new Node(s,val));
        }
      
        for(int i=1;i<=Q;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());
            System.out.println(getDistance(s,e));
        }

    }
    static int getDistance(int K, int node){
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(node,0));
        boolean [] visited = new boolean[N+1];
        
        int cnt = 0;
        while(!q.isEmpty()){
            Node cur = q.poll();
            int index = cur.idx;
            visited[index] = true;
            for(Node n: nodes[index]){
                if(!visited[n.idx] && n.val >= K){
                    cnt++;            
                    q.add(new Node(n.idx, cur.val + n.val));
                }
            }
        }

        return cnt;
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
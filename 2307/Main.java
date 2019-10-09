import java.util.*;
import java.io.*;

class Main{

    static int N,M;
    static LinkedList<Node> [] arr;
    static PriorityQueue<Node> pq;
    static long [] adj;
    static int [] parent;
    static boolean [] check;
    static int MAX = 987654321;
    static long [] distA;
    static int [] parentA;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        check = new boolean[N+1];
        arr = new LinkedList[N+1];

        adj = new long[N+1];
        parent = new int[N+1];

        for(int i=1;i<=N;i++){
            arr[i] = new LinkedList<>();
        }

        for(int i=1;i<=M;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());
            long val = Long.parseLong(token.nextToken());

            arr[s].add(new Node(e,val));
            arr[e].add(new Node(s,val));
        }

        pq = new PriorityQueue<>();
        
        Node first = new Node(1,0);
        pq.add(new Node(1,0));
        
        Arrays.fill(parent,-1);
        Arrays.fill(adj, MAX);
        adj[1] = 0;
        
        while(!pq.isEmpty()){
            Node current = pq.poll();

            for(Node node : arr[current.idx]){
                if(adj[node.idx] > current.val + node.val){
                    adj[node.idx] = current.val + node.val;

                    parent[node.idx] = current.idx;

                    pq.add(new Node(node.idx,adj[node.idx]));                    
                }
            }
        }
        
        long min = adj[N];
        long ans = 0;

        for (int i = N;;i = parent[i]) {
            ans = Math.max(ans, dijkstra(parent[i], i));
            if (parent[i] == 1) break;
        }
    
        if(ans == MAX){
            bw.write("-1\n");
        }else{
            bw.write((ans-min)+"\n");
        }
        bw.flush();        

    }
    static long dijkstra(int u, int v){ 
        long [] distA = new long [N+1];
        
        Arrays.fill(distA,MAX);

        distA[1] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1,0));

        while(!pq.isEmpty()){
            Node current = pq.poll();
            for(Node nextNode : arr[current.idx]){
                if(nextNode.idx == u && current.idx == v) continue;
                if(nextNode.idx == v && current.idx == u) continue;

                if(distA[nextNode.idx] > nextNode.val + current.val){
                    distA[nextNode.idx] = nextNode.val + current.val;
                    pq.add(new Node(nextNode.idx,distA[nextNode.idx]));
                }
            }
        }
        return distA[N];
    }
}
class Node implements Comparable<Node>{
    public int idx;
    public long val;
    public int step = 0;

    Node(int idx, long val){
        this.idx = idx;
        this.val = val;
    }

    @Override
    public int compareTo(Node n1){
        return (int)(this.val - n1.val);
    }
}
class Step{
    public int idx;
    public int step;

    Step(int idx, int step){
        this.idx = idx;
        this.step = step;
    }
}
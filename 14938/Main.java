import java.util.*;
import java.io.*;

class Main{

    static int n,m,r;
    static int [] capacity;
    static PriorityQueue<Node> pq;
    static LinkedList<Node>[] nodes;
    static int [] dist;
    static int res;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        n = Integer.parseInt(token.nextToken());
        m = Integer.parseInt(token.nextToken());
        r = Integer.parseInt(token.nextToken());

        res = 0;
        capacity = new int[n+1];
        dist = new int[n+1];
        token = new StringTokenizer(br.readLine());
        for(int i=1;i<=m;i++){
            capacity[i] = Integer.parseInt(token.nextToken());
        }

        pq = new PriorityQueue<>();
        nodes = new LinkedList[n+1];
        for(int i=1;i<=n;i++){
            nodes[i] = new LinkedList<>();
        }
        
        for(int i=1;i<=r;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());
            int d = Integer.parseInt(token.nextToken());

            nodes[s].add(new Node(e,d));
            nodes[e].add(new Node(s,d));
        }
        
        for(int i =1;i<=n;i++){
            dijkstra(i);
        }
        System.out.println(res);
    
    }
    static void dijkstra(int node){
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[node] = 0;

        pq.add(new Node(node, dist[node]));

        while(!pq.isEmpty()){
            Node current = pq.poll();
            
            if(current.d > dist[current.e]) continue;

            for(Node nextNode : nodes[current.e]){
                if(dist[nextNode.e] > dist[current.e] + nextNode.d && m >= dist[current.e] + nextNode.d){
                    dist[nextNode.e] = dist[current.e] + nextNode.d;
                    pq.add(new Node(nextNode.e, dist[nextNode.e]));
                }
            }
        }
        int sum = 0;
        for(int i=1;i<=n;i++){
            if(dist[i] != Integer.MAX_VALUE){
                sum += capacity[i];
            }
        }
        res = Math.max(res, sum);
    }
}
class Node implements Comparable<Node>{
    public int e;
    public int d;

    Node(int e, int d){
        this.e = e;
        this.d = d;
    }

    @Override
    public int compareTo(Node o) {
        return this.d - o.d;
    }
}
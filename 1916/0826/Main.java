import java.io.*;
import java.util.*;

class Main{

    static int N,M;
    static LinkedList<Node>[] arr;
    static PriorityQueue<Node> pq;
    static long [] adj;
    static long MAX = 987654321;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        token = new StringTokenizer(br.readLine());
        M = Integer.parseInt(token.nextToken());

        adj = new long[N+1];
        arr = new LinkedList[N+1];
        pq = new PriorityQueue<>();
        for(int i =1;i<=N;i++){
            arr[i] = new LinkedList<>();
        }

        for(int i=1;i<=M;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());
            long val = Long.parseLong(token.nextToken());

            arr[s].add(new Node(e,val));
        }

        for(int i=1;i<=N;i++){
            adj[i] = MAX;
        }

        token = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(token.nextToken());
        int e = Integer.parseInt(token.nextToken());

        adj[s] = 0;
        pq.add(new Node(s,0));

        while(!pq.isEmpty()){
            Node current = pq.poll();
            
            
            for(Node node : arr[current.idx]){
                if(adj[node.idx] != MAX){
                    if(adj[node.idx] > current.val + node.val){
                        adj[node.idx] = current.val +node.val;
                        pq.add(new Node(node.idx,adj[node.idx]));
                    }
                }
            }

        }

        System.out.println(adj[e]);
    
    }
}
class Node implements Comparable<Node>{
    public int idx;
    public long val;

    Node(int idx, long val){
        this.idx = idx;
        this.val = val;
    }

    @Override
    public int compareTo(Node n1){
        return (int)(this.val - n1.val);
    }
}
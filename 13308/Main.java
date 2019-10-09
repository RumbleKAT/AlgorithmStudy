import java.util.*;
import java.io.*;

class Main{

    static int N,M;
    static LinkedList<Node> [] arr;
    static int [] city;
    static int [][] adj;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        
        arr = new LinkedList[N+1];
        adj = new int[2551][2551];
        city = new int [2551];
    
        token = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++){
            city[i] = Integer.parseInt(token.nextToken());
            arr[i] = new LinkedList<>();
        }


        for(int i =1;i<=M;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());
            int val = Integer.parseInt(token.nextToken());

            arr[s].add(new Node(e,val));
            arr[e].add(new Node(s,val));
        }

        PriorityQueue<NodeComp> pq = new PriorityQueue<>();

        pq.add(new NodeComp(1,city[1],0));

        while(!pq.isEmpty()){
            NodeComp current = pq.poll();
            int cost = current.cost;
            int idx = current.idx;
            long dist = current.val;

            if(adj[idx][cost] != 0) continue;
            if(idx == N){
                // System.out.println("!!");
                System.out.println(dist);
                break;
            }
            System.out.println("idx : " + idx);//모든 거리를 파악한다.
            adj[idx][cost] = 1;

            for(Node node : arr[idx]){
                pq.add(new NodeComp(node.idx,Math.min(cost, city[node.idx]),cost*node.val + dist));
            }
        }

    }
}
class Node implements Comparable<Node>{
    public int idx;
    public int val;

    Node(int idx, int val){
        this.idx = idx;
        this.val = val;
    }

    @Override
    public int compareTo(Node n1){
        return (int)(this.val - n1.val);
    }
}

class NodeComp implements Comparable<NodeComp>{
    public int idx;
    public int cost;
    public long val;

    NodeComp(int idx, int cost, long val){
        this.idx = idx;
        this.cost = cost;
        this.val = val;
    }

    @Override
    public int compareTo(NodeComp nc){
        return (int)(this.val - nc.val);
    }
}
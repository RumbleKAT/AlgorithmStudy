import java.util.*;
import java.io.*;

class Main{

    static int N,M;
    static LinkedList<Node> [] arr;
    static PriorityQueue<NodeComp> pq;
    static long [] adj;
    static int [] past;
    static long MAX = Integer.MAX_VALUE;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        arr = new LinkedList[N+1];
        for(int i=1;i<=N;i++){
            arr[i] = new LinkedList<>();
        }
        past = new int[N+1];
        adj = new long[N+1];

        Arrays.fill(adj, MAX);
        Arrays.fill(past,-1);

        for(int i=1;i<=M;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());
            long val = Long.parseLong(token.nextToken());

            arr[s].add(new Node(e,val));
            arr[e].add(new Node(s,val));
        }


        adj[1] = 0;

        pq = new PriorityQueue<>();
        pq.add(new NodeComp(1,0));

        while(!pq.isEmpty()){
            NodeComp current = pq.poll();
            // System.out.println(current.idx);
           if(current.val > adj[current.idx]) continue;

           for(Node node : arr[current.idx]){
                // if(current.val == 0) continue;
                if(adj[node.idx] > current.val + node.val){
                    adj[node.idx] = current.val + node.val;
                    past[node.idx] = current.idx;
                    pq.add(new NodeComp(node.idx,adj[node.idx]));
                }
            }
        }

        int cnt = 0;
        for(int i=1;i<past.length;i++){
            if(past[i]!=-1) cnt++;
        }

        System.out.println(cnt);

        for(int i=1;i<=N;i++){
            if(past[i]!=-1){
                System.out.println(past[i] + " " + i);
            }
        }

    }
}
class Node {
    public int idx;
    public long val;

    Node(int idx, long val){
        this.idx = idx;
        this.val = val;
    }
}
class NodeComp implements Comparable<NodeComp>{
    public int idx;
    public long val;

    NodeComp(int idx, long val){
        this.idx = idx;
        this.val = val;
    }

    @Override
    public int compareTo(NodeComp n1){
        return (int)(this.val - n1.val);
    }
}
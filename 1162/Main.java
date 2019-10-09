import java.util.*;
import java.io.*;

class Main{
    static int N,M,K;
    static LinkedList <Comp> [] arr;
    static long [][] adj;
    static long MAX = Long.MAX_VALUE;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(token.nextToken());

        arr = new LinkedList[N+1];
        adj = new long [K+1][N+1];
        for(int i=1;i<=N;i++){
            arr[i] = new LinkedList<>();
        }

        for(int i =0;i<=K;i++){
            for(int j =1;j<=N;j++){
                adj[i][j] = MAX;
            }
        }

        for(int i =1;i<=M;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());
            long val = Long.parseLong(token.nextToken());

            arr[s].add(new Comp(e,val));
            arr[e].add(new Comp(s,val));
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<>();

        adj[0][1] = 0;
        pq.add(new Node(1,0,0));

        while(!pq.isEmpty()){
            Node current = pq.poll();
            int step = current.k;
            // System.out.println("current : " + currentK);

            if(adj[step][current.idx] < current.val) continue;

            for(Comp node : arr[current.idx]){
                // long cost = node.val + current.val;

                if(step+1 <=K && adj[step+1][node.idx] > adj[step][current.idx]){
                    adj[step+1][node.idx] = adj[step][current.idx];
                    pq.add(new Node(node.idx,adj[step+1][node.idx],step+1));
                }

                if(adj[step][node.idx] > node.val + adj[step][current.idx]){
                    adj[step][node.idx] = node.val + adj[step][current.idx];
                    pq.add(new Node(node.idx,adj[step][node.idx],step));
                }

            }
        }

        long ans = MAX;

        for (int i = 0; i <= K; i++) {
           ans = ans > adj[i][N] ? adj[i][N] : ans;
        }
        System.out.println(ans);
    }
}
class Comp {
    public int idx;
    public long val;

    Comp(int idx, long val){
        this.idx = idx;
        this.val = val;
    }
}

class Node implements Comparable<Node>{
    public int idx;
    public int k;
    public long val;

    Node(int idx, long val,int k){
        this.idx = idx;
        this.val = val;
        this.k = k;

    }

    @Override
    public int compareTo(Node n1){
        return (int)(this.val - n1.val);
    }
}
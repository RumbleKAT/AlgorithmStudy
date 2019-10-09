import java.io.*;
import java.util.*;

class Main{

    static int N,M;
    static LinkedList<Node>[] arr;
    static int MAX = 987654321;
    static int [] parent;
    static int [] adj;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        adj = new int [N+1];
        parent = new int [N+1];
        Arrays.fill(parent,-1);
        arr = new LinkedList[N+1];
        for(int i=1;i<=N;i++){
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

        dijkstra_0();
        long min = adj[N];

        long ans = 0;
        int i = N;

        // while(true){
        //     if(parent[i] == 1) break;
        //     int temp = dijkstra(parent[i], i);
        //     if(temp == -1){
        //         ans = -1;
        //         break;
        //     }else{
        //         ans = Math.max(ans, temp-min);
        //     }
        //     i = parent[i];
        // }

        for(int p=N;p!=parent[p];p=parent[p]){
            int temp = dijkstra(p, parent[p]);
            if(temp == -1){
                ans = -1;
                break;
            }
            ans = Math.max(ans,temp);
        }            

        bw.write(ans+"\n");
        bw.flush();

        
    }
    static int dijkstra(int u, int v){
        Arrays.fill(adj, MAX);
        
        PriorityQueue<Node>pq = new PriorityQueue<>();
        pq.add(new Node(1,0));
        parent[1]=1;
        adj[1] = 0;

        while(!pq.isEmpty()){
            Node current = pq.poll();

            for(Node next : arr[current.idx]){
                if(next.idx == u && current.idx == v) continue;
                if(current.idx == u && next.idx == v) continue;

                if(adj[next.idx] > next.val + current.val){
                    adj[next.idx] = next.val + current.val;
                    pq.add(new Node(next.idx,adj[next.idx]));
                    
                }
            }
        }

        if(adj[N] > MAX ){
            return -1;
        }else{
            return adj[N];
        }
    }

    static void dijkstra_0(){
        Arrays.fill(adj, MAX);
        
        PriorityQueue<Node>pq = new PriorityQueue<>();
        pq.add(new Node(1,0));
        adj[1] = 0;

        while(!pq.isEmpty()){
            Node current = pq.poll();

            for(Node next : arr[current.idx]){
                if(adj[next.idx] > next.val + current.val){
                    adj[next.idx] = next.val + current.val;
                    pq.add(new Node(next.idx,adj[next.idx]));
                    parent[next.idx] = current.idx;
                }
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
        return (int)(this.val-n1.val);
    }
} 
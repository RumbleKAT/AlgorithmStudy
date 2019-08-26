import java.util.*;
import java.io.*;

class Main{

    static LinkedList <Node> [] arr;
    static PriorityQueue<Node> pq;
    static long [][] adj;
    static int N,E;
    static long ans;
    static int MAX = 987654321;
    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        E = Integer.parseInt(token.nextToken());

        // System.out.println(N + " " + E);

        adj = new long[3][N+1];
        arr = new LinkedList[N+1];
        pq = new PriorityQueue<>();
        for(int i =1;i<=N;i++){
            arr[i] = new LinkedList<>();
        }

        for(int i =1;i<=E;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());
            long val = Long.parseLong(token.nextToken());
            
            arr[s].add(new Node(e,val));
            arr[e].add(new Node(s,val));
        }

        token = new StringTokenizer(br.readLine());

        int s = Integer.parseInt(token.nextToken());
        int e = Integer.parseInt(token.nextToken());

    

        Arrays.fill(adj[0],MAX);

        adj[0][1] = 0;
        pq.add(new Node(1,0));

        while(!pq.isEmpty()){
            Node current = pq.poll();

            for(Node node : arr[current.idx]){
                if(adj[0][node.idx] > node.val + current.val){
                    adj[0][node.idx] = node.val + current.val;
                    //System.out.println("next :  " + node.idx + " dist : " + adj[node.idx]);
                    pq.add(new Node(node.idx, adj[0][node.idx]));
                }                
            }
        }

        Arrays.fill(adj[1],MAX);

        adj[1][s] = 0;
        pq.add(new Node(s,0));

        while(!pq.isEmpty()){
            Node current = pq.poll();

            for(Node node : arr[current.idx]){
                if(adj[1][node.idx] > node.val + current.val){
                    adj[1][node.idx] = node.val + current.val;
                    //System.out.println("next :  " + node.idx + " dist : " + adj[node.idx]);
                    pq.add(new Node(node.idx, adj[1][node.idx]));
                }                
            }
        }

        Arrays.fill(adj[2],MAX);

        adj[2][e] = 0;
        pq.add(new Node(e,0));

        while(!pq.isEmpty()){
            Node current = pq.poll();

            for(Node node : arr[current.idx]){
                if(adj[2][node.idx] > node.val + current.val){
                    adj[2][node.idx] = node.val + current.val;
                    //System.out.println("next :  " + node.idx + " dist : " + adj[node.idx]);
                    pq.add(new Node(node.idx, adj[2][node.idx]));
                }                
            }
        }
        

        long ans1  = adj[0][s] + adj[1][e] + adj[2][N];
        long ans2 = adj[0][e] + adj[2][s] + adj[1][N];

        // System.out.println(ans1 + " "  + ans2);

        // for(int i =1;i<=N;i++){
        //     for(int j=1;j<=N;j++){
        //         System.out.print(adj[i][j] + " ");
        //     }System.out.println();
        // }
        long ans = Math.min(ans1, ans2);

        if(ans >= MAX){
            bw.write("-1\n");
        }else{
            bw.write(ans+"\n");
        }
        bw.flush();
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
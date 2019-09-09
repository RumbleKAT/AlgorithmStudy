import java.util.*;
import java.io.*;

class Main{

    static long [] adj;
    static LinkedList<Node> [] arr;
    static int MAX = 987654321;
    static int [] parent;
    static int N,M;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        token = new StringTokenizer(br.readLine());
        M = Integer.parseInt(token.nextToken());

        adj = new long [N+1];
        arr = new  LinkedList[N+1];
        for(int i=1;i<=N;i++){
            arr[i] = new LinkedList<>();
        }
        parent = new int[N+1];

        for(int i =1;i<=M;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());
            long val = Long.parseLong(token.nextToken());

            arr[s].add(new Node(e,val));
            // arr[e].add(new Node(s,val));
        }

        token = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(token.nextToken());
        int e = Integer.parseInt(token.nextToken());

        long res = 0;

        res = dijkstra(s, e);

        System.out.println(res);
        Stack<Integer> st = new Stack<>();
        int n = e;
        while(true){

            if(n==s){
                st.push(n);
                break;
            }

            st.push(n);
            n = parent[n];
        }


        System.out.println(st.size());
        while(!st.isEmpty()){
            System.out.print(st.pop() + " ");
        }System.out.println();


    }
    static long dijkstra(int s, int e){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Arrays.fill(adj, MAX);
        adj[s] = 0;
        pq.add(new Node(s,adj[s]));

        while(!pq.isEmpty()){
            Node current = pq.poll();

            for(Node node : arr[current.idx]){
                if(adj[node.idx] > current.val+ node.val){
                    adj[node.idx] = current.val + node.val;
                    
                    parent[node.idx] = current.idx;
                    // current.past.add(current.idx);
                    pq.add(new Node(node.idx, adj[node.idx]));
                }
            }
        }

        
        return adj[e];
    }
}
class Node implements Comparable<Node>{
    public int idx;
    public long val;

    Node(int idx, long val){
        this.idx = idx;
        this.val =val;
    }

    @Override
    public int compareTo(Node n1){
        return (int)(this.val -n1.val);
    }
}
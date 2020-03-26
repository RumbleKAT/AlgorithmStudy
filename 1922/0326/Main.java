import java.util.*;
import java.io.*;

class Main{
    static int N;
    static int M;

    static LinkedList<Node> [] nodes;
    static boolean [] visited;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));  
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        
        nodes = new LinkedList[N+1];
        visited = new boolean[N+1];
        
        for(int i=1;i<=N;i++){
            nodes[i] = new LinkedList<>();
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();

        for(int i =1;i<=M;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());
            long val = Long.parseLong(token.nextToken());
            
            nodes[s].add(new Node(e,val));
            nodes[e].add(new Node(s,val));
        }

        //1번 점을 기점으로 pq에 넣고 
        visited[1] = true;
        for(Node node : nodes[1]){
            pq.add(new Node(node.idx,node.val));
        }

        long ans = 0;

        while(!pq.isEmpty()){
            Node current = pq.poll();

            if(visited[current.idx]) continue;
            visited[current.idx] = true;
            ans += current.val;

            for(Node node : nodes[current.idx]){
               if(!visited[node.idx]){
                   pq.add(new Node(node.idx, node.val));
               }
            }
        }

        System.out.println(ans);
    }
}
class Node implements Comparable<Node>{
    public int idx;
    public long val;

    public Node(int idx, long val){
        this.idx = idx;
        this.val = val;
    }

    @Override
    public int compareTo(Node n1){
        return (int)(this.val - n1.val);
    }
}
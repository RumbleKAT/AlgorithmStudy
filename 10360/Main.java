import java.util.*;
import java.io.*;

class Main{
    
    static int TC;
    static int N,M;
    static long INF = Integer.MAX_VALUE;
    static long [] dist;
    static ArrayList<Integer> ans;
    static ArrayList<Node> nodes;
    static ArrayList<Node> [] rev;

    static boolean [] visited;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        TC = Integer.parseInt(token.nextToken());

        for(int t=1;t<=TC;t++){
            token = new StringTokenizer(br.readLine());
            N = Integer.parseInt(token.nextToken());
            M = Integer.parseInt(token.nextToken());
    
            dist = new long[N+1];
            visited = new boolean[N+1];
            nodes = new ArrayList<>();
            rev = new ArrayList[N+1];
            for(int i=0;i<=N;i++){
                rev[i] = new ArrayList<>();
            }
            ans = new ArrayList<>();
    
            for(int i=1;i<=M;i++){
                token = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(token.nextToken());
                int e = Integer.parseInt(token.nextToken());
                long val = Long.parseLong(token.nextToken());
    
                nodes.add(new Node(s,e,val));
                rev[e].add(new Node(s,val));
            }
            dfs(0);
            // System.out.println(Arrays.toString(visited));
            bellmanFord(t);
        }
     
    }
    static void dfs(int n){
        if(visited[n]) return;
        visited[n] = true;

        for(Node node: rev[n]){
            if(!visited[node.idx]){
                dfs(node.idx);
            }
        }
    }
 
    static void bellmanFord(int t){
        Arrays.fill(dist, INF);
        dist[0] = 0;

        boolean check = false;

        for(int j=0;j<N-1;j++){
            for(Node node : nodes){
                if(dist[j] != INF && dist[node.next] > dist[node.idx] + node.val){
                    dist[node.next] = dist[node.idx] + node.val;
                }
            }
        }

        for(Node node : nodes){
            if(dist[node.next] > dist[node.idx] + node.val && visited[node.idx]){
                check = true;
                break;
            }
        }

        if(check){
            System.out.println("Case #"+t+": possible");
        }else{
            System.out.println("Case #"+t+": not possible");
        }   
    }
    
}
class Node implements Comparable<Node>{
    public int idx;
    public int next;
    public long val;

    Node(int idx, long val){
        this.idx = idx;
        this.val = val;
    }

    Node(int idx, int next, long val){
        this.idx = idx;
        this.next = next;
        this.val = val;
    }

    @Override
    public int compareTo(Node n1){
        return (int)(this.val - n1.val);
    }
}
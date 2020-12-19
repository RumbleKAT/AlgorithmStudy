import java.util.*;
import java.io.*;

class Main{

    static int N;
    static int M;
    static ArrayList<Node> [] nodes;
    static ArrayList<Node> [] reverse_nodes;
    static int [] previous;

    static long INF = Integer.MAX_VALUE;
    static long [] dist;
    static boolean [] visited;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        nodes = new ArrayList[N+1];
        reverse_nodes = new ArrayList[N+1];

        dist = new long [N+1];
        previous = new int [N+1];

        visited = new boolean[N+1];
        for(int i=1;i<=N;i++){
            nodes[i] = new ArrayList<>();
            reverse_nodes[i] = new ArrayList<>();
        }

        for(int i=1;i<=M;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());
            long val = Long.parseLong(token.nextToken());
            
            nodes[s].add(new Node(e,val*-1));
            reverse_nodes[e].add(new Node(s,val)); //역간선 저장
        }

        //도착점에서 각 정점으로 접속이 되는지?
        bfs(N);
        if(bellmanford(1,N)){
            System.out.println("-1");
        }else{
            ArrayList<Integer> arr = new ArrayList<>();

            int prev = N;
            while(true){
                arr.add(prev);
                if(prev == 1){
                    break;
                }
                prev = previous[prev];
            }

            for(int i=N-1;i>=0;i--){
                System.out.print(arr.get(i) + " ");
            }System.out.println();

        }

    }
    static void bfs(int n){
        Arrays.fill(visited, false);

        Queue<Integer> que = new LinkedList<>();
        que.add(n);
        visited[n] = true;

        while(!que.isEmpty()){
            int cur = que.poll();

            for(Node node : reverse_nodes[cur]){ //역방향
                if(!visited[node.idx]){
                    visited[node.idx] = true;
                    que.add(node.idx);
                }
            }
        }
    }


    static boolean bellmanford(int s, int e){
        Arrays.fill(dist, INF);
        dist[s] = 0;

        boolean check = false;
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                for(Node node : nodes[j]){
                    if(dist[j] != INF &&  dist[node.idx] > dist[j] + node.val){
                        dist[node.idx] = dist[j] + node.val;
                        previous[node.idx] = j;
                        if(i==N && visited[node.idx]){
                            check = true;
                        }
                    }
                }
            }
        }

        return check;
    }

}
class Node{
    public int idx;
    public long val;

    Node(int idx, long val){
        this.idx = idx;
        this.val = val;
    }
}
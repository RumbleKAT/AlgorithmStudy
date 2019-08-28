import java.util.*;
import java.io.*;

class Main{
    static int N,M;
    static int S,D;
    static LinkedList<Node>[] arr;
    static long [][] map;
    static long [] adj;
    static LinkedList<Integer>[] trace;
    static long min;
    static int max = 987654321;

    public static void main(String [] args)throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token;
        
        while(true){
            token = new StringTokenizer(br.readLine());
            N = Integer.parseInt(token.nextToken());
            M = Integer.parseInt(token.nextToken());

            if(N == 0 && M == 0) {
				break;
			}
            
            adj = new long[N+1];
            map = new long[N+1][N+1];
            arr = new LinkedList[N+1];
            trace = new LinkedList[N+1];

            for(int i=0;i<N;i++){
                arr[i] = new LinkedList<>();
                trace[i] = new LinkedList<>();
                Arrays.fill(map[i],-1);
            }
            
            token = new StringTokenizer(br.readLine());
            S = Integer.parseInt(token.nextToken());
            D = Integer.parseInt(token.nextToken());  
            
            for(int i =1;i<=M;i++){
                token = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(token.nextToken());
                int v = Integer.parseInt(token.nextToken());
                long p = Long.parseLong(token.nextToken());

                arr[u].add(new Node(v,p));
                arr[v].add(new Node(u,p));
                map[u][v] = p;
            }

            min = dijkstra2(S, D); //최단거리는 무조건 기억

            //trace 삭제
            Queue <Integer> q = new LinkedList();
            q.add(D);
            while(!q.isEmpty()){
                int current = q.poll();
                for(int past : trace[current]){
                    if(map[past][current] != -1 && adj[current] == map[past][current] + adj[past]){
                        q.add(past);
                        map[past][current] = -1;
                    }
                }
            }

            long ans = dijkstra2(S, D);

            if(ans >= max){
                bw.write(-1+"\n");
            }else{
                bw.write(ans+"\n");
            }
            bw.flush();
        }
        
    }

    static long dijkstra2(int s, int d){

        for(int i=0;i<N;i++){
            Arrays.fill(adj, max);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        adj[s] = 0;

        pq.add(new Node(s,0));

        while(!pq.isEmpty()){
            Node current = pq.poll();

            for(int next = 0; next<N; next++){
                if(map[current.idx][next] != -1 && adj[next] >= map[current.idx][next] + current.val){    
                    adj[next] = map[current.idx][next] + current.val;
                    pq.add(new Node(next,adj[next]));
                    trace[next].add(current.idx);
                }
            }
        }

        return adj[d];
    }
    
}
class Node implements Comparable<Node>{
    public int idx;
    public long val = -1;

    Node(int idx, long val){
        this.idx = idx;
        this.val = val;
    }
    @Override
    public int compareTo(Node n1){
        return (int)(this.val - n1.val);
    }
}
class Trace {
    public int Idx;
    public int edgeIdx;//간선 정보

    Trace(int Idx, int edgeIdx){
        this.Idx = Idx;
        this.edgeIdx = edgeIdx;
    }

}
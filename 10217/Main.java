import java.util.*;
import java.io.*;

class Main{

    static int N,M,K;
    static int TC;
    static int [][] dp;
    static LinkedList<Node> [] adj;
    static int MAX = 987654321;
    static int u,v,c,d;
    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());
        TC = Integer.parseInt(token.nextToken());

        for(int t=1;t<=TC;t++){

            dp = new int [102][100002];
            adj = new LinkedList[102];

            for(int i =0;i<adj.length;i++){
                adj[i] = new LinkedList<>();
            }
            token = new StringTokenizer(br.readLine());
            N = Integer.parseInt(token.nextToken());
            M = Integer.parseInt(token.nextToken());
            K= Integer.parseInt(token.nextToken());

            for(int i =1;i<=K;i++){
                token = new StringTokenizer(br.readLine());
                u = Integer.parseInt(token.nextToken());
                v = Integer.parseInt(token.nextToken());
                c = Integer.parseInt(token.nextToken());
                d = Integer.parseInt(token.nextToken());

                adj[u].add(new Node(v,c,d));
            }

            int ans = MAX;
            PriorityQueue<Node> pq = new PriorityQueue();
            for(int i=0;i<102;i++){
                Arrays.fill(dp[i], -1);
            }

            dp[1][0] = 0;
            pq.add(new Node(1,0,0));
            while(!pq.isEmpty()){
                Node current = pq.poll();
                if(current.dist > dp[current.idx][current.time] || current.time > M) continue;

                for(int i = 0;i<adj[current.idx].size();i++){
                    Node next = adj[current.idx].get(i);
                    int tCost = current.time + next.time;
                    if((dp[next.idx][tCost] == -1 || dp[next.idx][tCost] > current.dist + next.dist) && current.time + next.time <= M){
                        dp[next.idx][tCost] = current.dist + next.dist;
                        pq.add(new Node(next.idx,tCost,current.dist + next.dist));
                    }
                }
            }

            for(int i=0;i<=M;i++){
                if(dp[N][i]!=-1 && ans > dp[N][i]){
                    ans = dp[N][i];
                }
            }
            if(ans == MAX){
                bw.write("Poor KCM\n");
            }else{
                bw.write(ans+"\n");
            }
            bw.flush();
        }
    }
}
class Node implements Comparable<Node>{
    public int idx;
    public int time;
    public int dist;

    Node(int idx, int time, int dist){
        this.idx = idx;
        this.time = time;
        this.dist = dist;
    }
    @Override
    public int compareTo(Node n1){
        return (int)(this.dist-n1.dist);
    }
}
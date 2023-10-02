import java.util.*;
import java.io.*;

class Main {

    static long [] dp;
    static ArrayList<Node> edge;
    static Long INF = Long.MAX_VALUE;
    static int N,ML,MD;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        ML = Integer.parseInt(token.nextToken());
        MD = Integer.parseInt(token.nextToken());

        dp = new long[N+1];
        Arrays.fill(dp,INF);
        edge = new ArrayList<>();
        dp[1] = 0;
        for(int i=1;i<=ML;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());
            int d = Integer.parseInt(token.nextToken());

            edge.add(new Node(s,e,d));
        }

        for(int i=1;i<=MD;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());
            int d = Integer.parseInt(token.nextToken());

            edge.add(new Node(s,e,-d));
        }

        boolean check = bellmanford();
        if(dp[N] == INF){
            bw.write("-2");
        }
        else if(!check){
            bw.write("-1");
        }else{
            bw.write(dp[N]+"");
        }
        bw.newLine();
        bw.flush();
    }
    static boolean bellmanford(){
        for(int i=0;i<=N;i++){
            for(Node node : edge){
                if(dp[node.s] != INF && dp[node.s] + node.val < dp[node.e]){
                  dp[node.e] = dp[node.s] + node.val;

                  if(i == N) return false;
                }
            }
        }
    }
    return true;
}
class Node{
    public int s;
    public int e;
    public int val;

    Node(int s,int e, int val){
        this.s = s;
        this.e = e;
        this.val = val;
    }
}
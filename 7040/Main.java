import java.util.*;
import java.io.*;

class Main {

    static long[] distDp;
    static ArrayList<Edge> edge;
    static Long INF = Long.MAX_VALUE;
    static int N,ML,MD;

    public static void main(String [] args) throws Exception{
        //System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        ML = Integer.parseInt(token.nextToken());
        MD = Integer.parseInt(token.nextToken());

        distDp = new long[N+1];
        Arrays.fill(distDp,INF);
        edge = new ArrayList<>();
        distDp[1] = 0;
        for(int i=1;i<=ML;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());
            int d = Integer.parseInt(token.nextToken());

            edge.add(new Edge(s,e,d));
        }

        for(int i=1;i<=MD;i++){
            token = new StringTokenizer(br.readLine());
            int e = Integer.parseInt(token.nextToken());
            int s = Integer.parseInt(token.nextToken());
            int d = Integer.parseInt(token.nextToken());

            edge.add(new Edge(s,e,-d));
        }

        boolean minusCycle = bellmanFord();
        
        if (!minusCycle) {
            bw.write(-1 + "\n");
        } else if (distDp[N] == INF) {
            bw.write(-2 + "\n");
        } else {
            bw.write(distDp[N] + "\n");
        }
        
        bw.flush();
        bw.close();
        br.close();
    }
    public static boolean bellmanFord() {
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < edge.size(); j++) {
                Edge cur = edge.get(j);
                
                if (distDp[cur.from] != INF 
                    && distDp[cur.to] > distDp[cur.from] + cur.dist) {
                    distDp[cur.to] = distDp[cur.from] + cur.dist;
                    
                    if (i == N) {
                        return false;
                    }
                }
            }
        }
        
        return true;
    }
}
 
class Edge {
    int from;
    int to;
    int dist;
    
    Edge(int from, int to, int dist) {
        this.from = from;
        this.to = to;
        this.dist = dist;
    }
}
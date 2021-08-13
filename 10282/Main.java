import java.io.*;
import java.util.*;

public class Main {

    static int TC;
    static int N,D,C;
    static ArrayList<Node> [] nodes;
    static boolean[] visited;
    static int [] dist;
    static final int MAX = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("/Users/songmyeongjin/eclipse-workspace/2512/src/com/company/sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());

        TC = Integer.parseInt(token.nextToken());

        for(int t=1;t<=TC;t++){
            token = new StringTokenizer(br.readLine());

            N = Integer.parseInt(token.nextToken());
            D = Integer.parseInt(token.nextToken());
            C = Integer.parseInt(token.nextToken());

            nodes = new ArrayList[N+1];
            visited = new boolean[N+1];
            for(int i=1;i<=N;i++){
                nodes[i] = new ArrayList<>();
            }

            dist = new int[N+1];
            Arrays.fill(dist,MAX);

            for(int i=1;i<=D;i++){
                token = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(token.nextToken());
                int b = Integer.parseInt(token.nextToken());
                int c = Integer.parseInt(token.nextToken());

                nodes[b].add(new Node(a,c));
            }

            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.add(new Node(C,0));
            dist[C] = 0;

            int cnt = 0;

            while(!pq.isEmpty()){
                Node cur = pq.poll();
                if(!visited[cur.idx]){
                    visited[cur.idx] = true;
                    cnt++;
                }else{
                    continue;
                }

                for(Node node : nodes[cur.idx]){
                    if(dist[node.idx] > dist[cur.idx] + node.val){
                        dist[node.idx] = dist[cur.idx] + node.val;
                        pq.add(new Node(node.idx,dist[node.idx]));
                    }
                }
            }
            int time = 0;
            for(int i=1;i<=N;i++){
                if(dist[i] != MAX) time = Math.max(time,dist[i]);
            }

            System.out.println(cnt + " " + time);
        }
    }
}
class Node implements Comparable<Node>{
    public int idx;
    public int val;

    public Node(int idx, int val){
        this.idx = idx;
        this.val = val;
    }

    @Override
    public int compareTo(Node o) {
        return this.val - o.val;
    }
}
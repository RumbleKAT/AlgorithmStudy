import java.util.*;
import java.io.*;

class Main{

    static int TC;
    static int N,M,T;
    static int S,G,H;
    static LinkedList<Node> [] arr;
    static long [] dists; 
    static int [] common;
    static long [] goals;
    static long [] commonToGoalsA;
    static long [] commonToGoalsB;
    static boolean [] results;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        TC = Integer.parseInt(token.nextToken());

        for(int t=1;t<=TC;t++){
            token = new StringTokenizer(br.readLine());
            N = Integer.parseInt(token.nextToken());
            M = Integer.parseInt(token.nextToken());
            T = Integer.parseInt(token.nextToken());

            arr = new LinkedList[N+1];
            goals = new long [N+1];
            common = new int [T+1];
            commonToGoalsA = new long[T+1];
            commonToGoalsB = new long[T+1];

            for(int i =1;i<=N;i++){
                arr[i] = new LinkedList<>();
            }
            dists = new long[N+1];
            // Arrays.fill(dists,987654321);

            token = new StringTokenizer(br.readLine());
            S = Integer.parseInt(token.nextToken());
            G = Integer.parseInt(token.nextToken());
            H = Integer.parseInt(token.nextToken());

            for(int i =1;i<=M;i++){
                token = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(token.nextToken());
                int e = Integer.parseInt(token.nextToken());
                int d = Integer.parseInt(token.nextToken());
                
                arr[s].add(new Node(e,d));
                arr[e].add(new Node(s,d));
            }

            for(int i=1;i<=T;i++){
                token = new StringTokenizer(br.readLine());
                common[i] = Integer.parseInt(token.nextToken());
                goals[i] = dijkstra(S, common[i]);
            }
            //목적지 후보군

            //시작점 -> G,H까지
            long StoG  = dijkstra(S, G);
            long StoH =  dijkstra(S, H);

            //G <-> H 는 같다.
            long GtoH = dijkstra(G, H);

            long sumTypeA = StoG + GtoH;
            long sumTypeB = StoH + GtoH;

            for(int i =1;i<=T;i++){
                commonToGoalsA[i] = dijkstra(G, common[i]);
                commonToGoalsB[i] = dijkstra(H, common[i]);
            }

            // System.out.println(StoG + " " + GtoH );
            // System.out.println(StoH + " " + HtoG );

            results = new boolean [N+1];

            for(int i =1;i<=T;i++){
                
                if(sumTypeA + commonToGoalsB[i] == goals[i]){
                    results[common[i]] = true;
                }

                if(sumTypeB + commonToGoalsA[i] == goals[i]){
                    results[common[i]] = true;
                }
            }

            for(int i=1;i<=N;i++){
                if(results[i]){
                    System.out.print(i + " ");
                }
            }System.out.println();

        }
    }
    static long dijkstra(int startNode, int destination){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(startNode,0));
        Arrays.fill(dists,987654321);

        dists[S] = 0;

        while(!pq.isEmpty()){
            Node current = pq.poll();

            if(destination == current.idx) return current.dist;

            for(Node nextNode : arr[current.idx]){
                if(dists[nextNode.idx] > current.dist + nextNode.dist){
                    dists[nextNode.idx] = current.dist + nextNode.dist;
                    pq.add(new Node(nextNode.idx, dists[nextNode.idx] ));
                }
            }
        }
        return -1;
    }
}
class Node implements Comparable<Node>{
    public int idx;
    public long dist;

    Node(int idx, long dist){
        this.idx = idx;
        this.dist = dist;
    }

    @Override
    public int compareTo(Node n1){
        return (int)(this.dist - n1.dist);
    }
}
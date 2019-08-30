import java.util.*;
import java.io.*;

class Main{
    
    static int N,M,K;
    static int s,e,v;
    static LinkedList<Node> [] arr;
    static PriorityQueue<Comp> [] dist;
    static int MAX = 987654321;
    static int [][] map;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(token.nextToken());

        arr = new LinkedList[N+1];
        dist = new PriorityQueue[N+1];
        map = new int [N+1][N+1];

        for(int i=1;i<=N;i++){
            arr[i] = new LinkedList<>();
            dist[i] = new PriorityQueue<>();
        }

        for(int i=1;i<=M;i++){
            token = new StringTokenizer(br.readLine());
            s = Integer.parseInt(token.nextToken());
            e = Integer.parseInt(token.nextToken());
            v = Integer.parseInt(token.nextToken());

            arr[s].add(new Node(e,v));
        }

        dijkstra();

    }
    static void dijkstra() throws Exception{
        PriorityQueue<Node> pq = new PriorityQueue<>();

        dist[1].add(new Comp(0));
        pq.add(new Node(1,0));

        while(!pq.isEmpty()){
            Node current = pq.poll();

            // System.out.println("idx : " + current.idx + " current : " + current.val);
            
            for(Node next : arr[current.idx]){
                // System.out.println("current Idx : " + current.idx + " " + next.idx + " " +dist[next.idx].size() + " " + dist[next.idx].peek());
                
                if(dist[next.idx].size() < K){
                    dist[next.idx].add(new Comp(next.val+current.val));
                    pq.add(new Node(next.idx , next.val+current.val));
                }
                else if(dist[next.idx].peek().val > next.val+current.val){
                    
                    dist[next.idx].poll();
                    dist[next.idx].add(new Comp(next.val+current.val));

                    pq.add(new Node(next.idx , next.val+current.val));
                }
            }

        }

        for(int i =1;i<=N;i++){
            if(dist[i].size() == K){
                bw.write(dist[i].poll().val+"\n");
            }else{
                bw.write("-1\n");
            }
            bw.flush();
        }

    }   

}
class Node implements Comparable<Node>{
    public int idx;
    public int val;

    Node(int idx, int val){
        this.idx = idx;
        this.val = val;
    }

    @Override
    public int compareTo(Node n1){
        return this.val < n1.val ? -1 : 1;
    }
}

class Comp implements Comparable<Comp>{
    public int val;

    Comp(int val){
        this.val = val;
    }

    @Override
    public int compareTo(Comp c1){
        return c1.val - this.val;
    }
}
import java.util.*;
import java.io.*;

class Main{

    static int N,M;
    static int start, end;

    static int [] dist;
    static int [][] adj;

    public static void main(String [] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        
        dist = new int [N+1];
        adj = new int [N+1][N+1];

        Arrays.fill(dist, Integer.MAX_VALUE);

        for(int i =1;i<=M;i++){
            token = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());
            int d =  Integer.parseInt(token.nextToken());

            adj[s][e] = d;
        }
        token = new StringTokenizer(br.readLine());
        start = Integer.parseInt(token.nextToken());
        end = Integer.parseInt(token.nextToken());

        PriorityQueue <Element> pq = new PriorityQueue<Element>();
        dist[start] = 0;
        pq.offer(new Element(start, dist[start]));

        while(!pq.isEmpty()){
            Element current = pq.poll();
            if(current.distance > dist[current.index]){
                continue;
            }

            for(int i = 0;i<=N;i++){
                if(adj[current.index][i] != 0 && dist[i] > dist[current.index] + adj[current.index][i]){
                    dist[i] = dist[current.index] + adj[current.index][i];
                    pq.offer(new Element(i,dist[i]));
                }
            }
        }

        System.out.println(dist[end]);

    }
}

class Element implements Comparable<Element>{
    public int index;
    public int distance;

    Element(int index, int distance){
        this.index = index;
        this.distance = distance;
    }

    public int compareTo(Element o){
        return this.distance <= o.distance ? -1 : 1;
    }
}
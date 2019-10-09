import java.util.*;
import java.io.*;

class Main{

    static int TC;
    static int N,M,W;
    static LinkedList<Node> [] arr;
    static long [] cost;
    static int [] parent;
    static long [] dist;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        TC = Integer.parseInt(token.nextToken());
        
        for(int t=1;t<=TC;t++){
            token = new StringTokenizer(br.readLine());
            N = Integer.parseInt(token.nextToken());
            M = Integer.parseInt(token.nextToken());

            cost = new long [N+1];
            parent = new int [N+1];
            arr = new LinkedList[N+1];
            dist = new long [N+1];
            token = new StringTokenizer(br.readLine());
            for(int i=1;i<=N;i++){
                arr[i] = new LinkedList<>();
                cost[i] = Long.parseLong(token.nextToken());
            }

            for(int i=1;i<=M;i++){
                token = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(token.nextToken());
                int e = Integer.parseInt(token.nextToken());
                
                arr[s].add(new Node(e));
                parent[e]++;                
            }

            LinkedList<NodeComp> que = new LinkedList<>();
            for(int i=1;i<=N;i++){
                if(parent[i] == 0){
                    // System.out.println("i : " +i);
                    que.add(new NodeComp(i));
                }
            }

            token = new StringTokenizer(br.readLine());
            W = Integer.parseInt(token.nextToken());
            // System.out.println(W);

            while(!que.isEmpty()){
                NodeComp current = que.poll();
                int idx = current.idx;

                // System.out.println(current.idx + " " +cur_cost);
                if(current.idx == W){
                    System.out.println((dist[W] + cost[W]));
                    // System.out.println(Arrays.toString(cost));
                }

                for(Node node : arr[current.idx]){
                    dist[node.idx] = Math.max(dist[node.idx] ,dist[idx]+cost[idx]);                    
                    parent[node.idx]--;
                    if(parent[node.idx] == 0){
                        que.add(new NodeComp(node.idx));
                    }
                }


            }

        }
    }
}
class Node{
    public int idx;

    Node(int idx){
        this.idx = idx;
    }
}
class NodeComp implements Comparable<NodeComp>{
    public int idx;
    public long val;
    public int level;

    NodeComp(int idx, long val){
        this.idx = idx;
        this.val = val;
    }

    NodeComp(int idx){
        this.idx = idx;
    }

    @Override
    public int compareTo(NodeComp nc1){
        return (int)(this.val - nc1.val);
    }
}
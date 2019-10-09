import java.util.*;
import java.io.*;

class Main{

    static int N,M;
    static LinkedList<Node>[] arr;
    static LinkedList<Node>[] pArr;
    static LinkedList<Node>[] mArr;
    static long [] adj;
    static long [] wolfFast;
    static long [] wolfSlow;
    static int [] visited;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        arr = new LinkedList[N+1];
        pArr = new LinkedList[N+1];
        mArr = new LinkedList[N+1];
        adj = new long[N+1];
        wolfFast = new long[N+1];
        wolfSlow = new long[N+1];
        visited = new int [N+1];

        for(int i=1;i<=N;i++){
            arr[i] = new LinkedList<>();
            pArr[i] = new LinkedList<>();
            mArr[i] = new LinkedList<>();
        }

        for(int i=1;i<=M;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());
            long val = Long.parseLong(token.nextToken());

            arr[s].add(new Node(e,val*2));
            arr[e].add(new Node(s,val*2));
        }

        Arrays.fill(adj, Integer.MAX_VALUE);
        Arrays.fill(wolfFast, Integer.MAX_VALUE);
        Arrays.fill(wolfSlow, Integer.MAX_VALUE);

        PriorityQueue<NodeComp> que = new PriorityQueue<>();
        que.add(new NodeComp(1,true,0,0));
        adj[1] = 0;

        Comp [] cntCheck = new Comp [N+1];
        int [] cnt = new int[N+1];

        long ans = 0;

        while(!que.isEmpty()){
            NodeComp current = que.poll();
            
            if (current.val != adj[current.idx]) continue;

            if(current.fox){
                //일반 다익스트라
                for(Node node : arr[current.idx]){
                    if(adj[node.idx] > node.val + adj[current.idx]){
                        adj[node.idx] = node.val + adj[current.idx];
                        que.add(new NodeComp(node.idx,true, adj[node.idx],current.cnt+1));
                    }
                }
            }
        }

        que.clear();

        que = new PriorityQueue<>();
        
        que.add(new NodeComp(1,false,0,true,0));
        wolfSlow[1] = 0;
        // wolfSlow[1] = 0;

        while(!que.isEmpty()){
            NodeComp current = que.poll();

            if(current.mpCheck){
                if(wolfSlow[current.idx] != current.val) continue;
            }else{
                if(wolfFast[current.idx] != current.val) continue;
            }

            for(Node node : arr[current.idx]){
                if(current.mpCheck){
                    if(wolfFast[node.idx] >(node.val/2) + wolfSlow[current.idx]){
                        wolfFast[node.idx] = (node.val/2) + wolfSlow[current.idx];
                        que.add(new NodeComp(node.idx,false, wolfFast[node.idx],false,current.cnt+1));
                    }
                
                }else{
                    if(wolfSlow[node.idx] > (node.val*2) + wolfFast[current.idx]){
                        wolfSlow[node.idx] = (node.val*2) + wolfFast[current.idx];
                        que.add(new NodeComp(node.idx,false, wolfSlow[node.idx],true,current.cnt+1));
                    }
                }
            }
        }

        for(int i=1;i<=N;i++){
            if(adj[i] < Math.min(wolfFast[i],wolfSlow[i])){
                ans +=1;
            }
        }

        //  System.out.println(Arrays.toString(adj));
        //  System.out.println(Arrays.toString(wolfFast));
        //  System.out.println(Arrays.toString(wolfSlow));

        bw.write(ans+"\n");
        bw.flush();

        // for(Comp comp : cntCheck){
        //     if(comp!=null)
        //     System.out.println(comp.fox + " " + comp.val);
        // }


    }
}
class Node implements Comparable<Node>{
    public int idx;
    public long val;

    Node(int idx, long val){
        this.idx = idx;
        this.val = val;
    }

    @Override
    public int compareTo(Node n1){
        return (int)(this.val - n1.val);
    }

}
class NodeComp implements Comparable<NodeComp>{
    public int idx;
    public boolean fox; //false wolf
    public long val;
    public boolean mpCheck; // multi X divide
    public int cnt;

    NodeComp(int idx, boolean fox, long val, int cnt){
        this.idx = idx;
        this.fox = fox;
        this.val = val;
        this.cnt = cnt;
    }

    NodeComp(int idx, boolean fox, long val, boolean mpCheck, int cnt){
        this.idx = idx;
        this.fox = fox;
        this.val = val;
        this.mpCheck = mpCheck;
        this.cnt = cnt;
    }

    @Override
    public int compareTo(NodeComp nc){
        return (int)(this.val - nc.val);
    }
}

class Comp{
    public boolean fox;
    public int val;

    Comp(boolean fox, int val){
        this.fox = fox;
        this.val = val;
    }
}
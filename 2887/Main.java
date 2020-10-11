import java.util.*;
import java.io.*;

class Main{

    static int N;
    static ArrayList<Node> nodes;
    static NodeComp [] nodeList;
    static int [] parent;

    public static void main(String [] args) throws Exception{
        // System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(token.nextToken());

        nodes = new ArrayList<>();
        nodeList = new NodeComp[N+1];
        parent = new int[N+1];

        for(int i=1;i<=N;i++){
            token = new StringTokenizer(br.readLine());
            long x = Long.parseLong(token.nextToken());
            long y = Long.parseLong(token.nextToken());
            long z = Long.parseLong(token.nextToken());
            parent[i] = i;

            nodeList[i] = new NodeComp(x,y,z,i);
        }

        Arrays.sort(nodeList,1,N+1,new Comparator<NodeComp>(){
            @Override
            public int compare(NodeComp n1, NodeComp n2){
                return (int)(n1.x - n2.x);
            }
        });
        
        for(int i=1;i<N;i++){
            nodes.add(new Node(nodeList[i].idx,nodeList[i+1].idx,Math.abs(nodeList[i].x-nodeList[i+1].x)));
        }

        Arrays.sort(nodeList,1,N+1,new Comparator<NodeComp>(){
            @Override
            public int compare(NodeComp n1, NodeComp n2){
                return (int)(n1.y - n2.y);
            }
        });
        
        for(int i=1;i<N;i++){
            nodes.add(new Node(nodeList[i].idx,nodeList[i+1].idx,Math.abs(nodeList[i].y-nodeList[i+1].y)));
        }

        Arrays.sort(nodeList,1,N+1,new Comparator<NodeComp>(){
            @Override
            public int compare(NodeComp n1, NodeComp n2){
                return (int)(n1.z - n2.z);
            }
        });
        
        for(int i=1;i<N;i++){
            nodes.add(new Node(nodeList[i].idx,nodeList[i+1].idx,Math.abs(nodeList[i].z-nodeList[i+1].z)));
        }

        Collections.sort(nodes);

        long res = 0;

        for(Node node : nodes){
            int rootA = find(node.s);
            int rootB = find(node.e);
            
            if(rootA != rootB){
                parent[rootB] = rootA;
                res += node.val;
            }            
        }

        System.out.println(res);


    }
    static int find(int a){
        if(parent[a] == a) return a;
        else return parent[a] = find(parent[a]);
    }

}
class Node implements Comparable<Node>{
    public int s;
    public int e;
    public long val;

    Node(int s, int e, long val){
        this.s = s;
        this.e = e;
        this.val = val;
    }   

    @Override
    public int compareTo(Node n1){
        return (int)(this.val - n1.val);
    }
}

class NodeComp{
    public long x;
    public long y;
    public long z;
    public int idx;

    NodeComp(long x, long y, long z, int idx){
        this.x = x;
        this.y = y;
        this.z = z;
        this.idx = idx;
    }
}
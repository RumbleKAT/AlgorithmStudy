import java.util.*;
import java.io.*;

class Main{

    static int N,C;
    static Node [] node;
    static ArrayList <NodeComp> nodes;
    static int [] root;

    public static void main(String [] args) throws Exception {
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(token.nextToken());
        C = Integer.parseInt(token.nextToken());

        node = new Node[N+1];
        nodes = new ArrayList<>();
        root = new int[N+1];

        for(int i=1;i<=N;i++){
            root[i] = i;
        }

        for(int i=1;i<=N;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());
            node[i] = new Node(s,e,i);
        }

        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                if(i!=j)
                nodes.add(new NodeComp(i, j, getDist(node[i].x,node[i].y,node[j].x,node[j].y)));
            }
        }

        Collections.sort(nodes);
        long sum = 0;
        int cnt= 0 ;

        for(NodeComp node : nodes){
            if(node.val < C){
                cnt +=1;
                continue;
            }
            int rootA = getRoot(node.s);
            int rootB = getRoot(node.e);
            
            if(rootA != rootB){
                root[rootB] = rootA;
                sum += node.val;
            }        
        }

        boolean check = true;
        if(N != 1){
            for(int i=2;i<=N;i++){
                if(root[i] != root[i-1]){
                    check = false;
                    break;
                }
            }
        }
        // System.out.println(Arrays.toString(root));

        if(!check) System.out.println(-1);
        else System.out.println(sum);

    }
    static long getDist(int x1, int y1, int x2, int y2){
        return (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2);
    }

    static int getRoot(int a){
        if(root[a] == a) return root[a];
        else return root[a] = getRoot(root[a]);
    }
}
class Node{
    public int x;
    public int y;
    public int idx;

    Node(int x, int y, int idx){
        this.x = x;
        this.y = y;
        this.idx = idx;
    }
}

class NodeComp implements Comparable<NodeComp>{
    public int s;
    public int e;
    public long val;

    NodeComp(int s, int e, long val){
        this.s = s;
        this.e = e;
        this.val = val;
    }

    @Override
    public int compareTo(NodeComp arg0) {
        return (int)(this.val - arg0.val);
    }
}
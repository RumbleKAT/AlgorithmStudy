import java.io.*;
import java.util.*;

public class Main {

    static int N,M;
    static int [] parent;
    static ArrayList<NodeComp> nodes;
    static Node [] arr;

    public static void main(String[] args) throws Exception {
	 //   System.setIn(new FileInputStream("/Users/songmyeongjin/eclipse-workspace/2512/src/com/company/sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        arr = new Node[N+1];
        parent = new int[N+1];
        nodes = new ArrayList<>();

        for(int i=1;i<=N;i++){
            token = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(token.nextToken());
            int y = Integer.parseInt(token.nextToken());
            int z = Integer.parseInt(token.nextToken());

            arr[i] = new Node(x,y,z,i);
            parent[i] = i;
        }

        Arrays.sort(arr, 1, N + 1, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.x - o2.x;
            }
        });

        for(int i=1;i<N;i++){
            nodes.add(new NodeComp(arr[i].idx,arr[i+1].idx,Math.abs(arr[i].x - arr[i+1].x)));
        }

        Arrays.sort(arr, 1, N + 1, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.y - o2.y;
            }
        });

        for(int i=1;i<N;i++){
            nodes.add(new NodeComp(arr[i].idx,arr[i+1].idx,Math.abs(arr[i].y - arr[i+1].y)));
        }


        Arrays.sort(arr, 1, N + 1, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.z - o2.z;
            }
        });

        for(int i=1;i<N;i++){
            nodes.add(new NodeComp(arr[i].idx,arr[i+1].idx,Math.abs(arr[i].z - arr[i+1].z)));
        }

        Collections.sort(nodes);

        int sum = 0;

        for(NodeComp cur : nodes){
            int rs = getRoot(cur.s);
            int re = getRoot(cur.e);

            if(rs != re){
                sum += cur.val;
                if(parent[rs] > re){
                    parent[rs] = re;
                }else{
                    parent[re] = rs;
                }
            }
        }

        bw.write(sum+"\n");
        bw.flush();
    }
    static int getRoot(int n){
        if(parent[n] == n){
            return n;
        }else{
            return parent[n] = getRoot(parent[n]);
        }
    }
}
class Node{
    public int x;
    public int y;
    public int z;
    public int idx;

    Node(int x, int y, int z, int idx){
        this.x = x;
        this.y = y;
        this.z = z;
        this.idx = idx;
    }
}
class NodeComp implements Comparable<NodeComp>{
    public int s;
    public int e;
    public int val;

    NodeComp(int s, int e, int val){
        this.s = s;
        this.e = e;
        this.val = val;
    }

    @Override
    public int compareTo(NodeComp o) {
        return this.val - o.val;
    }
}
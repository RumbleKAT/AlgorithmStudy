import java.util.*;
import java.io.*;

class Main{

    static int N,M;
    static Node [] arr;
    static int [] parent;
    static long [] groupSize;
    static long total;
    static final long MOD = 1000000000;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        
        arr = new Node[M+1];
        parent = new int[N+1];
        groupSize = new long[N+1];
        for(int i=1;i<=N;i++){
            parent[i] = i;
            groupSize[i] = 1;
        }
        total = 0;
        for(int i=1;i<=M;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());
            long val = Long.parseLong(token.nextToken());
            arr[i] = new Node(s,e,val);
            total += val;
        }

        // System.out.println(total);

        Arrays.sort(arr,1,M+1,new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2){
                return (int)(n2.val - n1.val);
            }
        });

        // for(int i =1;i<=M;i++){
        //     System.out.println(arr[i].val + " " + arr[i].x + " " + arr[i].y);
        // }

        long res = 0;
        for(int i=1;i<=M;i++){
            Node node = arr[i];
            int rootA = find(node.x);
            int rootB = find(node.y);

            if(rootA!=rootB){
                // System.out.println(groupSize[rootA] + " " + groupSize[rootB]);
                res += groupSize[rootA] * groupSize[rootB] * total;
                res %= MOD;
                parent[rootB] = rootA;
                groupSize[rootA] +=  groupSize[rootB];
            }
            total -= node.val;
        }
        System.out.println(res);
        
    }

    static int find(int a){
        if(parent[a] == a){
            return a;
        }else{
            return parent[a] = find(parent[a]);
        }
    }
}
class Node{
    public int x;
    public int y;
    public long val;

    Node(int x, int y, long val){
        this.x = x;
        this.y = y;
        this.val = val;
    }
}
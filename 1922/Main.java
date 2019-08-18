import java.util.*;
import java.io.*;

class Main{
    
    static int N;
    static int M;
    static int [] parent;
    static long cost;
    static Node [] arr;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        token = new StringTokenizer(br.readLine());
        M = Integer.parseInt(token.nextToken());

        cost= 0;
        arr = new Node[M+1];
        parent = new int[N+1];

        for(int i=1;i<=N;i++){
            parent[i] = i;
        }

        for(int i =1;i<=M;i++){
            token = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(token.nextToken());
            int b = Integer.parseInt(token.nextToken());
            int c = Integer.parseInt(token.nextToken());

            arr[i] = new Node(a,b,c);
        }

        Arrays.sort(arr,1,M+1);

        for(int i = 1;i<=M;i++){
            int a = arr[i].a;
            int b = arr[i].b;

            union(a,b,i);
        }

        System.out.println(cost);
    }

    static void union(int a, int b,int idx){
        int rootA = find(a);
        int rootB = find(b);

        if(rootA != rootB){
            parent[rootA] = rootB;
            cost += arr[idx].c;
        }
    }

    static int find(int a){
        if(parent[a] == a){
            return a;
        }else{
            parent[a] = find(parent[a]);    
            return parent[a];
        }
    }
}
class Node implements Comparable<Node>{
    public int a;
    public int b;
    public int c;

    Node(int a, int b, int c){
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int compareTo(Node n){
        return c - n.c;
    }
}
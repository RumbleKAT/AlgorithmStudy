import java.util.*;
import java.io.*;

class Main{

    static int N,M;
    static int [] parent;
    static Node [] arr;
    static long min;
    static long max;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        
        parent = new int [N+1];
        arr = new Node[M+1];
        for(int i=0;i<=N;i++){
            parent[i] = i;
        }

        for(int i=0;i<=M;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());
            int v = Integer.parseInt(token.nextToken());
            
            if(v == 0) v = 1;
            else v = 0;
            arr[i] = new Node(s,e,v);
        }

        Arrays.sort(arr,0,M+1,new Comparator<Node>(){
            @Override
            public int compare(Node n1, Node n2){
                return n1.val - n2.val;
            }
        });

        long ans = 0;
        long ans_2 = 0;

        for(int i=0;i<=M;i++){
            int rootA = find(arr[i].s);
            int rootB = find(arr[i].e);

            if(rootA != rootB){
                parent[rootA] = rootB;
                ans += arr[i].val;
            }
        }

        // System.out.println(ans);

        for(int i=0;i<=N;i++){
            parent[i] = i;
        }
        
        for(int i=M;i>=0;i--){
            int rootA = find(arr[i].s);
            int rootB = find(arr[i].e);

            if(rootA != rootB){
                parent[rootA] = rootB;
                ans_2 += arr[i].val;
            }
        }

        System.out.println((ans_2*ans_2) -(ans*ans));

    }

    static int find(int a){
        if(parent[a] == a) return a;
        else return parent[a] = find(parent[a]);
    }
}
class Node{
    public int s;
    public int e;
    public int val;

    Node(int s, int e, int val){
        this.s = s;
        this.e = e;
        this.val = val;
    }
}
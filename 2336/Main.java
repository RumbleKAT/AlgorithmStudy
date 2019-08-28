import java.util.*;
import java.io.*;

class Main{

    static int N;
    static Node [] arr1;
    static int [] seg;
    static int MAX = 500001;
    static int INF = 987654321;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());

        arr1 = new Node [MAX];
        int h =1;

        N = Integer.parseInt(token.nextToken());

        while(h<N) h*=2;

        seg = new int [h*2];

            token = new StringTokenizer(br.readLine());
            
            for(int i =1;i<=N;i++){
                int tmp =Integer.parseInt(token.nextToken());
                // System.out.println(tmp);
                arr1[tmp] = new Node(i);
            }

            token = new StringTokenizer(br.readLine());
            
            for(int i =1;i<=N;i++){
                int tmp =Integer.parseInt(token.nextToken());
                // System.out.println(tmp);
                arr1[tmp].y = i;
            }

            token = new StringTokenizer(br.readLine());
            
            for(int i =1;i<=N;i++){
                int tmp =Integer.parseInt(token.nextToken());
                // System.out.println(tmp);
                arr1[tmp].z = i;
            }

        Arrays.sort(arr1,1,N+1,new Comparator<Node>(){
            @Override
            public int compare(Node n1, Node n2){
                return (int)(n1.x - n2.x);
            }
        });

        int cnt = 0;
        for(int i =1;i<=N;i++) update(i,INF,1,1,N);

        for(int i =1;i<=N;i++){
            if (query(1, arr1[i].y, 1, 1, N)>arr1[i].z)
                cnt++;
                update(arr1[i].y, arr1[i].z, 1, 1, N);
            // for(int j=1;j<=N*4;j++){
            //     if(seg[j] == Integer.MAX_VALUE) System.out.print("INF ");
            //     else System.out.print(seg[j] + " ");
            // }System.out.println();

            // System.out.println(arr1[i].x + " "+ arr1[i].y + " " + arr1[i].z);
        }
        System.out.println(cnt);

    }
    static int update(int pos, int val, int node, int x, int y) {
        if (y < pos || pos < x)
            return seg[node];
        if (x == y)
            return seg[node] = val;
        int mid = (x + y) >> 1;
        return seg[node] = Math.min(update(pos, val, node * 2, x, mid), update(pos, val, node * 2 + 1, mid + 1, y));
    }
    static int query(int lo, int hi, int node, int x, int y) {
        if (y < lo || hi < x)
            return INF;
        if (lo <= x&&y <= hi)
            return seg[node];
        int mid = (x + y) >> 1;
        return Math.min(query(lo, hi, node * 2, x, mid), query(lo, hi, node * 2 + 1, mid + 1, y));
    }
    
}
class Node{
    public int x;
    public int y;
    public int z;

    Node(int x){
        this.x = x;
    }

    Node(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
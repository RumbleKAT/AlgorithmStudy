import java.util.*;
import java.io.*;

class Main{

    static int N;
    static int MAX = 100001;
    static int MAX_2 = 300003;
    static Node [] arr;
    static long [] seg;//y
    static int [] cnt;

    public static void main(String [] args) throws Exception{
        //System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());

        arr = new Node [MAX];
        seg = new long [MAX_2*4];
        cnt = new int [MAX_2*4];

        for(int i =0;i<N;i++){
            token = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(token.nextToken());
            int y1 = Integer.parseInt(token.nextToken());
            
            int x2 = Integer.parseInt(token.nextToken());
            int y2 = Integer.parseInt(token.nextToken());

            arr[i] = new Node(x1, y1, y2-1, 1);
            arr[i+N] = new Node(x2, y1, y2-1, -1);
        }

        Arrays.sort(arr,0,N*2,new Comparator<Node>(){
            @Override
            public int compare(Node n1, Node n2){
                if(n1.x > n2.x) return 1;
                else if(n1.x == n2.x) return 0;
                else return -1;
            }
        });

        int past = -1;
        long ans = 0;
        for(int i =0;i<N*2;i++){

            if(i>0){
                int dx = arr[i].x-past;
                ans += dx*seg[1];
            }
            update(1, arr[i].z, 0, MAX, arr[i].y1, arr[i].y2);
            past = arr[i].x;
        }

        bw.write(ans+"");
        bw.newLine();
        bw.flush();
    }
    static void update(int node, int val, int start, int end, int left, int right){
        if(start > right || end < left) return;
        if(left <= start && end <= right){
            cnt[node] += val;
        }else{
            int mid = (start+end)/2;
            update(node*2, val, start, mid, left, right);
            update(node*2+1, val, mid+1, end, left, right);
        }
        if(cnt[node]>0) seg[node] = end-start+1;
        else{
            if(start == end){
                seg[node] = 0;
            }else{
                seg[node] = seg[node*2] + seg[node*2+1];
            }
        }
    }
}
class Node{
    public int x;
    public int y1;
    public int y2;
    public int z;

    Node(int x, int y1, int y2, int z){
        this.x = x;
        this.y1 = y1;
        this.y2 = y2;
        this.z = z;
    }

}
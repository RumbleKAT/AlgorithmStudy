import java.util.*;
import java.io.*;


class Main{

    static int N;
    static int [] arr;
    static Node [] node;
    static int [] seg;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());

        arr = new int [N+1];
        node = new Node [N+1];
        seg = new int [(N+1)*4];



        for(int i =0;i<N;i++){
            token = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(token.nextToken());
            node[i] = new Node(arr[i],i);
        }

        Arrays.sort(node,0,N, new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2){
                return n1.first - n2.first;
            }
        });

        for(int i=0;i<N;i++){
            node[i].first = i; //좌표 압축
        }

        Arrays.sort(node,0,N, new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2){
                return n1.second - n2.second;
            }
        });

        for(int i =0;i<N;i++){
            System.out.println((1+query(1,node[i].first,0,N-1)));
            update(1, node[i].first, 0, N-1);
        }

        
    }
    static int update (int node, int value, int left, int right){
        if(value < left) return seg[node];
        if(right < value || left == right) return 0;
        int mid = (left+right)/2;
        return seg[node] = update(node*2, value, left,mid) + update(node*2+1, value, mid+1,right);
    }

    static int query(int node, int value, int start, int end){
        if(value < start) return seg[node];
        if(end < value || start == end) return 0;
        int mid = (start+end)/2;
        return query(node*2, value, start, mid) + query(node*2+1, value, mid+1, end);
    }
}
class Node {
    public int first;
    public int second;

    Node(int first, int second){
        this.first = first;
        this.second = second;
    }
}
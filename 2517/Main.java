import java.util.*;
import java.io.*;

class Main{

    static int [] seg;
    static Node [] nodes;
    static int [] arr;
    static int N;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());

        arr = new int [N+1];
        seg = new int [N*4];
        nodes = new Node [N+1];

        for(int i =1;i<=N;i++){
            token = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(token.nextToken());
            nodes[i] = new Node(arr[i], i);
        }

        Arrays.sort(nodes,1,N+1,new Comparator<Node>(){
            @Override
            public int compare(Node n1, Node n2){
                return (int)(n1.first - n2.first);
            }
        });
        
        int [] order = new int [N+1];

        for(int i =1;i<=N;i++){
            order[nodes[i].second] = i;
        }

        System.out.println(Arrays.toString(order));

		StringBuilder ansSB = new StringBuilder();

        for(int i =1;i<=N;i++){
            ansSB.append(i-query(1, 0, order[i], 1, N)).append("\n");
            update(1, order[i], 1, N);
        }
        System.out.println(ansSB);
    }

    static void update(int node, int index, int start, int end){
        if (index < start || end < index) {
			return;
		}   
        seg[node] += 1;
        if(start != end){
            int mid = (start + end)/2;
            update(node*2, index, start, mid);
            update(node*2+1, index, mid+1, end);
        }
    }

    static int query(int node, int left, int right, int start, int end){
        if(start > right || end < left) return 0;
        if(left <= start && end <= right) return seg[node];
        int mid = (start + end)/2;
        return query(node*2, left, right, start, mid) + query(node*2+1, left, right, mid+1, end);
    }
}
class Node{
    public int first;
    public int second;

    Node(int first, int second){
        this.first = first;
        this.second = second;
    }
}
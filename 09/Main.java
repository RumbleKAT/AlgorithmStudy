import java.io.*;
import java.util.*;

class Main {
	
	static int N;
	static int [] arr;
	static long [] seg;
    static Node [] nodes;
    static final int MAX = 500001;
	
	public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./sample.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(token.nextToken());
		
		arr = new int [MAX+1];
        seg = new long [MAX*4];
        nodes = new Node[N];
		
		for(int i=0;i<N;i++){
			token = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(token.nextToken());
			int e = Integer.parseInt(token.nextToken());
            nodes[i] = new Node(s,e);
        }	
        Arrays.sort(nodes);

        // for(Node node : nodes){
        //     System.out.println(node.s + " " + node.e);
        // }
        
        init(1,1,MAX);
        long ans = 0;
        
        for(int i =0;i<N;i++){
            update(1, 1, MAX+1, nodes[i].s, 1);
            ans = Math.max(ans, query(1, nodes[i].s,nodes[i].e , 1, MAX+1));
        }
        System.out.println(ans);
		
    }//lazy
    
    static long init(int node, int start, int end){
        if(start == end){
            return seg[node] = arr[start];
        }
        int mid = (start+end)/2;
        return seg[node] = init(node*2, start, mid) + init(node*2+1, mid+1, end);
    }
		
	static long update(int node, int left, int right, int index, long val){
		if(index < left || index > right) return seg[node];
		int mid = (left+right)/2;
		if(left == right){
			return seg[node] += val;
		}else{
			return seg[node] = update(node*2,left,mid,index, val) + update(node*2+1,mid+1,right,index,val);
		}
	}
	
	static long query(int node, int left, int right, int start, int end){
		if(start > right || end < left) return 0;
		if(left <= start && end <= right) return seg[node];
		int mid = (start+end)/2;
		return query(node*2,left,right,start,mid) + query(node*2+1,left,right,mid+1,end);
	}
}
class Node implements Comparable<Node>{
    public int s;
    public int e;

    Node(int s, int e){
        this.s = s;
        this.e = e;
    }

    @Override
    public int compareTo(Node node){
        if(this.s == node.s){
           return node.e - this.e;
        }
        return node.s - this.s;
    }
}
import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int [][] parent;
	static int [][] cost;
	static int [] depth;
	static int [] energy;
	static boolean [] visited;
	static ArrayList<Node> [] nodes;
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("C:\\Users\\root\\eclipse-workspace\\14942\\sample.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(token.nextToken());
		
		visited = new boolean [N+1];
		energy = new int[N+1];
		parent = new int [N+1][21];
		cost = new int [N+1][21];
		depth = new int [N+1];
		
		nodes = new ArrayList[N+1];
		for(int i=1;i<=N;i++) {
			nodes[i] = new ArrayList<Node>();
		}
		
		for(int i=1;i<=N;i++) {
			token = new StringTokenizer(br.readLine());
			energy[i] = Integer.parseInt(token.nextToken());
		}
		
		for(int i=1;i<N;i++) {
			token = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(token.nextToken());
			int e = Integer.parseInt(token.nextToken());
			int val = Integer.parseInt(token.nextToken());
			
			nodes[s].add(new Node(e,val));
			nodes[e].add(new Node(s,val));
		}
		
		bfs(1);
		init();
	
		for(int n=1;n<=N;n++) {
			int here = n;
			for(int m=20;m>=0;m--) {
				if(energy[n] - cost[here][m] >= 0) {
					energy[n] -= cost[here][m];
					here = parent[here][m];
				}
			}
			if(here == 0) {
				System.out.println(1);	
			}else{
				System.out.println(here);
			}
		}
		
	}
	static void bfs(int node) {
		Queue<Node> que = new LinkedList<>();
		que.add(new Node(node,0)); //current node / depth
		
		while(!que.isEmpty()) {
			Node cur = que.poll();
			
			if(visited[cur.idx]) continue;
			visited[cur.idx] = true;
			depth[cur.idx] = cur.val;
			
			for(Node next : nodes[cur.idx]) {
				if(!visited[next.idx]) {
					parent[next.idx][0] = cur.idx; 
					cost[next.idx][0] = next.val;
					que.add(new Node(next.idx,cur.val+1));
				}
			}
		}
	}
	static void init() {
		for(int j=1;j<=20;j++) {
			for(int i=1;i<=N;i++) {
				parent[i][j] = parent[parent[i][j-1]][j-1];
				cost[i][j] = cost[parent[i][j-1]][j-1] + cost[i][j-1];
			}
		}
	}
	
	
}
class Node{
	public int idx;
	public int val;
	
	Node(int idx, int val) {
		this.idx = idx;
		this.val = val;
	}
}

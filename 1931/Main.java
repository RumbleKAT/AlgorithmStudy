import java.util.*;
import java.io.*;


public class Solution {

	static Node [] nodes;
	static int N;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("/Users/songmyeongjin/eclipse-workspace/Solution/src/sample.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(br.readLine());

		N = Integer.parseInt(token.nextToken());

		nodes = new Node[N];

		for(int i=0;i<N;i++){
			token = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(token.nextToken());
			int e = Integer.parseInt(token.nextToken());
			nodes[i] = new Node(s,e);
		}

		Arrays.sort(nodes);

		int cnt = 0;
		int prevEnd = -1;

		for(Node node : nodes){
			System.out.println(node.s + " " + node.e + " " + node.val);
			if(prevEnd <= node.s){
				prevEnd = node.e;
				cnt++;
			}
		}

		System.out.println(cnt);

	}
}
class Node implements Comparable<Node> {
	public int s;
	public int e;
	public int val;

	Node(int s,int e){
		this.s = s;
		this.e = e;
		this.val = e - s;
	}

	@Override
	public int compareTo(Node o) {
		if(o.e == this.e){
			return this.s - o.s;
		}
		return this.e - o.e;
	}
}
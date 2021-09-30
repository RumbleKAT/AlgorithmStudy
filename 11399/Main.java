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

		token = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++){
			nodes[i] = new Node(i,Integer.parseInt(token.nextToken()));
		}

		Arrays.sort(nodes);

		int [] sum = new int[N];
		sum[0] = nodes[0].val;

		for(int i =1;i<nodes.length;i++){
			sum[i] = sum[i-1] + nodes[i].val;
		}
		int ans = 0;
		for(int i=0;i<sum.length;i++){
			ans += sum[i];
		}
//		System.out.println(Arrays.toString(sum));
		System.out.println(ans);

	}
}
class Node implements Comparable<Node>{
	public int idx;
	public int val;

	Node(int idx, int val){
		this.idx = idx;
		this.val = val;
	}

	@Override
	public int compareTo(Node o) {
		return this.val - o.val;
	}
}
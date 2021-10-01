import java.util.*;
import java.io.*;


public class Main {

	static int N, K;
	static boolean [] visited;

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("/Users/songmyeongjin/eclipse-workspace/Solution/src/sample.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(br.readLine());

		N = Integer.parseInt(token.nextToken());
		K = Integer.parseInt(token.nextToken());

		Queue<Node> que = new LinkedList<>();
		que.add(new Node(N,0));
		visited = new boolean[100001];

		int min = 100001;

		while(!que.isEmpty()){
			Node cur = que.poll();
			if(cur.idx > 100000  || cur.idx < 0) continue;
            
			if(visited[cur.idx]) continue;
			visited[cur.idx] = true;
            
			if(cur.idx == K){
        		System.out.println(cur.second);
                break;
			}
			que.add(new Node(cur.idx*2,cur.second));
			que.add(new Node(cur.idx-1,cur.second+1));
            que.add(new Node(cur.idx+1,cur.second+1));
        }

	}

}
class Node{
	public int idx;
	public int second;

	Node(int idx, int second){
		this.idx = idx;
		this.second = second;
	}
}

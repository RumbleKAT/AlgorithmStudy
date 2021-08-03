import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static boolean [] visited;
    static ArrayList<Node> [] nodes;
    static PriorityQueue<NodeComp> pq;
    static int max;
    static int max_idx;

    public static void main(String[] args) throws Exception {
	   // System.setIn(new FileInputStream("C:\\Users\\reki3\\IdeaProjects\\1967\\src\\com\\company\\sample.txt"));
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer token = new StringTokenizer(br.readLine());

	    N = Integer.parseInt(token.nextToken());

	    visited = new boolean[N+1];
	    nodes = new ArrayList[N+1];
	    for(int i=0;i<=N;i++){
	        nodes[i] = new ArrayList<>();
        }
	    pq = new PriorityQueue<>();

	    for(int i=1;i<N;i++){
	        token = new StringTokenizer(br.readLine());
	        int s = Integer.parseInt(token.nextToken());
	        int e = Integer.parseInt(token.nextToken());
	        int val = Integer.parseInt(token.nextToken());

            nodes[s].add(new Node(e,val));
            nodes[e].add(new Node(s,val));
        }
        max = 0;
        max_idx = 0;

        dfs(1,0, 0);

//	    NodeComp cur = pq.poll();
	    int s = max_idx;
        max = 0;

	    pq.clear();
        Arrays.fill(visited,false);

        dfs2(s,0,0);

        System.out.println(max);

    }
    static void dfs(int start, int val, int depth){
        //가중치가 가장 큰 노드를 찾는다.
        if(visited[start]) return;
        visited[start] = true;

        if(max < val){
            max = val;
            max_idx = start;
        }

        for(Node node : nodes[start]){
            if(!visited[node.idx]){
                dfs(node.idx, val + node.val, depth + 1);
            }
        }
    }
    static void dfs2(int start, int val, int depth){
        if(visited[start]) return;
        visited[start] = true;

        max = Math.max(max, val);

        for(Node node : nodes[start]){
            if(!visited[node.idx]){
                dfs2(node.idx, val + node.val, depth + 1);
            }
        }
    }

}
class Node{
    public int idx;
    public int val;

    Node(int idx, int val){
        this.idx = idx;
        this.val = val;
    }
}

class NodeComp implements Comparable<NodeComp>{
    public int idx;
    public int level;
    public int val;

    NodeComp(int idx , int level, int val){
        this.idx = idx;
        this.level = level;
        this.val = val;
    }

    @Override
    public int compareTo(NodeComp o) {
        if(this.level == o.level){
            return o.val - this.val;
        }
        return o.level - this.level;
    }

}

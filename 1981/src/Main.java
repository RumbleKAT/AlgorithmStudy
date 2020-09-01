import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int [][] map;
	static ArrayList<Integer> alist;
	static int [][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("C:\\Users\\root\\eclipse-workspace\\1981\\sample.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(br.readLine());
	
		N = Integer.parseInt(token.nextToken());
		alist = new ArrayList<>();
		
		map = new int [N+1][N+1];
		
		for(int i=1;i<=N;i++) {
			token = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) {
				map[i][j] = Integer.parseInt(token.nextToken());
				if(!alist.contains(map[i][j])) {
					alist.add(map[i][j]);
				}
			}
		}
		
		Collections.sort(alist);
		
		int left  = 0;
		int right = 0;
		int ans = Integer.MAX_VALUE;
		
		while(left < alist.size() && right < alist.size()) {
			if(bfs(alist.get(left), alist.get(right))) {
				int mid = alist.get(right) - alist.get(left);
				ans = Math.min(ans, mid);
				left++;
			}else {
				right++;
			}
		}
		
		System.out.println(ans);
		
	}

	private static boolean bfs(int left, int right) {
		if(map[1][1]< left || map[1][1] > right) return false;
		
		boolean [][] visited = new boolean[N+1][N+1];
		Queue<Node> que = new LinkedList<>();
		
		que.add(new Node(1,1));
		visited[1][1] = true;
		
		while(!que.isEmpty()) {
			Node current = que.poll();
			
			if(current.idx == N && current.val == N) return true;
			
			for(int i=0;i<4;i++) {
				int nextY = current.idx + dir[i][0];
				int nextX = current.val + dir[i][1];
				
				if(nextX >=1 && nextX <=N && nextY>=1 && nextY <= N) {
					if(visited[nextY][nextX]) continue;
					
					if(map[nextY][nextX] >= left && map[nextY][nextX] <= right) {
						visited[nextY][nextX] = true;
						que.add(new Node(nextY,nextX));
					}
				}
			}
		}
		return false;

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
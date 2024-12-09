import java.util.*;
class Main {
	public int app(int n, int[][] flights, int s, int e, int k){
		int answer = Integer.MAX_VALUE;
		
		List<Node>[] nodes = new ArrayList[n];
		for(int i=0;i<n;i++){
			nodes[i] = new ArrayList<>();
		}

		for(int i=0;i<flights.length;i++){
			int start = flights[i][0];
			int end = flights[i][1];
			int val = flights[i][2];
			nodes[start].add(new Node(end,val));
		}
		List<Node> tmp = nodes[s];
		PriorityQueue<Node> pq = new PriorityQueue<>();

		for(Node start: tmp){
			pq.add(new Node(start.e, start.val, k));
		}

		while(!pq.isEmpty()){
			Node cur = pq.poll();
			if(cur.e == e){
				answer = Math.min(answer, cur.val);
				break;
			}			
			for(Node start: nodes[cur.e]){
				if(cur.change-1 >= 0){
					pq.add(new Node(start.e, cur.val + start.val, cur.change-1));	
				}
			}
		}
		if(answer == Integer.MAX_VALUE){
			answer = -1;
		}
		return answer;
	}
		
	public static void main(String[] args){
		Main T = new Main();
		System.out.println(T.app(5, new int[][]{{0, 1, 10}, {1, 2, 20}, {0, 2, 70}, {0, 3, 100}, {1, 3, 80}, {2, 3, 10}, {2, 4, 30}, {3, 4, 10}}, 0, 3, 1));
		System.out.println(T.app(4, new int[][]{{0, 1, 10}, {0, 2, 10}, {1, 3, 5}, {2, 3, 3}}, 0, 3, 0));
		System.out.println(T.app(8, new int[][]{{0, 3, 10}, {1, 5, 10}, {1, 7, 100}, {0, 1, 10}, {0, 2, 10}, {5, 7, 30}, {3, 7, 10}, {1, 3, 5}, {2, 3, 3}}, 1, 7, 2));
		System.out.println(T.app(10, new int[][]{{1, 8, 50}, {0, 8, 30}, {1, 0, 10}, {2, 8, 10}, {0, 3, 10}, {1, 5, 10}, {1, 7, 100}, {0, 1, 10}, {0, 2, 10}, {5, 7, 30}, {3, 7, 10}, {1, 3, 5}, {2, 3, 3}}, 1, 8, 2));
		System.out.println(T.app(4, new int[][]{{0, 3, 59},{2, 0, 83}, {3, 1, 16}, {1, 3, 16}}, 3, 0, 3));
	}
}
class Node implements Comparable<Node>{
	public int e;
	public int val;
	public int change;

	Node(int e, int val){
		this.e = e;
		this.val = val;
	}

	Node(int e, int val, int change){
		this.e = e;
		this.val = val;
		this.change = change;
	}

	@Override
	public int compareTo(Node o) {
		return this.val - o.val;
	}
}
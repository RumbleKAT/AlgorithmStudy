import java.io.*;
import java.util.*;

/*
거의 최단 경로
https://www.acmicpc.net/problem/5719
 */
public class Main {
	static int n,m;
	static int start, dest;
	static List<List<Node>> roads;
	static int[] dist;
	static List<List<Node>> parents;
	public static void main(String[] args) throws IOException {
		InputReader reader = new InputReader();
//		InputReader reader = new InputReader();
		StringBuilder sb = new StringBuilder();
		while(true) {
			String line = reader.readLine();
			if(line.equals("0 0")) break;

			StringTokenizer st = new StringTokenizer(line);
			n = Integer.parseInt(st.nextToken()); m = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(reader.readLine());
			start = Integer.parseInt(st.nextToken()); dest = Integer.parseInt(st.nextToken());

			roads = new ArrayList<>();	parents = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				roads.add(new ArrayList<>());
				parents.add(new ArrayList<>());
			}

			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(reader.readLine());

				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				roads.get(u).add(new Node(v, cost));
			}


			//최단 경로 탐색
			dist = new int[n]; Arrays.fill(dist, Integer.MAX_VALUE);
			diijkstra(start, true);
			//최단 경로 삭제
			deleteShortestPath(dest);
			//거의 최단 경로 탐색
			dist = new int[n]; Arrays.fill(dist, Integer.MAX_VALUE);
			diijkstra(start, false);

			int ret = dist[dest];
			if(ret == Integer.MAX_VALUE) sb.append(-1+"\n");
			else sb.append(ret+"\n");
		}
		sb.deleteCharAt(sb.length()-1);
		System.out.println(sb.toString());
	}
	//bfs방식으로 바꿀 것.
	private static void deleteShortestPath(int dest_id) {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] discovered = new boolean[n];

		q.add(dest_id);
		discovered[dest_id] = true;
		while(!q.isEmpty()) {
			int here_id = q.poll();

			for (int i = 0; i < parents.get(here_id).size(); i++) {
				Node parent = parents.get(here_id).get(i);
				if(!discovered[parent.id]) {
					discovered[parent.id] = true;
					q.add(parent.id);
				}
				removeEdge(parent.id, here_id);
			}
		}
	}
	private static void removeEdge(int parent, int childTarget) {
		for (int i = 0; i < roads.get(parent).size(); i++) {
			Node child = roads.get(parent).get(i);
			if(child.id == childTarget) {
				roads.get(parent).set(i,new Node(childTarget,-1)); break;
			}
		}
	}


	public static void diijkstra(int start, boolean saveParent){
		PriorityQueue<Node> q = new PriorityQueue<>(Comparator.comparing(node -> node.cost));
		q.add(new Node(start,0));
		dist[start] = 0;

		while(!q.isEmpty()) {
			Node here = q.poll();
			if(here.cost > dist[here.id]) continue;

			for (int i = 0; i < roads.get(here.id).size(); i++) {
				Node there = roads.get(here.id).get(i);
				if(there.cost == -1) continue;
				int nextCost = here.cost + there.cost;

				if(nextCost < dist[there.id]) {
					// 더 짧은 경로를 발견하면 부모 리스트 초기화하고 다시 저장
					if(saveParent) {
						parents.set(there.id, new ArrayList<>());
						parents.get(there.id).add(here);
					}
					q.add(new Node(there.id, nextCost));
					dist[there.id] = nextCost;
				}
				//같은 간선인 부모 저장
				if(saveParent && nextCost == dist[there.id]) parents.get(there.id).add(here);
			}
		}
	}
}

class Node {
	int id, cost;
	public Node(int id, int cost) {
		this.id = id;
		this.cost = cost;
	}
}

class InputReader {
	private BufferedReader br;

	public InputReader() {
		br = new BufferedReader(new InputStreamReader(System.in));
	}

	public InputReader(String filepath) {
		try {
			br = new BufferedReader(new FileReader(filepath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public boolean ready() throws IOException {
		return br.ready();
	}

	public String readLine() throws IOException {
		return br.readLine();
	}
	public int readInt() throws IOException {
		return Integer.parseInt(readLine());
	}
	public Long readLong() throws IOException {
		return Long.parseLong(readLine());
	}
}
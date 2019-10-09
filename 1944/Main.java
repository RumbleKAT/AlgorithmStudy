import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, K;
	static int[] par, d;
	static char[][] map;
	static int[][] key;
	static ArrayList<Node> nodelist;
	static ArrayList<Robot> list;

	static class Node {
		int s, e, val;

		public Node(int s, int e, int val) {
			this.s = s;
			this.e = e;
			this.val = val;
		}
	}

	static class Robot implements Comparable<Robot> {
		int x, y, val;

		public Robot(int x, int y, int val) {
			this.x = x;
			this.y = y;
			this.val = val;
		}

		public int compareTo(Robot o) {
			return this.val - o.val;
		}
	}

	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("sample.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new char[N + 1][N + 1];
		key = new int[N + 1][N + 1];

		nodelist = new ArrayList<>();
		list = new ArrayList<>();

		char[] temp = null;
		int cnt = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			temp = st.nextToken().toCharArray();
			for (int j = 0; j < N; j++) {
				map[i][j] = temp[j];
				if (temp[j] == 'S' || temp[j] == 'K') {
					key[i][j] = ++cnt;
					nodelist.add(new Node(i, j, 0));
				}
			}
		}

		boolean isTrue = true;
		for (Node n : nodelist) {
			isTrue = bfs(n);
			if (isTrue == false) {
				break;
			}
		}

		if (isTrue == false) {
			System.out.println("-1");
		} else {
			par = new int[cnt + 1];
			for (int i = 1; i <= cnt; i++) {
				par[i] = i;
			}

			Collections.sort(list);

			int ans = 0;
			for (Robot R : list) {
				if (find(R.x) != find(R.y)) {
					union(R.x, R.y);
					ans += R.val;
				}
			}

			System.out.println(ans);
		}
	}

	private static void union(int x, int y) {
		x = find(x);
		y = find(y);
		par[y] = x;
	}

	private static int find(int i) {
		if (par[i] == i) {
			return i;
		} else {
			int p = find(par[i]);
			par[i] = p;
			return p;
		}
	}

	private static boolean bfs(Node n) {
		boolean[][] visited = new boolean[N + 1][N + 1];
		Queue<Node> que = new LinkedList<>();
		que.add(n);

		int keyCnt = 0;
		while (!que.isEmpty()) {
			Node preN = que.poll();
			int preS = preN.s;
			int preE = preN.e;
			int cnt = preN.val + 1;

			if (visited[preS][preE]) {
				continue;
			}
			visited[preS][preE] = true;

			for (int i = 0; i < 4; i++) {
				int nX = preS + dx[i];
				int nY = preE + dy[i];

				if (nX < 0 || nX >= N || nY < 0 || nY >= N) {
					continue;
				}
				if (visited[nX][nY] || map[nX][nY] == '1') {
					continue;
				}

				if (map[nX][nY] == 'S' || map[nX][nY] == 'K') {
					list.add(new Robot(key[n.s][n.e], key[nX][nY], cnt));
					keyCnt++;
					if (keyCnt == K) {
						return true;
					}
				}

				que.offer(new Node(nX, nY, cnt));
			}

		}

		return false;
	}

}

import java.util.*;
import java.io.*;

class Main{

	static ArrayList <Integer> [] arr;
    static int MAX = 1000001;
    static int MD = 22;
    static int[][] parent = new int[MAX][MD+1];
	static int[] depth = new int[MAX];
	static int N,M;
    static boolean [] visited = new boolean[MAX];

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token;
		N = Integer.parseInt(br.readLine());
		
		arr = new ArrayList[N+1];
        for(int i = 0;i<arr.length;i++){
            arr[i] = new ArrayList<>();
        }

        for(int i =1;i<N;i++){
            token = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(token.nextToken());
            int b = Integer.parseInt(token.nextToken());

            arr[a].add(b);
            arr[b].add(a);            
            
		}
		
		token = new StringTokenizer(br.readLine());
	 
		M = Integer.parseInt(token.nextToken());
		
		dfs(1,0);
		setParent();

		for(int i = 1;i<=M;i++){
			token = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(token.nextToken());
			int e = Integer.parseInt(token.nextToken());

			System.out.println(lca(s, e));
		}

	}

	static void dfs(int here, int d) {
        visited[here] = true;
        depth[here] = d;
        for(int next : arr[here]){
            if(visited[next]){
                continue;
            }
            parent[next][0] = here;
            dfs(next, d+1);
        }
    }

	static void setParent(){
		for(int j = 1;j<21;j++){
			for(int i=1;i<=N;i++){
				parent[i][j] = parent[parent[i][j-1]][j-1];
			}
		}
	}

	static int lca(int a, int b){
		if(depth[a] > depth[b]){
			int tmp = depth[a];
			depth[a] = depth[b];
			depth[b] = tmp;
		}

		for(int i = 20;i>=0;i--){
			if(depth[b] - depth[a] >= (1<<i)){
				b = parent[b][i];
			}
		}

		if(a == b) return a;

		for(int i =20;i>=0;i--){
			if(parent[a][i] != parent[b][i]){
				a = parent[a][i];
				b = parent[b][i];
			}
		}

		return parent[a][0];
	}
}
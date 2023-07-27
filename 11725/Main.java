import java.util.*;
import java.io.*;

class Main{

    static int N;
    static ArrayList<Integer> [] nodes;
    static int [] visited;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        nodes = new ArrayList[N+1];
        visited = new int[N+1];
        for(int i=0;i<=N;i++) nodes[i] = new ArrayList<>();
        for(int i=0;i<N-1;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());

            nodes[s].add(e);
            nodes[e].add(s);
        }

        visited[1] = 0;
        dfs(1);
        
        for(int i=2;i<=N;i++){
            System.out.println(visited[i]);
        }
    }
    static void dfs(int parent){
        for(int node : nodes[parent]){
            if(visited[node] == 0){
                visited[node] = parent;
                dfs(node);
            }
        }
    }
}
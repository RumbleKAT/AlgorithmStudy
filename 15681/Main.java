import java.util.*;
import java.io.*;

class Main{
    static int N, R, Q;
    static List<List<Integer>> nodes;
    static boolean [] visited;
    static int[] subtreeSize;

    static int count;
    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        R = Integer.parseInt(token.nextToken());
        Q = Integer.parseInt(token.nextToken());

        nodes = new ArrayList<>();
        for(int i=1;i<=N+1;i++){
            nodes.add(new ArrayList<>());
        }

        visited = new boolean[N+1];
        subtreeSize = new int[N+1]; 


        for(int i=1;i<=N-1;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());

            nodes.get(s).add(e);
            nodes.get(e).add(s);
        }

        dfs(R);

        for(int i=1;i<=Q;i++){
            Arrays.fill(visited, false);
            token = new StringTokenizer(br.readLine());
            int current_root = Integer.parseInt(token.nextToken());
            System.out.println(subtreeSize[current_root]);
        }

    }
    static int dfs(int current_root){
        if(visited[current_root]) return 0;
        visited[current_root] = true;

        int size = 1;
    
        for(int next: nodes.get(current_root)){
            if(!visited[next]){
                size += dfs(next);
            }
        }

        subtreeSize[current_root] = size;
        return size;
    }
}
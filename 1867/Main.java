import java.io.*;
import java.util.*;

class Main{

    static int N,K;
    static ArrayList<Integer> [] edges;
    static int [] matched;
    static boolean [] visited;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(token.nextToken());

        edges = new ArrayList [N+1];
        matched = new int [N+1];
        visited = new boolean [N+1];

        for(int i = 0;i<edges.length;i++){
            edges[i] = new ArrayList<Integer>();
        }

        for(int i =1;i<=K;i++){
            token = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(token.nextToken());
            int b = Integer.parseInt(token.nextToken());
            
            edges[a].add(b);
        }

        Arrays.fill(matched, -1);
        int ans = 0;

        for(int i =1;i<=N;i++){
            //Arrays.fill(visited, false);
            if(dfs(i)){
                ++ans;
            }
        }
        System.out.println(ans);
    }

    static boolean dfs(int currentIdx){
        if(visited[currentIdx]){
            return false;
        }
        visited[currentIdx] = true;

        for(int next : edges[currentIdx]){
            if(matched[next] == -1 || dfs(matched[next])){
                matched[next] = currentIdx;
                return true;
            }
        }
        return false;
    }
}
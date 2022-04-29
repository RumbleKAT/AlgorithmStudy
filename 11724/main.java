import java.io.*;
import java.util.*;

public class Main {

    static int [] visited;
    static ArrayList<Integer> [] nodes;
    static int N,M;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("C:\\Users\\reki3\\desktop\\Spring_Boot_Mission\\mission4\\11724\\src\\com\\company\\sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        nodes = new ArrayList[1001];
        visited = new int[1001];
        for(int i=1;i<=1000;i++){
            nodes[i] = new ArrayList<>();
        }
        Arrays.fill(visited,Integer.MAX_VALUE);

        for(int i=1;i<=M;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());
            nodes[s].add(e);
            nodes[e].add(s);
        }
        int cnt = 0;
        for(int i=1;i<=N;i++){
            if(visited[i] == Integer.MAX_VALUE){
                cnt++;
                visited[i] = i;
                bfs(i);
            }
        }
        System.out.println(cnt);
    }
    static void bfs(int param){
        Queue<Integer> que = new LinkedList<>();
        que.add(param);

        while(!que.isEmpty()){
            int cur = que.poll();
            for(int next : nodes[cur]){
                if(visited[next] == Integer.MAX_VALUE){
                    visited[next] = param;
                    que.add(next);
                }
            }
        }
    }
}
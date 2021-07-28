import java.util.*;
import java.io.*;

public class Main {

    static int N,M;
    static ArrayList<Integer> [] nodes;
    static boolean [] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());

        token = new StringTokenizer(br.readLine());
        M = Integer.parseInt(token.nextToken());

        nodes = new ArrayList[N+1];
        for(int i=0;i<nodes.length;i++){
            nodes[i] = new ArrayList<>();
        }
        visited = new boolean[N+1];

        for(int i=1;i<=M;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());

            nodes[s].add(e);
            nodes[e].add(s);
        }

        Queue<Integer> que = new LinkedList<>();
        que.add(1);
        int cnt = 0;

        while(!que.isEmpty()){
            int cur = que.poll();
            if(visited[cur]) continue;
            visited[cur] = true;
            if(cur != 1) cnt++;

            for(int next : nodes[cur]){
                if(!visited[next]){
                    que.add(next);
                }
            }
        }
        System.out.println(cnt);

    }
}

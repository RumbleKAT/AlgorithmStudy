import java.io.*;
import java.util.*;

class Main{

    static int N,S,E,M;
    static ArrayList<Node> [] nodes;
    static ArrayList<Integer> ans_nodes;
    static long [] dist;
    static long INF = -Integer.MAX_VALUE; //거리를 지나면서 -로 가기때문에 음의 INF 값으로 초기화한다.
    static boolean [] visited;
    static long [] cost;

    public static void main(String [] args) throws Exception{
        // System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        S = Integer.parseInt(token.nextToken());
        E = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        dist = new long [N+1];
        cost = new long [N+1];
        visited = new boolean[N+1];

        ans_nodes = new ArrayList<>();
        nodes = new ArrayList[N+1];

        for(int i=0;i<=N;i++){
            nodes[i] = new ArrayList<>();
        }

        for(int i=1;i<=M;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());
            long val = Long.parseLong(token.nextToken());

            nodes[s].add(new Node(e,-val));
        }
        
        token = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            cost[i] = Long.parseLong(token.nextToken());
        }

        dfs(S); //갈수 있냐 없냐 구분은 DFS
        if(!visited[E]){
            System.out.println("gg");
        }else{
            System.out.println(bellmanFord(S, E));
        }
    }
    static void dfs(int s){
        if(visited[s]) return;
        visited[s] = true;

        for(Node next : nodes[s]){
            if(!visited[next.idx]) dfs(next.idx);
        }
    }

    static String bellmanFord(int S, int E){
        Arrays.fill(dist, INF);
        dist[S] = cost[S];

        boolean check = false;

        for(int i=0;i<N-1;i++){ //정점의 갯수
            check = false;
            for(int j=0;j<N;j++){
                for(Node next : nodes[j]){
                    if(dist[j] != INF && dist[next.idx] < dist[j] + next.val + cost[next.idx]){
                        dist[next.idx] = dist[j] + next.val + cost[next.idx];
                        check = true;
                    }
                }
            }
            if(!check){
                break; // 더이상 변화가 일어나지 않을 경우, 반복문 해제
            }
        }
 
        //한번더 돌려서 변화가 일어날 경우, 그것은 사이클이 있는 경우이므로, 시작점과 끝점을 저장한다.
        for(int j=0;j<N;j++){
            for(Node next : nodes[j]){
                if(dist[j] != INF && dist[next.idx] < dist[j] + next.val + cost[next.idx]){
                    ans_nodes.add(j);
                    ans_nodes.add(next.idx);
                }
            }
        }

        if(!ans_nodes.isEmpty()){
            for(int node : ans_nodes){
                Arrays.fill(visited, false);
                dfs(node);
                if(visited[E]){
                   //도달할수 있는 경우, 끝점에서 돈을 무한대로 가지고 있다.
                    return "Gee";
                }
            }
        }
        
        //해당 노드를 제외하고 cost를 구한다.
        Arrays.fill(dist, INF);
        dist[S] = cost[S];

        for(int i=0;i<N-1;i++){ //정점의 갯수
            check = false;
            for(int j=0;j<N;j++){
                for(Node next : nodes[j]){
                    if(ans_nodes.contains(j) || ans_nodes.contains(next.idx) ) continue; //사이클을 도는 점들을 모두 제외한다.
                    if(dist[j] != INF && dist[next.idx] < dist[j] + next.val + cost[next.idx]){
                        dist[next.idx] = dist[j] + next.val + cost[next.idx];
                        check = true;
                    }
                }
            }
            if(!check){
                break; // 더이상 변화가 일어나지 않을 경우, 반복문 해제
            }
        }

        return dist[E]+"";
    }

}
class Node implements Comparable<Node>{
    public int idx;
    public long val;

    Node(int idx, long val){
        this.idx = idx;
        this.val = val;
    }

    @Override
    public int compareTo(Node next){
        return (int)(this.val - next.val);
    }
}
class NodeComp{
    public int s;
    public int e;

    NodeComp(int s, int e){
        this.s = s;
        this.e = e;
    }
}
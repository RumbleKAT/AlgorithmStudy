import java.util.*;
import java.io.*;

public class Main {
    
    static int N,M,V;
    static boolean [] visited;
    static LinkedList<Node> [] nodes;
    static StringBuilder sb_1;
    static StringBuilder sb_2;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt")); 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        V = Integer.parseInt(token.nextToken());

        nodes = new LinkedList[N+1];
        visited = new boolean[N+1];

        sb_1 = new StringBuilder();
        sb_2 = new StringBuilder();

        for(int i =1;i<=N;i++){
            nodes[i] = new LinkedList<>();
        }

        for(int i =1;i<=M;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());

            nodes[s].add(new Node(e));
            nodes[e].add(new Node(s));
        }

        for(int i=1;i<=N;i++){
            Collections.sort(nodes[i]);
        }

        dfs(V);
        Arrays.fill(visited, false);
        System.out.println(sb_1.toString());
        bfs(V);
        System.out.println(sb_2.toString());

    }
    static void dfs(int idx){
        Stack<Node> st = new Stack<>();
        st.push(new Node(idx));
        sb_1.append(idx+" ");

        while(!st.isEmpty()){
            Node current = st.pop();
            visited[current.idx] = true;

            for(Node node : nodes[current.idx]){
                if(!visited[node.idx]){
                    sb_1.append(node.idx+" ");
                    st.push(new Node(current.idx));
                    st.push(new Node(node.idx));
                    break;
                }
            }
        }
    }
    static void bfs(int idx){
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(idx));

        while(!que.isEmpty()){
            Node current = que.poll();            
            if(visited[current.idx]) continue;
            visited[current.idx] = true;

            sb_2.append(current.idx + " ");

            for(Node node : nodes[current.idx]){
                if(!visited[node.idx]){
                    que.add(new Node(node.idx));
                }
            }
        }
    }
}
class Node implements Comparable<Node>{
    public int idx;

    Node(int idx){
        this.idx = idx;
    }

    @Override
    public int compareTo(Node n1){
        return this.idx - n1.idx;
    }
}
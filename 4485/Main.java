import java.util.*;
import java.io.*;

class Main{

    static int N;
    static int [][] map;
    static int [][] adj;
    static int [][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
    static int MAX = 987654321;
    static int TC;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token;

        int idx = 1;
        
        while(true){
        token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());

        if(N == 0) break;

        map = new int[N+2][N+2];
        adj = new int[N+2][N+2];
        
        for(int i =1;i<=N;i++){
            token = new StringTokenizer(br.readLine());
            
            for(int j=1;j<=N;j++){
                map[i][j] = Integer.parseInt(token.nextToken()); 
            }
            Arrays.fill(adj[i], MAX);
        }

        PriorityQueue<Node> que= new PriorityQueue <>();

        adj[1][1] = map[1][1];
        que.add(new Node(1, 1, map[1][1])); //이전에 방문한 노드는 제외한다.

        long ans = MAX;

        while(!que.isEmpty()){
            Node current = que.poll();
            int x = current.x;
            int y = current.y;

             if(x == N && y == N){
                if(ans > current.val){
                    ans = current.val;
                }
                break;
             }

            for(int i =0;i<4;i++){
                int nextX = x + dir[i][0];
                int nextY = y + dir[i][1];
                
                if(nextX >=1 && nextY >=1 && nextX <= N && nextY <=N){
                    if(adj[nextY][nextX] > map[nextY][nextX] + current.val){
                        adj[nextY][nextX] = map[nextY][nextX] + current.val;
                        que.add(new Node(nextX, nextY, adj[nextY][nextX])); //이전에 방문한 노드는 제외한다.
                    }
                }
            }
        }
            
            bw.write("Problem "+idx+ ": " + ans+"\n");
            idx++;
            bw.flush();
        }
    }
}
class Node implements Comparable<Node>{
    public int x;
    public int y;
    public int val;

    Node(int x, int y, int val){
        this.x = x;
        this.y = y;
        this.val = val;
    }

    @Override
    public int compareTo(Node n1){
        return (int)(this.val - n1.val);
    }
}

import java.util.*;
import java.io.*;

class Main{
    static int K, W, H;
    static int [][] map;
    static int [][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
    static int [][] dir2 = {  {-2, -1}, {-2, +1},
    {+2, -1}, {+2, +1},
    {-1, -2}, {-1, +2},
    {+1, -2}, {+1, +2}};
    static boolean [][][] visited;

    public static void main(String[] args) throws Exception {
       System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        K = Integer.parseInt(token.nextToken());

        token = new StringTokenizer(br.readLine());
        W = Integer.parseInt(token.nextToken());
        H = Integer.parseInt(token.nextToken());
        
        map = new int[H+1][W+1];
        visited = new boolean[H+1][W+1][K+1];

        for(int i=1;i<=H;i++){
            token = new StringTokenizer(br.readLine());
            for(int j=1;j<=W;j++){
                map[i][j] = Integer.parseInt(token.nextToken());
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1,1,K,0));
        int min = Integer.MAX_VALUE;
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            visited[cur.y][cur.x][cur.k] = true;
            if(cur.y == H && cur.x == W){
                min = Math.min(min, cur.cnt);
                break;
            }
            
            for(int i=0;i<dir.length;i++){
                int nextY = dir[i][0] + cur.y;
                int nextX = dir[i][1] + cur.x;

                if(nextY>=1 && nextY <= H && nextX>=1 && nextX <= W){
                    if(map[nextY][nextX] == 0 && !visited[nextY][nextX][cur.k]){
                        visited[nextY][nextX][cur.k] = true;
                        pq.add(new Node(nextY, nextX, cur.k , cur.cnt+1));    
                    }
                }
            }
            if(cur.k > 0){
                for(int i=0;i<dir2.length;i++){
                    int nextY = dir2[i][0] + cur.y;
                    int nextX = dir2[i][1] + cur.x;
    
                    if(nextY>=1 && nextY <= H && nextX>=1 && nextX <= W){
                        if(map[nextY][nextX] == 0 && !visited[nextY][nextX][cur.k-1]){
                            visited[nextY][nextX][cur.k-1] = true;
                            pq.add(new Node(nextY, nextX, cur.k-1 , cur.cnt+1));    
                        }
                    }
                }
            }
        }

        if(min == Integer.MAX_VALUE){
            System.out.println(-1);
        }else{
            System.out.println(min);
        }
    }
}
class Node implements Comparable<Node>{
    public int y;
    public int x;
    public int k;
    public int cnt;

    public Node(int y, int x, int k){
        this.y = y;
        this.x = x;
        this.k = k;
    }

    public Node(int y, int x, int k, int cnt){
        this.y = y;
        this.x = x;
        this.k = k;
        this.cnt = cnt;
    }


    @Override
    public int compareTo(Node o) {
        return this.cnt - o.cnt;
    }
}
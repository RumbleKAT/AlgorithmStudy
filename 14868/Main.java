import java.util.*;
import java.io.*;

class Main{

    static int [][] map = new int [2001][2001];
    static int [][] visited = new int [2001][2001];
    static int [][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
    static int N,K;
    static int [] parent;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(token.nextToken());

        parent = new int[N+1];
        for(int i=1;i<=N;i++){
            parent[i] = i;   
        }

        Queue<Node> que = new LinkedList<>();

        for(int i=1;i<=K;i++){
            token = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(token.nextToken());
            int y = Integer.parseInt(token.nextToken());

            map[y][x] = i;
            que.add(new Node(y,x,i,0));
        }

        int cnt = 0;

        while(!que.isEmpty()){
            Node cur = que.poll();

            if(visited[cur.y][cur.x] > 0){

                if(checkParent()){
                    System.out.println(cur.cnt);
                    break;
                }
                
                int rootA = find(cur.idx);
                int rootB = find(map[cur.y][cur.x]);

                if(rootA != rootB){
                    parent[rootB] = rootA;
                    //전체 문명 중에서 다른 것들이 있는지 없는지를 그때마다 체크를 해줘야하는지?    
                }
                continue;
            }else{
                visited[cur.y][cur.x] = cur.idx;
            }

            for(int i=0;i<dir.length;i++){
                int nextY = dir[i][0] + cur.y;
                int nextX = dir[i][1] + cur.x;

                if(nextY >= 1 && nextY <= N && nextX >=1 && nextX <= N){

                    if(visited[nextY][nextX] == 0){
                        que.add(new Node(nextY,nextX,cur.idx));
                    }else{
                        int rootA = find(cur.idx);
                        int rootB = find(map[nextY][nextX]);
                        if(rootA != rootB){
                            que.add(new Node(nextY,nextX,cur.idx));
                        }
                    }
                }
            }
        }
        System.out.println(cnt);
    }
    static int find(int a){
        if(parent[a] == a) return a;
        else return parent[a] = find(parent[a]);
    }
}
class Node{
    public int y;
    public int x;
    public int idx;
    public int cnt;
    
    Node(int y, int x, int idx, int cnt){
        this.y = y;
        this.x = x;
        this.idx = idx;
        this.cnt = cnt;
    }
}
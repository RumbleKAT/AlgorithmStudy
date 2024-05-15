import java.util.*;
import java.io.*;

class Main{

    static int N, M, H;
    static int [][][] map;
    static int [][][] visited;
    static int[] dx = {0, 0, 0, 0, 1, -1};
    static int[] dy = {0, 0, 1, -1, 0, 0};
    static int[] dz = {1, -1, 0, 0, 0, 0};
    static Queue<Node> nodes;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        M = Integer.parseInt(token.nextToken());
        N = Integer.parseInt(token.nextToken());
        H = Integer.parseInt(token.nextToken());

        map = new int[H][N][M];
        visited = new int[H][N][M];
        nodes = new LinkedList<>();
        for(int i=0;i<H;i++){
            for(int j=0;j<N;j++){
                token = new StringTokenizer(br.readLine());
                for(int h=0;h<M;h++){
                    map[i][j][h] = Integer.parseInt(token.nextToken());
                    if(map[i][j][h] == 1){
                        nodes.add(new Node(j,h,i,0));
                    }
                }
            }
        }
        
        System.out.println(bfs());

    }

    private static int bfs() {
        int maxDays = 0;
        while(!nodes.isEmpty()){
            Node current = nodes.poll();

            for(int i=0;i<6;i++){
                int nz = current.h + dz[i];
                int ny = current.y + dy[i];
                int nx = current.x + dx[i];
                
                if (nz >= 0 && nz < H && ny >= 0 && ny < N && nx >= 0 && nx < M) {
                    if (map[nz][ny][nx] == 0) {
                        map[nz][ny][nx] = 1;
                        nodes.add(new Node(ny, nx, nz, current.date + 1));
                        maxDays = Math.max(maxDays, current.date + 1);
                    }
                }
            }
        }

        for(int h=0;h<H;h++){
            for(int n = 0;n<N;n++){
                for(int m = 0;m<M;m++){
                    if(map[h][n][m] == 0){
                        return -1;
                    }
                }
            }
        }
        return maxDays;
    }
}

class Node implements Comparable<Node>{
    public int y;
    public int x;
    public int h;
    public int date;

    Node(int y, int x, int h, int date){
        this.y = y;
        this.x = x;
        this.h = h;
        this.date = date;
    }

    @Override
    public int compareTo(Node o) {
        return this.date - o.date;
    }
}
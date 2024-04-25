import java.util.*;
import java.io.*;

class Main{
    static int w, h;
    static int [][] map;
    static int [][] visited;
    static int [][] dir = {{0,-1},{0,1},{-1,0},{1,0}, {-1,-1},{1,1},{-1,1},{1,-1}};

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./sample.txt"));        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token;

        while(true){
            token = new StringTokenizer(br.readLine());
            w = Integer.parseInt(token.nextToken());
            h = Integer.parseInt(token.nextToken());

            if(w == 0 && h == 0) break;
            if(w == 1 && h == 1){
                token = new StringTokenizer(br.readLine());
                System.out.println(Integer.parseInt(token.nextToken()));
                continue;
            }

            map = new int[h+1][w+1];
            visited = new int[h+1][w+1];

            for(int i=1;i<=h;i++){
                token = new StringTokenizer(br.readLine());
                for(int j=1;j<=w;j++){
                    map[i][j] = Integer.parseInt(token.nextToken());   
                }
            }
            int idx = 1;
            for(int i=1;i<=h;i++){
                for(int j=1;j<=w;j++){
                    if(map[i][j] == 1 && visited[i][j] == 0){
                        dfs(i,j, idx);
                        idx++;
                    }
                }
            }
            System.out.println(idx-1);
        }
    }
    static void dfs(int y,int x, int idx){
        visited[y][x] = idx;

        for(int i =0;i<dir.length;i++){
            int nextY = y + dir[i][0];
            int nextX = x + dir[i][1];
            if(nextY >= 1 && nextY <= h && nextX >= 1 && nextX <= w){
                if(map[nextY][nextX] == 1 && visited[nextY][nextX] == 0){
                    dfs(nextY,nextX,idx);
                }
            }
        }
    }
}
import java.util.*;
import java.io.*;

class Main{

    static int N;
    static char [][] map;
    static char [][] map2;
    static int [][] visited;
    static int [][] dir = {{0,1},{0,-1},{1,0},{-1,0}};

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());

        map = new char[N+1][N+1];
        map2 = new char[N+1][N+1];
        visited = new int[N+1][N+1];
 
        for(int i=1;i<=N;i++){
            token = new StringTokenizer(br.readLine());
            String cur = " " + token.nextToken();
            for(int j=1;j<=N;j++){
                map[i][j] = cur.charAt(j);
                map2[i][j] = map[i][j];
                if(map2[i][j] == 'R') map2[i][j] = 'G';
            }
        }
        int cnt = 1;
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                if(visited[i][j]==0){
                    dfs(i, j, cnt, map);
                    cnt++;
                }
            }
        }
        
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                visited[i][j] = 0;
            }
        }

        int r_cnt = 1;

        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                if(visited[i][j]==0){
                    dfs(i, j, r_cnt, map2);
                    r_cnt++;
                }
            }
        }

        System.out.println((cnt-1) + " " + (r_cnt-1));            
    }
    static void dfs(int y,int x, int cnt, char [][] map){
        visited[y][x] = cnt;
        
        for(int i=0;i<dir.length;i++){
            int nextY = y + dir[i][0];
            int nextX = x + dir[i][1];

            if(nextY >= 1 && nextY <= N && nextX >= 1 && nextX <= N){
                if(map[y][x] == map[nextY][nextX] && visited[nextY][nextX] == 0){
                    dfs(nextY,nextX, cnt, map);
                }
            }
        }
    }
}
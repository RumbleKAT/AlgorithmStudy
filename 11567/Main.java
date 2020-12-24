import java.util.*;
import java.io.*;

class Main{
    static int N,M;
    static char [][] map;
    static int [][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
    static int r1,r2,c1,c2;

    public static void main(String [] args)throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        map = new char[501][501];
        
        for(int i=1;i<=N;i++){
            token = new StringTokenizer(br.readLine());
            String temp = " " +token.nextToken();
            for(int j=1;j<=M;j++){
                map[i][j] = temp.charAt(j);
                // if(map[i][j] == 'X') visited[i][j] = true;
            }
        }
        token = new StringTokenizer(br.readLine());
        r1 = Integer.parseInt(token.nextToken());
        c1 = Integer.parseInt(token.nextToken());

        token = new StringTokenizer(br.readLine());
        r2 = Integer.parseInt(token.nextToken());
        c2 = Integer.parseInt(token.nextToken());

        Queue<Node> que = new LinkedList<>();
        que.add(new Node(r1,c1));
    
        boolean check = true;

        outter:
        while(!que.isEmpty()){
            Node cur = que.poll();

            for(int i=0;i<dir.length;i++){
                int nextX = dir[i][0] + cur.x;
                int nextY = dir[i][1] + cur.y;

                if(nextX >=1 && nextX <=N && nextY>= 1 && nextY <= M){
                    if(nextX == r2 && nextY == c2){
                        if(map[nextX][nextY] == '.'){
                            map[nextX][nextY] = 'X';
                            que.add(new Node(nextX,nextY));
                        }else{
                            check= false;
                            break outter;
                        }
                    }
                    else if(map[nextX][nextY] == '.'){
                        map[nextX][nextY] = 'X';
                        que.add(new Node(nextX,nextY));
                    }
                }
            }
            // printMap();
        }

        if(!check){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }


    }
    static void printMap(){
        System.out.println();
        for(int i=1;i<=N;i++){
            for(int j=1;j<=M;j++){
                System.out.print(map[i][j]);
            }System.out.println();
        }
    }
}
class Node{
    public int x;
    public int y;

    Node(int x, int y){
        this.x = x;
        this.y = y;
    }
}
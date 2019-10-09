import java.util.*;
import java.io.*;

class Main{

    static int [][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
    static int [][] Map = new int [101][101];
    static int [][] adj = new int [101][101];
    static boolean [][] visited = new boolean[101][101];
    static int N,M;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());

        M = Integer.parseInt(token.nextToken());

        N = Integer.parseInt(token.nextToken());

        for(int i=1;i<=N;i++){
            String str = br.readLine();
            for(int j =1;j<=M;j++){
                Map[i][j] = str.charAt(j-1)-'0';
            }
        }

        // printMap();

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1,1,0));
        visited[1][1] = true;

        long min = 987654321;

        while(!pq.isEmpty()){
            Node current = pq.poll();
            // System.out.println(current.y + " " + current.x);

            if(current.x == M && current.y == N){
                min = current.cnt;
                break;
            }
            
            for(int i =0;i<dir.length;i++){
                int nextX = current.x + dir[i][0];
                int nextY = current.y + dir[i][1];

                if(nextX >=1 && nextY >=1 && nextX <= M && nextY <=N){

                    if(Map[nextY][nextX] == 1 && !visited[nextY][nextX]){
                        visited[nextY][nextX] = true;
                        pq.add(new Node(nextX,nextY,current.cnt+1));

                    }
                     if(Map[nextY][nextX] == 0&& !visited[nextY][nextX]){
                        visited[nextY][nextX] = true;
                        pq.add(new Node(nextX,nextY,current.cnt));
                    }
                    
                }
            }
        }

        bw.write(min+"\n");
        bw.flush();
    }
    static void printMap(){
        for(int i =1;i<=N;i++){
            for(int j=1;j<=M;j++){
                System.out.print(Map[i][j]+ " ");
            }System.out.println();
        }
    }
}
class Node implements Comparable<Node>{
    public int x;
    public int y;
    public int cnt = 0;

    Node(int x, int y, int cnt){
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }

    @Override
    public int compareTo(Node n1){
        return this.cnt - n1.cnt;
    }
}
import java.util.*;
import java.io.*;

public class Main {

    static int [][] map;
    static boolean [][] visited;
    static ArrayList<Node> nodes;
    static Queue<NodeComp> que;    
    static int N, K , L;
    static Queue<NodeComp> rear;//뱀의 몸길이

    static int [][] rotate ={{0,1},{0,-1},{1,0},{-1,0}};
    
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());

        map = new int[N+2][N+2];
        visited = new boolean[N+2][N+2];

        token = new StringTokenizer(br.readLine());
        K = Integer.parseInt(token.nextToken());

        nodes = new ArrayList<>();
        que = new LinkedList<>();

        for(int i=0;i<=N+1;i++){
            for(int j=0;j<=N+1;j++){
                if(i>=1 && i<=N && j>=1 && j<=N) continue;
                visited[i][j] = true;
            }
        }

        // for(int i=0;i<=N+1;i++){
        //     for(int j=0;j<=N+1;j++){
        //         System.out.print(visited[i][j] + " ");
        //     }System.out.println();
        // }


        for(int i=1;i<=K;i++){
            token = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(token.nextToken());
            int x = Integer.parseInt(token.nextToken());
            visited[y][x] = true;
        }
        
        token = new StringTokenizer(br.readLine());
        L = Integer.parseInt(token.nextToken());

        for(int i=1;i<=L;i++){
            token = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(token.nextToken());
            char temp = token.nextToken().charAt(0);
            nodes.add(new Node(idx,temp));
        }

        que.add(new NodeComp(1, 1, 1));
        rear = new LinkedList<>();
        rear.add(new NodeComp(1,1,0));

        while(!que.isEmpty()){
            NodeComp node = que.poll();

            

            System.out.println(node.y + " " + node.x);
        }



    }
    
}
class Node{
    public int second;
    public char rotate;
    
    Node(int second, char rotate){
        this.second = second;
        this.rotate = rotate;
    }
}
class NodeComp implements Comparable<NodeComp>{
    public int y;
    public int x;
    public int dist;

    NodeComp(int y, int x, int dist){
        this.y = y;
        this.x = x;
        this.dist = dist;
    }

    @Override
    public int compareTo(NodeComp node){
        return this.dist - node.dist;
    }
}
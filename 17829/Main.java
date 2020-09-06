import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int [][] map = new int [1025][1025];
    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());

        for(int i=0;i<N;i++){
            token = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(token.nextToken());
            }
        }
        // System.out.println(ans);

        while(N>1){
            for(int i=0;i<N;i+=2){
                for(int j=0;j<N;j+=2){
                    map[i/2][j/2] = secondMax(i, j);
                }
            }
            N /= 2;
        }

        System.out.println(map[0][0]);
    }
    static int secondMax(int sx, int sy){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        for(int i=sy;i<sy+2;i++){
            for(int j=sx;j<sx+2;j++){
                pq.add(new Node(map[i][j]));
            }
        }
        pq.poll();         
        return pq.poll().val;
    }
}
class Node implements Comparable<Node>{
    public int val;
    Node(int val){
        this.val = val;
    }
    @Override
    public int compareTo(Node node){
        return node.val - this.val;
    }
}

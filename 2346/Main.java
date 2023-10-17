import java.util.*;
import java.io.*;

class Main {

    static int N;
    static Deque<Node> deque;
    static int [] nodes;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        deque = new ArrayDeque<Node>();
        nodes = new int[N+1];
        
        token = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++){
            nodes[i] = Integer.parseInt(token.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        sb.append("1 ");
        int in = nodes[1];

        for(int i=2;i<=N;i++){
            deque.add(new Node(i,nodes[i]));
        }

        while(!deque.isEmpty()){
            if(in > 0){
                for(int i=1;i<in;i++){
                   deque.add(deque.poll()); 
                }

                Node next = deque.poll();
                in = next.step;
                sb.append(next.idx + " ");
            }else{
                for(int i=1;i<-in;i++){
                   deque.addFirst(deque.pollLast()); 
                }
                Node next = deque.pollLast();
                in = next.step;
                sb.append(next.idx + " ");
            }
        }

        System.out.println(sb.toString());
        
    }

}
class Node{
    public int idx;
    public int step;

    Node(int idx, int step){
        this.idx = idx;
        this.step = step;
    }
}
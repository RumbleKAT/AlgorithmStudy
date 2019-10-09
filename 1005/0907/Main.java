import java.util.*;
import java.io.*;

class Main{
    static int TC;
    static int N,K;
    static long [] time;
    static int W;
    static LinkedList<Node> [] arr;
    static int [] past;
    static long [] dist;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());
            N = Integer.parseInt(token.nextToken());
            K = Integer.parseInt(token.nextToken());

            past = new int [N+1];
            time = new long [N+1];
            dist = new long [N+1];
            arr = new LinkedList[N+1];
            for(int i =1;i<=N;i++){
                arr[i] = new LinkedList<>();
            }

            for(int i =1;i<=K;i++){
                token = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(token.nextToken());
                int e = Integer.parseInt(token.nextToken());

                arr[s].add(new Node(e));
                past[e]++;                
            }

            Queue<Node> que = new LinkedList<>();

            for(int i=1;i<=N;i++){
                if(past[i] == 0) que.add(new Node(i));
            }

            while(!que.isEmpty()){
                Node current = que.poll();
                System.out.print(current.idx + " ");
                for(Node node : arr[current.idx]){
                    // System.out.println(node.idx);
                    if(--past[node.idx] == 0) que.add(node);
                }
            }System.out.println();
            

        }

}
class Node{
    public int idx;

    Node(int idx){
        this.idx = idx;
    }
}
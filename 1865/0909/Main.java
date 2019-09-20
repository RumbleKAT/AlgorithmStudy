import java.util.*;
import java.io.*;

class Main{

    static int TC;
    static int N,M,K;
    static int MAX = 987654321;
    static LinkedList<Node> [] arr;
    static long [] adj;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        TC = Integer.parseInt(token.nextToken());

        for(int t=1;t<=TC;t++){
            token = new StringTokenizer(br.readLine());

            N = Integer.parseInt(token.nextToken());
            M = Integer.parseInt(token.nextToken());    
            K = Integer.parseInt(token.nextToken());

            arr = new LinkedList[N+1];
            for(int i=1;i<=N;i++){
                arr[i] = new LinkedList<>();
            }
            adj = new long [N+1];

            Arrays.fill(adj, MAX);

            for(int i=1;i<=M+K;i++){
                token = new StringTokenizer(br.readLine());

                int s = Integer.parseInt(token.nextToken());
                int e = Integer.parseInt(token.nextToken());

                long val = Long.parseLong(token.nextToken());
                
                if(i>M){
                    val *= -1;
                    arr[s].add(new Node(e,val));
                }else{
                    arr[s].add(new Node(e,val));
                    arr[e].add(new Node(s,val));
                }
            }

            boolean result = false;

            adj[1] = 0;
            for(int i=1;i<=N;i++){
                for(int j =1;j<=N;j++){
                    for(Node node : arr[j]){
                        if(adj[j] != MAX && adj[node.idx] > adj[j] + node.val){
                            adj[node.idx] = adj[j] + node.val;
                            if(i == N) result = true;
                        }
                    }
                }
            }

            if(result)System.out.println("YES"); 
            else System.out.println("NO");


        }

    
    }
}
class Node{
    public int idx;
    public long val;
    
    Node(int idx, long val){
        this.idx = idx;
        this.val = val;
    }

}
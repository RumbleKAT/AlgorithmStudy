import java.util.*;
import java.io.*;


class Main{

    static int N,M;
    static Node [] arr;
    static long [] adj;
    static final long INF = Integer.MAX_VALUE;


    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        arr = new Node [6000];
        adj = new long[501];

        Arrays.fill(adj,INF);//아직 접근을 안했기 때문에 무한대로 취급

        for(int i =0;i<M;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());
            int v = Integer.parseInt(token.nextToken());

            arr[i] = new Node(s,e,v);
        }

        adj[1] = 0;

        boolean negative_cycle = false;

        for(int i = 0;i<N; i++){
            for(int j = 0; j<M;j++){
                int start = arr[j].start;
                int end = arr[j].end;
                long value = arr[j].value;

                if(adj[start] != INF && adj[end] > adj[start] + value){
                    adj[end] = adj[start] + value;
                    if(i == N-1){
                        negative_cycle = true;
                    } 
                }
            }
        }

        if(negative_cycle){
            System.out.println(-1);
        }else{
            for(int i = 2;i<=N;i++){
                System.out.printf("%d\n", adj[i] == Integer.MAX_VALUE ? -1 : adj[i]);
            }
        }
    }
    
}
class Node{
    public int start;
    public int end;
    public int value;

    Node(int start, int end, int value){
        this.start = start;
        this.end = end;
        this.value = value;
    }
}
import java.util.*;
import java.io.*;


class Main{

    static int N,M,W;
    static Node [] arr;
    static long [] adj;
    static final long INF = Integer.MAX_VALUE;


    public static void main(String [] args) throws Exception{
      //  System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        
        int TC = Integer.parseInt(token.nextToken());

        for(int t= 1;t<=TC;t++){
         
            token = new StringTokenizer(br.readLine());
            N = Integer.parseInt(token.nextToken());
            M = Integer.parseInt(token.nextToken());
            W = Integer.parseInt(token.nextToken());
            
            arr = new Node [6000];
            adj = new long[501];
    
            Arrays.fill(adj,INF);//아직 접근을 안했기 때문에 무한대로 취급
            
            
            int edge_count =0;
            
            for(int i =1;i<=M;i++){
                token = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(token.nextToken());
                int e = Integer.parseInt(token.nextToken());
                int v = Integer.parseInt(token.nextToken());
                arr[++edge_count] = new Node(s,e,v);
                arr[++edge_count] = new Node(e,s,v);
            }
            //양방향이기때문에 선분을 더 추가해준다. s -> e / e -> s

            for(int i =1;i<=W;i++){
                token = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(token.nextToken());
                int e = Integer.parseInt(token.nextToken());
                int v = Integer.parseInt(token.nextToken());
                v *= -1;
                arr[++edge_count] = new Node(s,e,v);
            }
    
            adj[1] = 0;
    
            boolean negative_cycle = false;
            
            edge_count = M*2 +W;
    
            for(int i = 1;i<=N; i++){
                for(int j = 1; j<= edge_count;j++){
                    int start = arr[j].start;
                    int end = arr[j].end;
                    long value = arr[j].value;
    
                    if(adj[start] != INF && adj[end] > adj[start] + value){
                        adj[end] = adj[start] + value;
                        if(i == N){
                            negative_cycle = true;
                        } 
                    }
                }
            }
    
            if(negative_cycle){
                System.out.println("YES");
            }else{
                System.out.println("NO");
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
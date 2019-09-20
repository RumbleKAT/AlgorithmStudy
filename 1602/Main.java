import java.io.*;
import java.util.*;

class Main{
    
    static int N,M,Q;
    static long [][] dp;
    static long [][] dist;
    static Node [] arr;
    static int [] delay;
    static long max = Integer.MAX_VALUE;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        Q = Integer.parseInt(token.nextToken());
        
        dp = new long[N+1][N+1];
        dist = new long[N+1][N+1];
        arr = new Node[N+1];
        delay = new int[N+1];

        token = new StringTokenizer(br.readLine());

        for(int i=1;i<=N;i++){
            int temp = Integer.parseInt(token.nextToken());
            delay[i] = temp;
            arr[i] = new Node(i,delay[i]);
        }
        
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                if(i == j){
                    dp[i][j] = 0;
                    dist[i][j] = 0;
                }
                else {
                    dp[i][j] = max;
                    dist[i][j] = max;
                }
            }
        }
        
        for(int i=1;i<=M;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());
            long val = Long.parseLong(token.nextToken());

            dist[s][e] = val;
            dist[e][s] = val;
        }

        Arrays.sort(arr,1,N+1);

        //거쳐가는 노드
        for(int k=1;k<=N;k++){
            // System.out.println( k +" " + arr[k]);
            int w = arr[k].idx;

            for(int i =1;i<=N;i++){

                for(int j=1;j<=N;j++){
                    dist[i][j] = Math.min(dist[i][j], dist[i][w] + dist[w][j]);
                    // System.out.println(arr[w].val);
                    // System.out.println(delay[i] + " " + delay[j] + " " + delay[k]);
                    dp[i][j] = Math.min(dp[i][j],dist[i][w]+dist[w][j] + Math.max(delay[i],Math.max(delay[j],delay[w])));
                }
            }
        }

        // for(int i =1;i<=N;i++){
        //     for(int j=1;j<=N;j++){
        //         System.out.print(dp[i][j] + " " + dist[i][j]);
        //     }
        //     System.out.println();
        // }

        for(int i=1;i<=Q;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());

            //System.out.println(s + " " + e + " " + dp[s][e] + " "+ dist[s][e]);
            if(dp[s][e] == max){
                System.out.println("-1");
            }else{
                System.out.println(dp[s][e]);
            }
        }

    }
}
class Node implements Comparable<Node>{
    public int idx;
    public int val;

    Node(int idx, int val){
        this.idx = idx;
        this.val = val;
    }

    @Override
    public int compareTo(Node n1){
        return (this.val - n1.val);
    }
}
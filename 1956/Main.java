import java.util.*;
import java.io.*;

class Main{

    static int V,E;
    static ArrayList<Node> [] arr;
    static long [][] adj;
    static long MAX = Integer.MAX_VALUE;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        V = Integer.parseInt(token.nextToken());
        E = Integer.parseInt(token.nextToken());

        adj = new long [401][401];
        arr = new ArrayList[401];
        for(int i=1;i<=400;i++){
            arr[i] = new ArrayList<>();
            Arrays.fill(adj[i],MAX); 
        }

        for(int i=1;i<=E;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());
            int val = Integer.parseInt(token.nextToken());

            adj[s][e] = val;
            adj[s][s] = 0;
            adj[e][e] = 0;
        }


        for(int k=1;k<=V;k++){
            for(int i=1;i<=V;i++){
                for(int j=1;j<=V;j++){
                    if(adj[i][j] > adj[i][k] + adj[k][j]){
                        adj[i][j] = adj[i][k] + adj[k][j];
                    }
                }
            }
        }

        long res = Integer.MAX_VALUE;

        for(int i=1;i<=V;i++){
            for(int j=1;j<=V;j++){
                if(i==j) continue;
                res = Math.min(res, adj[i][j] + adj[j][i]);
            }
        }

        if(res == MAX) System.out.println("-1");
        else System.out.println(res);

// for(int i=1;i<=V;i++){
//     for(int j=1;j<=V;j++){
//         System.out.print(adj[i][j] + " ");
//     }System.out.println();
// }

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
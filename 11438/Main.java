import java.io.IOException;
import java.util.*;
import java.io.*;

public class Main {

    static int N,M;
    static int MAX = 1000001;
    static int MD = 22;
    static int[][] p = new int[MAX][MD+1];
    static int[] d = new int[MAX];
    static boolean [] visited = new boolean[MAX];
     
    static ArrayList<Integer>[] arr;

    public static void main(String[] args) throws NumberFormatException, IOException {
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token;
        N = Integer.parseInt(br.readLine());

        arr = new ArrayList[N+1];
        for(int i = 0;i<arr.length;i++){
            arr[i] = new ArrayList<>();
        }

        for(int i =1;i<N;i++){
            token = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(token.nextToken());
            int b = Integer.parseInt(token.nextToken());

            arr[a].add(b);
            arr[b].add(a);            
            
        }

        M = Integer.parseInt(br.readLine());

        dfs(1,0);//dfs 한번 돌리고
        
        // for(int i = 0;i<=N+1;i++){
        //     System.out.print(p[i][0] + " ");
        // }
        // System.out.println();

        set_parent();

        // for(int j = 0;j<=N;j++){
        //     for(int i = 0;i<MD;i++){
        //         System.out.print(p[i][j] + " ");
        //     }
        //     System.out.println();
        // }

        for(int i =1;i<=M;i++){
            token = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(token.nextToken());
            int b = Integer.parseInt(token.nextToken());
         
            System.out.println(lca(a,b));
        }

    }

    static void dfs(int here, int depth) {
        visited[here] = true;
        d[here] = depth;
        for(int next : arr[here]){
            if(visited[next]){
                continue;
            }
            p[next][0] = here;
            dfs(next, depth+1);
        }
    }

    static void set_parent() {
        for (int j = 1; j < 21; j++) {
            for (int i = 1; i <= N; i++) {
                p[i][j] = p[p[i][j - 1]][j - 1];
            }
        }
    }

    static int lca(int a, int b){
        if(d[a] > d[b]){
            int tmp = a;
            a = b;
            b = tmp;
        }
    
        for(int n=20;n>=0;n--){
            if(d[b]-d[a] >= (1<<n)){
                b = p[b][n];
            }
        }
    
        if(a == b) return a;
    
        for(int n=20;n>=0;n--){
            if(p[a][n] != p[b][n]){
                a = p[a][n];
                b = p[b][n];
            }
        }
    
        return p[a][0];
    }


}

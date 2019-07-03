import java.io.IOException;
import java.util.*;
import java.io.*;

public class Main {
 
 
    static int lca(int x, int y) {
        if (d[x] > d[y]) {
            int temp = d[x];
            d[x] = d[y];
            d[y] = temp;
        }

 
        for (int i = 20; i >= 0; i--) {
            if (d[y] - d[x] >= (1 << i))
                y = p[y][i];
        }
        
        if (x == y) return x;
 
        for (int i = 20; i >= 0; i--) {
            if (p[x][i] != p[y][i]) {
                x = p[x][i];
                y = p[y][i];
            }
        }
        return p[x][0];
 
    }

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

        dfs(1,0);
        
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
        d[here] = depth;
        visited[here] = true;
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


}

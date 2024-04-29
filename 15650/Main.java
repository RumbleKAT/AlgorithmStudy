import java.util.*;
import java.io.*;

class Main {

    static int N, M;
    static int size;
    static boolean [] visited;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        visited = new boolean[N];

        for(int i=0;i<N;i++){
            if(!visited[i]){
                visited[i] = true;
                dfs(i, 1);
                visited[i] = false;
            }
        }
    }
    static void dfs(int num, int seq){
        if(seq == M){
            for(int i = 0;i< visited.length;i++){
                if(visited[i]){
                    System.out.print((i+1) + " ");
                }
            }
            System.out.println();
            return;
        }
        for(int i=num;i<N;i++){
            if(!visited[i]){
                visited[i] = true;
                dfs(i, seq+ 1);
                visited[i] = false;
            }
        }
    }
}
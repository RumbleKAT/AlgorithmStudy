import java.util.*;
import java.io.*;

public class Main {

    static int N,M;
    static int [] parent;
    
    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        parent = new int[N+1];
        for(int i=1;i<=N;i++){
            parent[i] = i;
        }

        for(int i=1;i<=M;i++){
            token = new StringTokenizer(br.readLine());
            int temp = Integer.parseInt(token.nextToken());

            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());

            if(temp == 0){
                int rootA = find(s);
                int rootB = find(e);

                if(rootA != rootB){
                    parent[rootB] = rootA;
                }
            }else{
                if(find(s)==find(e)){
                    System.out.println("YES");
                }else{
                    System.out.println("NO");
                }
            }

        }
    }    
    static int find(int a){
        if(parent[a] == a) return a;
        else return parent[a] = find(parent[a]);
    }
}

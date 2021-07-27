import java.sql.Array;
import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int M;
    static int [] root;
    static ArrayList [] nodes;

    public static void main(String[] args) throws Exception{
        //System.setIn(new FileInputStream("C:\\Users\\reki3\\IdeaProjects\\1976\\src\\sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        token = new StringTokenizer(br.readLine());
        M = Integer.parseInt(token.nextToken());

        root = new int[N+1];
        for(int i=1;i<=N;i++){
            root[i] = i;
        }

        nodes = new ArrayList[N+1];
        for(int i=1;i<=N;i++){
            nodes[i] = new ArrayList<Integer>();
        }

        for(int i=1;i<=N;i++){
            token = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++){
                int cur = Integer.parseInt(token.nextToken());
                if(cur == 1){
                    nodes[i].add(j);
                    int rootA = getRoot(i);
                    int rootB = getRoot(j);
                    if(rootA != rootB){
                        if(rootA < rootB){
                            root[rootB] = rootA;
                        }else{
                            root[rootA] = rootB;
                        }
                    }
                }
            }
        }
//        System.out.println(Arrays.toString(root));
        token = new StringTokenizer(br.readLine());
        int rootA = Integer.parseInt(token.nextToken());
        int rA = getRoot(rootA);
        boolean flag = false;
        
        while(token.hasMoreTokens()){
            int rootB = Integer.parseInt(token.nextToken());
            int rB = getRoot(rootB);

            if(rA != rB){
                System.out.println("NO");
                flag = true;
                break;
            }
        }
        if(!flag) System.out.println("YES");

    }
    static int getRoot(int n){
        if(n == root[n]) return n;
        return root[n] = getRoot(root[n]);
    }
}

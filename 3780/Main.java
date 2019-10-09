import java.io.*;
import java.util.*;

class Main{
    static int TC,N;
    static int [] parent;
    static LinkedList<Node> [] arr; 
    static long [] dists;
    static final int MOD = 1000;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        TC=  Integer.parseInt(token.nextToken());

        for(int t =1;t<=TC;t++){
            token = new StringTokenizer(br.readLine());
            N = Integer.parseInt(token.nextToken());

            parent = new int[N+1];
            arr = new LinkedList[N+1];
            dists = new long [N+1];

            for(int i=1;i<=N;i++){
                arr[i] = new LinkedList<>();
                parent[i]= i;
                dists[i] = 0;
            }

            long ans = 0;

            while(true){
                token = new StringTokenizer(br.readLine());
                int count = token.countTokens();

                if(count == 1){
                    break;
                }else if(count == 2){
                    // System.out.println(ans);
                    char c = token.nextToken().charAt(0);
                    int idx = Integer.parseInt(token.nextToken());
                
                    // System.out.println(Arrays.toString(parent));
                    System.out.println(Arrays.toString(dists));
                    // System.out.println("ans : " + dists[idx]);
                    System.out.println(find(idx));

                }else if(count == 3){
                    char c = token.nextToken().charAt(0);
                    int s = Integer.parseInt(token.nextToken());
                    int e = Integer.parseInt(token.nextToken());

                    dists[s] = Math.abs(s-e)%MOD;
                    parent[s] = e;

                }
            }
        }

    }static long find(int a){
        if(parent[a] == a){
            // System.out.println("same : " + a + " " + dists[a]);
            return dists[a];
        }
        else{
            System.out.println("parent : " + find(parent[a]) + " " +parent[a] + " "  +a + " " + parent[parent[a]]);
            dists[a] += find(parent[a]);            
            parent[a] = parent[parent[a]];
            return dists[a];
        } 
    }


}
class Node implements Comparable<Node>{
    public int idx;
    public long dist;

    Node(int idx, long dist){
        this.idx = idx;
        this.dist = dist;
    }

    @Override
    public int compareTo(Node n1){
        return (int)(this.dist - n1.dist);
    }
}
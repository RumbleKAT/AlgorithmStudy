import java.util.*;
import java.io.*;

class Main{
    static int N,M;
    static int [] trace;
    static LinkedList<Node> [] arr;
    static boolean [] visited;
    static int count;
    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        trace = new int [N+1];
        arr = new LinkedList[N+1];
        for(int i=1;i<=N;i++){
            arr[i] = new LinkedList<>();
        }
        visited = new boolean [N+1];


        for(int i=1;i<=M;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());

            arr[s].add(new Node(e));
            arr[e].add(new Node(s));
        }
        
        count = 0;

        for(int i=1;i<=N;i++){
            if(trace[i]==0){
                dfs(i, true);
            }
        }

        int cnt = 0;
        for(int i=1;i<=N;i++){
            if(visited[i]){
                cnt++;
            }
        }
        System.out.println(cnt);

        for(int i=1;i<=N;i++){
            if(visited[i]){
                System.out.print(i + " ");
            }
        }
        System.out.println();


    }
    static int dfs(int idx, boolean root){
        trace[idx] = ++count;
        int ans = trace[idx];
        int child = 0;

        for(Node node : arr[idx]){
            if(trace[node.idx] > 0){
                ans = Math.min(ans, trace[node.idx]);
                continue;
            }

            child++;
            int low = dfs(node.idx, false);
            ans = Math.min(ans, low);

            if(!root && low >= trace[idx]){
                visited[idx] = true;
            }

        }

        if(root && child >= 2){
            visited[idx] = true;
        }

        return ans;
    }

}
class Node{
    public int idx;

    Node(int idx){
        this.idx = idx;
    }
}
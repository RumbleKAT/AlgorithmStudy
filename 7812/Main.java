import java.util.*;
import java.io.*;

class Main {

    static ArrayList<Node> [] arr;
    static int N;
    static int [] cnt;
    static long [] sum;
    static long ans;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token;

        while(true){ 
            String temp = br.readLine();        
            if(temp.split(" ").length == 1 && temp.charAt(0) == '0') break;
            
            N = Integer.parseInt(temp);

            cnt = new int [N+1];
            arr = new ArrayList[N+1];
            sum = new long[N+1];

            for(int i=0;i<=N;i++){
                arr[i] = new ArrayList<>();
            }

            ans = Long.MAX_VALUE;

            for(int i=1;i<N;i++){
                token = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(token.nextToken());
                int e = Integer.parseInt(token.nextToken());
                int val = Integer.parseInt(token.nextToken());
                
                arr[s].add(new Node(e,val));
                arr[e].add(new Node(s,val));
            }

            dfs(0,-1);
            // System.out.println(Arrays.toString(cnt));
            // System.out.println(Arrays.toString(sum));
            
            System.out.println(getSum(0,-1,sum[0]));
            
        }
    }
    static long getSum(int here, int prev, long res){
        ans = Math.min(ans, res);

        for(Node node : arr[here]){
            if(node.idx != prev){
                getSum(node.idx, here, res-(cnt[node.idx]*node.val) + ((N-cnt[node.idx])*node.val));
            }
        }        
        return ans;
    }

    static void dfs(int here, int prev){
        cnt[here] = 1;

        for(Node node : arr[here]){
            if(node.idx != prev){
                dfs(node.idx, here);
                cnt[here] += cnt[node.idx];//자신을 포함한 서브노드의 갯수
                sum[here] += node.val * cnt[node.idx] + sum[node.idx];
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
    public int compareTo(Node node){
        return this.val - node.val;
    }
}
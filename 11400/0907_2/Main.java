import java.util.*;
import java.io.*;

class Main{

    static int N,M;
    static int [] trace;
    static LinkedList<Node> [] arr;
    static LinkedList<Comp> comps;
    static int count;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        trace = new int[N+1];
        arr =  new LinkedList[N+1];
        for(int i =1;i<=N;i++){
            arr[i] = new LinkedList<>();
        }
        comps = new LinkedList<>();

        for(int i =1;i<=M;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());

            arr[s].add(new Node(e));
            arr[e].add(new Node(s));
        }

        for(int i =1;i<=N;i++){
            if(trace[i] == 0)dfs(i,0);
        }

        System.out.println(comps.size());
        Collections.sort(comps);

        for(Comp comp : comps){
            System.out.println(comp.a + " " + comp.b);
        }

    }
    static int dfs(int idx, int parent){
        trace[idx] = ++count;
        int ans = trace[idx];

        for(Node node : arr[idx]){
            if(node.idx == parent ) continue;
            if(trace[node.idx] == 0){
                int low = dfs(node.idx, idx);
                if(low > trace[idx]){
                    int min = Math.min(node.idx, idx);
                    int max = Math.max(node.idx, idx);
                    comps.add(new Comp(min,max));
                }
                ans = Math.min(ans, low);
            }else{
                ans = Math.min(ans, trace[node.idx]);
            }
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
class Comp implements Comparable<Comp>{
    public int a;
    public int b;

    Comp(int a, int b){
        this.a = a;
        this.b = b;
    }

    @Override
    public int compareTo(Comp c1){
        if(this.a == c1.a){
            return this.b - c1.b;
        }else{
            return this.a - c1.a;
        }
    }

}
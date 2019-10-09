import java.util.*;
import java.io.*;

class Main{
    
    static int N,M;
    static LinkedList<Node> [] arr;
    static LinkedList<Comp> comps;
    static int [] trace;
    static int count;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        arr= new LinkedList[N+1];
        comps = new LinkedList<>();
        trace = new int[N+1];
        for(int i =1;i<=N;i++){
            arr[i] = new LinkedList<>();
        }
        count = 0;

        for(int i =1;i<=M;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());

            arr[s].add(new Node(e));
            arr[e].add(new Node(s));
        }

        for(int i =1;i<=N;i++){
            if(trace[i] ==0)
            dfs(i,0);
        }

        System.out.println(comps.size());

        Collections.sort(comps);

        for(Comp comp : comps){
            System.out.println(comp.A + " " + comp.B);
        }


    }
    static int dfs(int idx, int parent){
        trace[idx] = ++count;
        int ans = trace[idx];
    
        for(Node node : arr[idx]){
            if(parent == node.idx) continue;
            if(trace[node.idx] ==0){
                int low = dfs(node.idx, idx);
                if(low > trace[idx]){
                    int min = Math.min(node.idx, idx);
                    int max = Math.max(node.idx,idx);
                    comps.add(new Comp(min,max));
                }
                ans = Math.min(ans,low);
            }else{
                ans = Math.min(ans,trace[node.idx]);
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
    public int A;
    public int B;

    Comp(int A, int B){
        this.A = A;
        this.B = B;
    }

    @Override
    public int compareTo(Comp c1){
        if(c1.A == this.A){
            return this.B - c1.B;
        }else{
            return this.A - c1.A;
        }
    }
}
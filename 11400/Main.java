import java.util.*;
import java.io.*;

class Main{

    static int V,E;
    static int [] trace;
    static int count;
    static LinkedList <Node> [] arr;
    static LinkedList <Comp> comps;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());
        V = Integer.parseInt(token.nextToken());
        E = Integer.parseInt(token.nextToken());

        trace = new int [V+1];
        arr = new LinkedList[V+1];
        comps = new LinkedList<>();
        for(int i =1;i<=V;i++){
            arr[i] = new LinkedList<>();
        }

        for(int i =1;i<=E;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());

            arr[s].add(new Node(e));
            arr[e].add(new Node(s));
        }

        count = 0;

        for(int i =1;i<=V;i++){
            if(trace[i] == 0) dfs(i,0);
        }
        bw.write(comps.size()+"\n");
        Collections.sort(comps);

        for(Comp com : comps){
            bw.write(com.A + " " + com.B+"\n");
        }
        bw.flush();


    }
    static int dfs(int idx, int parent){
        trace[idx] = ++count;
        int ans = trace[idx];

        for(Node node : arr[idx]){
            if(node.idx == parent) continue;
            if(trace[node.idx] == 0){
                int low = dfs(node.idx,idx);
                if(low > trace[idx]){
                    int min = Math.min(node.idx,idx);
                    int max = Math.max(node.idx,idx);
                    comps.add(new Comp(min, max));
                }
                ans = Math.min(ans, low);
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

    Comp (int A, int B){
        this.A = A;
        this.B = B;
    }

    @Override
    public int compareTo(Comp c1){
        if(this.A == c1.A){
            return (this.B - c1.B);
        }else{
            return (this.A - c1.A);
        }
    }
}
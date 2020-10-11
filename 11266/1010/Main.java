import java.util.*;
import java.io.*;

public class Main {

    static int V,E;
    static int count = 0;
    static int childCount = 0;
    static int [] visited;
    static int [] low;
    static boolean [] curPointList;
    static ArrayList<Integer> [] adj;
    static Stack<Node>st;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        V = Integer.parseInt(token.nextToken());
        E = Integer.parseInt(token.nextToken());

        visited = new int [V+1];
        low = new int [V+1];
        adj = new ArrayList[V+1];
        curPointList = new boolean [V+1];

        for(int i=1;i<=V;i++){
            adj[i] = new ArrayList<Integer>();
        }
        st = new Stack<>();

        for(int i=1;i<=E;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());
            adj[s].add(e);
            adj[e].add(s);
        }
        for(int i=1;i<=V;i++){
           if(visited[i] == 0){
               st.add(new Node(i,0,-1));
               while(!st.isEmpty()){
                   Node cur = st.pop();
                   int num = cur.idx;
                   int childCount = cur.childCount;
                   int lastIdx = cur.lastIdx;

                   if(lastIdx != -1){
                     if(num ==i && childCount >= 2){
                         if(!curPointList[num]){
                             curPointList[num] = true;
                         }
                     }
                     if(num != i && visited[num] <= low[adj[num].get(lastIdx)]){
                        if(!curPointList[num]){
                            curPointList[num] = true;
                        }
                     }
                   }
                   lastIdx++;

                   if(visited[num] == 0){
                       visited[num] = low[num] = ++count;
                   }

                   for(int idx = lastIdx;idx<adj[num].size();idx++){
                        if(visited[adj[num].get(idx)] != 0){
                            low[num] = Math.min(low[num], visited[adj[num].get(idx)]);
                        }else{
                            st.add(new Node(num,childCount+1,idx));
                            st.add(new Node(adj[num].get(idx),0,-1));
                            break;
                        }
                   }
               }
           }
        }

        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=V;i++){
            if(curPointList[i]){
                sb.append(i+" ");
                cnt++;
            }
        }
        System.out.println(cnt);
        System.out.println(sb.toString());

    }    
}
class Node{
    public int idx;
    public int childCount;
    public int lastIdx;

    Node(int idx, int childCount, int lastIdx){
        this.idx = idx;
        this.childCount = childCount;
        this.lastIdx = lastIdx;
    }
}

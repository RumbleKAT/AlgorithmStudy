import java.util.*;
import java.io.*;

class Main{

    static boolean [] visited;
    static ArrayList<Integer> [] nodes;
    static int cnt;
    static int subCnt;
    static int TC = 1;
    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token;
        while(true){
            token = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(token.nextToken());
            int m = Integer.parseInt(token.nextToken());

            if(n == 0  && m == 0){
                break;
            }
            visited = new boolean [n+1];
            nodes = new ArrayList[n+1];
            for(int i=0;i<n+1;i++){
                nodes[i] = new ArrayList<>();
            }

            HashSet<Integer> numbers = new HashSet<>();

            for(int i=0;i<m;i++){
                token = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(token.nextToken());
                int e = Integer.parseInt(token.nextToken());
                nodes[s].add(e);
                nodes[e].add(s);
                numbers.add(s);
                numbers.add(e);
            }
            int cnt =  0;
            for(int i=1;i<=n;i++){
                if(!visited[i]){
                    visited[i] = true;
                    if(isTree(0, i)) cnt++;
                }
            }
            // System.out.println(cnt);
            bw.write("Case "+TC+": ");
            if(cnt == 0){
                bw.write("No trees.");
            }else if(cnt == 1){
                bw.write("There is one tree.");
            }else{
                bw.write("A forest of "+cnt+" trees.");
            }
            bw.newLine();
            bw.flush();
            TC++;
        }
          
    }
    static boolean isTree(int root, int idx){
        for(int next : nodes[idx]){
            if(next == root) continue;
            if(visited[next]) return false;
            visited[next] = true;
            if(!isTree(idx, next)) return false;
        }
        return true;
    }
    
}
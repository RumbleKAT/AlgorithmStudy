import java.util.*;
import java.io.*;

class Main{
    static int V,E;
    static int [][] parent = new int [50050][22];
    static int [][] g = new int [50050][22];
    static int [] depth = new int[50050];
    static int [] p = new int[50050];
    static int [] c = new int [200020];
    static Node [] arr;
    static Node [] mst;
    static long f = 0;
    static int cnt = 0;
    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());
    
        V = Integer.parseInt(token.nextToken());
        E = Integer.parseInt(token.nextToken());

        arr = new Node[V+1];
        mst = new Node[V+1];
    
        p = new int[V+1];
        for(int i =1;i<=V;i++){
            p[i] = i;
        }

        for(int i =1;i<=E;i++){
            token = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(token.nextToken());
            int b = Integer.parseInt(token.nextToken());
            int val = Integer.parseInt(token.nextToken());

            arr[i] =  new Node(a,b,val);
        }

        Arrays.sort(arr,1,V+1, new Comparator<Node>(){
            public int compare(Node n1, Node n2){
                return n1.val - n2.val;
            }
        });

        for(int i=1;i<=E;i++){
            if(union(i, arr[i].get(i).next,arr[i].get(i).val)){
                c[i]++;
                cnt++;
            }
            if(cnt == V-1) break;
        }

        if(cnt != V-1 || E <= V-1){
            bw.write("-1");
            bw.newLine();
            bw.flush();
            return;
        }

        dfs(1,0);
        setParent();
        long r = Long.MAX_VALUE;

        for(int i=1;i<E;i++){
            if(c[i]>0) continue;
            int gmax = getMax(arr[i].get(i+1).next,arr[i].get(i+1).val,arr[i].get(i).next);
            if(gmax ==0) continue;
            r = Math.min(r, (long)(f-gmax+arr[i].get(i).next));
        }
        if(r == Long.MAX_VALUE || r == f) bw.write("-1");
        else{
            bw.write(r+"\n");
        }
        bw.newLine();
        bw.flush();
    }

    static int binarySearch(int x, int y, int z){
        if(y == 0) return 0;
        int ret = 0;
        if(g[x][y-1] == z)
            ret = Math.max(ret, binarySearch(x, y-1, z));
        else 
            ret = Math.max(ret, g[x][y-1]);
        if(g[parent[x][y-1]][y-1] == z)
            ret = Math.max(ret, binarySearch(parent[x][y-1],y-1,z));
        else
            ret = Math.max(ret, g[parent[x][y-1]][y-1]);
        return ret;
    }

    static int getMax(int x, int y, int z){
        if(depth[x] > depth[y]){
            int tmp = x;
            x = y;
            y = tmp;
        }
        int ret = 0;
        for(int i =20;i>=0;i--){
            if(depth[y]-depth[x] >= (1<<i)){
                if(g[y][i] == z)
                    ret = Math.max(ret, binarySearch(y,i,z));
                else
                    ret = Math.max(ret, g[y][i]);
                y = parent[y][i];
            }
        }
        if(x == y) return ret;
        for(int i =20;i>=0;i--){
            if(parent[x][i] != parent[y][i]){
                if(g[y][i] == z)
                    ret = Math.max(ret, binarySearch(y,i,z));
                else
                    ret = Math.max(ret, g[y][i]);
                if(g[x][i] == z)
                    ret = Math.max(ret, binarySearch(x,i,z));
                else
                    ret = Math.max(ret, g[x][i]);
                
                x = parent[x][i];
                y = parent[y][i];
            }
        }

        if(g[x][0]!=z)
            ret = Math.max(ret, g[x][0]);
        if(g[y][0]!=z)
            ret = Math.max(ret, g[y][0]);

        return ret;
    }

    static void dfs(int current, int prev){
        for(Node nextNode : arr[current]){
            if(nextNode.next == prev) continue;
            depth[nextNode.next] = depth[current] +1;
            parent[nextNode.next][0] = current;
            g[nextNode.next][0] = nextNode.val;
            dfs(nextNode.next ,current);
        }
    }
    
    static void setParent(){
        for(int j =1;j<21;j++){
            for(int i =1;i<=V;i++){
                parent[i][j] = parent[parent[i][j-1]][j-1];
                g[i][j] = Math.max(g[parent[i][j-1]][j-1], g[i][j-1]);
            }
        }
    }

    static int find(int a){
        if(a == p[a]) return a;
        else return p[a] = find(p[a]); 
    }

    static boolean union(int a, int b, int c){
        if(find(a) == find(b)) return false;
        mst[a] =new Node(a,b,c);
        f += c;
        p[find(a)] = find(b);
        return true;
    }
}
class Node implements Comparable<Node>{
    public int current;
    public int next;
    public int val;

    Node(int current, int next, int val){
        this.current = current;
        this.next = next;
        this.val = val;
    }

    @Override
    public int compareTo(Node n1){
        return this.val - n1.val;
    }
}
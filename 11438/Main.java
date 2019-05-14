import java.util.*;
import java.io.*;

class Main{
    static int k, q;
    static ArrayList <Integer> [] arr;
    static boolean [] visit;
    static int [][] parent = new int [100000][21];
    static int [] depth = new int [100000];

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token;
        k = Integer.parseInt(br.readLine());

        arr = new ArrayList [k+1];
        visit = new boolean [k+1];

        for(int i =1;i<=k;i++){
            depth[i] = -1;
            arr[i] = new ArrayList<>();
        }

        for(int j=0;j<17;j++){
            for(int i=1;i<=k;i++){
                parent[i][j] = -1;
            }
        }

        for(int i =1;i<=k-1;i++){
            token = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(token.nextToken());
            int end = Integer.parseInt(token.nextToken());

            arr[start].add(end);
            arr[end].add(start);      
        }

        depth[0] = 0;
        bfs();

        for(int j=1;j<=17;j++){
            for(int i=1;i<=k;i++){
                parent[i][j] = parent[parent[i][j-1]][j-1];
            }
        }

        q = Integer.parseInt(br.readLine());

        for(int i = 1;i<=q;i++){
            token = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(token.nextToken());
            int end = Integer.parseInt(token.nextToken());

            System.out.println(lca(start,end));
        }

    }

    static int lca(int a, int b){

        int low, high;

        if(depth[a] < depth[b]){ low=b; high=a; }
        else { low=a; high=b; }

        //낮은 애를 올려서 높이를 맞춰준다
        for(int i=20; 0<=i; i--){
            int diff = depth[low] - depth[high];
            if(Math.pow(2, i) <= diff){
                low = parent[low][i];
            }
        }

//        System.out.printf("low:%d(%d) high:%d(%d)",low,depth[low],high,depth[high]);

        //높이만 맞췄는데 같아져버림
        if( low == high )return high;

        //목표지점 한칸 아래까지 함께 점프
        for(int i=20; 0<=i; i--){
            if(parent[low][i] != parent[high][i]){
                low = parent[low][i];
                high = parent[high][i];
            }
        }

        //남은한칸 마저 점프
        high = parent[high][0];
        //점프 후
//        System.out.printf("[before]low:%d(%d)", low, depth[low]);

        return high;
    }

    

    static void showMap(){
        for(int i =0;i<17;i++){
            for(int j =1;j<=k;j++){
                System.out.print(parent[i][j] + " ");
            }System.out.println();
        }
    }

    static void bfs(){
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        visit[1] = true;
        int d=0;


        while (!q.isEmpty()){
            d++;

            int qSize = q.size();
            for(int i=0; i<qSize; i++){
                int now = q.poll();

                if(arr[now]==null) continue;

                int mSize = arr[now].size();
                for(int j=0; j<mSize; j++){

                    int next = arr[now].get(j);
                    if(visit[next]==false){
                        parent[next][0] = now;
                        q.add(next);
                        visit[next]=true;
                        depth[next] = d;
                    }
                }
            }//for

        }
    }

    static void dfs(int index){
        // if(visit[index]){
        //     return;
        // }        
        // visit[index] = true;
        // System.out.println("index : " + index);

        for(int nextNode : arr[index]){
            if(depth[nextNode] == -1){
                parent[nextNode][0] = index;
                depth[nextNode] = depth[index] + 1;
                dfs(nextNode);
            }
        }
    }
}
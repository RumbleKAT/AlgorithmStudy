import java.util.*;
import java.io.*;

class Main{
    static int N, M;
    static char [][] map;
    static int [] parent;
    static int [][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
    //RLUD

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        map = new char[N][M];
        parent = new int[N*M];

        for(int i=0;i<parent.length;i++){
            parent[i] = i;
        }

        for(int i=0;i<N;i++){
            map[i] = br.readLine().toCharArray();
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                int curIdx = i * M + j;
                int nextIdx = findIdx(i,j);
                if(find(curIdx) != find(nextIdx)){
                    union(curIdx, nextIdx);
                }
            }
        }

        // System.out.println(Arrays.toString(parent));
        HashSet<Integer> set = new HashSet<>();
        for(int i=0;i<parent.length;i++){
            set.add(find(i));
        }
        System.out.println(set.size());


    }
    static int findIdx(int y,int x){
        int ny = y;
        int nx = x;

        if(map[y][x] == 'U'){
            ny--;
        }else if (map[y][x] == 'D'){
            ny++;
        }else if(map[y][x] == 'L'){
            nx--;
        }else{
            nx++;
        }

        return ny * M + nx;

    }
    
    public static int getId(int x, int y, int cols) {
        return x * cols + y;
    }

    public static int find(int x){
        if(parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    public static void union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);
        if(rootA > rootB){
            parent[rootA] = rootB;
        }else{
            parent[rootB] = rootA;
        } 
    }



}
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.FileInputStream;

class Main{
    static int n,m;
    static int [] parent;
    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        n = Integer.parseInt(token.nextToken());
        m = Integer.parseInt(token.nextToken());
        parent = new int [1000001];

        init();

        for(int i =1;i<=m;i++){
            token = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(token.nextToken());
            int from = Integer.parseInt(token.nextToken());
            int to = Integer.parseInt(token.nextToken());

            if(order == 0){
                //add
                union(from, to); 
            }else{
                //show
                if(find(from) == find(to)){
                    System.out.println("YES");
                }else{
                    System.out.println("NO");
                }
            }
        }
    }

    static void init(){
        for(int i = 1;i<=n;i++){
            parent[i] = i;
        }
    }
    static void union(int a,int b){
        int aRoot = find(a);
        int bRoot = find(b);
        parent[aRoot] = bRoot;
    }
    static int find(int a){
        if(parent[a] == a) return a;
        else return parent[a] = find(parent[a]);
    }
}
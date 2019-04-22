import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.io.FileInputStream;

class Main{

    static ArrayList <Integer>[]arr;
    static boolean [] visited;
    static int n,m;
    static Stack <Integer> stack;
    static Integer [] countArr;

    public static void main(String [] args) throws Exception{
        
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        n = Integer.parseInt(token.nextToken());
        m = Integer.parseInt(token.nextToken());

        int min = Integer.MAX_VALUE;

        arr = (ArrayList<Integer>[])new ArrayList[n+1];
        visited = new boolean[n+1];
        stack = new Stack<>();
        countArr = new Integer[n+1];

        for(int i =0;i<=n;i++){
            arr[i] = new ArrayList<Integer>();
        }

        for(int i =1;i<=m;i++){
            token = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(token.nextToken());
            int to = Integer.parseInt(token.nextToken());

            arr[from].add(to);
            //arr[to].add(from);
        }

        for(int i = 1;i<=n;i++){
            if(!visited[i]){
                dfs(i);
            }
        }

        while(!stack.isEmpty()){
            System.out.print(stack.pop() + " ");
        }System.out.println();

    }
    static void dfs(int k){
        visited[k] = true;
        for(int i : arr[k]){
            if(visited[i]){
                continue;
            }    
            dfs(i);
        }
        stack.add(k);
    }

}

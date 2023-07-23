
import java.io.*;
import java.util.*;

public class Main {
    static int M,N;
    static int [] arr;
    static int [] result;
    static boolean [] visited;
    static BufferedWriter bw;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("C:\\Users\\root\\Desktop\\Study\\algorithm\\src\\sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        arr = new int[N];
        visited = new boolean[N];
        result = new int[M];
        for(int i=1;i<=N;i++){
            arr[i-1] = i;
        }
        dfs(0);
    }
    static void dfs(int index) throws IOException {
        if(index == result.length){
            for(int i=0;i<result.length;i++){
                bw.write(result[i] + " ");
            }
            bw.newLine();
            bw.flush();
        }else{
           for(int i=0;i<arr.length;i++){
               if(!visited[i]){
                   visited[i] = true;
                   result[index] = arr[i];
                   dfs(index+1);
                   visited[i] = false;
               }
           }
        }
    }
}
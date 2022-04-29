import java.util.*;
import java.io.*;


public class Main {

    static int N;
    static int [] arr;
    static boolean [] visited;
    static int [] res;
    static boolean checked;

    public static void main(String[] args) throws Exception{
        // System.setIn(new FileInputStream("/Users/songmyeongjin/Desktop/10448/src/com/company/sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());

        arr = new int[51];
        for(int i=1;i<=50;i++){
            arr[i] = i*(i+1)/2;
        }

        visited = new boolean[3];
        res = new int[3];

        for(int i=0;i<N;i++) {
            checked = false;
            token = new StringTokenizer(br.readLine());
            int std = Integer.parseInt(token.nextToken());
            dfs(0,std);
            if(checked){
                System.out.println(1);
            }else{
                System.out.println(0);
            }
        }

	}
    static void dfs(int param, int std){
        if(param == 3){
            if(std == res[0] + res[1] + res[2]){
                checked = true;
            }
            return;
        }
        for(int i=1;i<=50;i++) {
            if (visited[param]) return;
            visited[param] = true;
            res[param] = arr[i];
            dfs(param+1,std);
            visited[param] = false;
        }
    }
}

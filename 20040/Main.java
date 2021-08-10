import java.io.*;
import java.util.*;

public class Main {

    static int N,M;
    static ArrayList<Integer> [] arr;
    static int [] parent;

    public static void main(String[] args) throws Exception {
	    //System.setIn(new FileInputStream("/Users/songmyeongjin/eclipse-workspace/2512/src/com/company/sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        arr = new ArrayList[N];
        for(int i=0;i<arr.length;i++){
            arr[i] = new ArrayList<>();
        }
        parent = new int[N];
        for(int i=0;i<parent.length;i++){
            parent[i] = i;
        }
        int cnt = 0;

        for(int i=0;i<M;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());

            int rs = getRoot(s);
            int re = getRoot(e);

            if(rs != re){
                if(rs > re){
                    parent[re] = rs;
                }else{
                    parent[rs] = re;
                }
            }else{
                cnt = i+1;
                break;
            }
        }
        System.out.println(cnt);
    }
    static int getRoot(int n){
        if(parent[n] == n){
            return n;
        }else{
            return parent[n] = getRoot(parent[n]);
        }
    }
}

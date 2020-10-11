import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int [] arr  = new int[2];
    static int [][] map = new int [129][129];
    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(token.nextToken());
        for(int i=1;i<=N;i++){
            token = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++){
                map[i][j] = Integer.parseInt(token.nextToken());
            }
        }

        cnt(1, 1, N,N);

        System.out.println(arr[0]);
        System.out.println(arr[1]);
    }   
    static boolean check(int x1, int y1, int x2, int y2){
        int check = map[y1][x1];
        for(int i=y1;i<=y2;i++){
            for(int j=x1;j<=x2;j++){
                if(check != map[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
    static void cnt(int x1, int y1, int x2, int y2){
        if(check(x1, y1, x2, y2)){
            arr[map[y1][x1]]++;
            return;
        }        
        int mx = (x1+x2)/2;
        int my = (y1+y2)/2;

        cnt(x1, y1, mx, my);  
        cnt(mx+1, y1, x2, my);  
        cnt(x1, my+1, mx, y2);
        cnt(mx+1, my+1, x2, y2);  
        
        return;
    }

}

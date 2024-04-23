import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        
        int a = Integer.parseInt(token.nextToken());
        int b = Integer.parseInt(token.nextToken());
        int c = Integer.parseInt(token.nextToken());
        int d = Integer.parseInt(token.nextToken());
        int e = Integer.parseInt(token.nextToken());
        int f = Integer.parseInt(token.nextToken());

        for(int i=-999;i<=999;i++){
            for(int j=-999;j<=999;j++){
                if(check(a,b,i,j) == c && check(d,e,i,j) == f){
                    System.out.println(i + " " + j);
                    break;
                }
            }
        }
    }
    public static int check(int a, int b, int x, int y){
       return a * x + b * y;
    }
}
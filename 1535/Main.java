import java.util.*;
import java.io.*;

class Main{

    static int N;
    static int [] L;
    static int [] J;
    static int [][] capacity; //사람수 * weight

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());

        L = new int[N+1];
        J = new int[N+1];
        capacity = new int [N+1][101];

        token = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++){
            L[i] = Integer.parseInt(token.nextToken());
        }

        token = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++){
            J[i] = Integer.parseInt(token.nextToken());
        }


        for(int i=1;i<=N;i++){
            for(int j=1;j<100;j++){
                if(j >= L[i]){
                    capacity[i][j] = Math.max(capacity[i-1][j],capacity[i-1][j-L[i]] + J[i]);
                }else{
                    capacity[i][j] = capacity[i-1][j];
                }
            }
        }

        System.out.println(capacity[N][99]);
    }
}
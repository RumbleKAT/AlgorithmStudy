import java.util.*;
import java.io.*;

class Main{

    static int N,K;
    static int [][] capacity;
    static int [] weight;
    static int [] value;

    public static void main(String [] args) throws Exception {
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));        
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(token.nextToken());

        capacity = new int [N+1][K+1];
        weight = new int[N+1];
        value = new int [N+1];

        for(int i=1;i<=N;i++){
            token = new StringTokenizer(br.readLine());
            weight[i] = Integer.parseInt(token.nextToken());
            value[i] = Integer.parseInt(token.nextToken());
        }

        

        for(int i=1;i<=N;i++){
            for(int j=1;j<=K;j++){
                if(j >= weight[i]){
                    capacity[i][j] = Math.max(capacity[i-1][j], capacity[i-1][j-weight[i]] + value[i]);
                }else{
                    capacity[i][j] = capacity[i-1][j];
                }
            }
        }


        System.out.println(capacity[N][K]);

    }
}
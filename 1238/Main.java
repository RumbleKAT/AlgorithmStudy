import java.util.*;
import java.io.*;

class Main{

    static int [][] map;
    static int MAX = 987654321;
    static int N,M,K;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(token.nextToken());

        map = new int [N+1][N+1];

        for(int i =1;i<=N;i++){
            for(int j =1;j<=N;j++){
                if(i==j) continue;
                map[i][j] = MAX;
            }
        }

        for(int i=1;i<=M;i++){
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int e = Integer.parseInt(token.nextToken());
            int val = Integer.parseInt(token.nextToken());

            map[s][e] = Math.min(map[s][e],val);            
        }

        for(int k =1;k<=N;k++){
            for(int i =1;i<=N;i++){
                for(int j =1;j<=N;j++){
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }
        long max = 0;

        for(int i =1;i<=N;i++){
            if(map[i][K]+ map[K][i] > max){
                max = map[i][K]+ map[K][i];
            }
        }

        bw.write(max+"\n");
        bw.flush();
    }
}
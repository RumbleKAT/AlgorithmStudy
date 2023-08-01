import java.util.*;
import java.io.*;

class Main{

    static int tc;
    static int [][] apt;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        tc = Integer.parseInt(token.nextToken());

        apt = new int[15][15];

        for(int i=0;i<15;i++){
            apt[i][1] = 1;
            apt[0][i] = i;
        }

        for(int i=1;i<15;i++){
            for(int j=2;j<15;j++){
                apt[i][j] = apt[i-1][j] + apt[i][j-1];
            }
        }

        for(int t=1;t<=tc;t++){
            token = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(token.nextToken());
            token = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(token.nextToken());

            System.out.println(apt[k][n]);
        }

    }
}
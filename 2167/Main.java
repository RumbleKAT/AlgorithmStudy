import java.util.*;
import java.io.*;

class Main {

    static int N, M, K;

    static int [][] arr;
    static long [][] pre_sum;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(token.nextToken());
        int M = Integer.parseInt(token.nextToken());

        arr = new int[N][M];
        pre_sum = new long[N+1][M+1];

        for(int i=0;i<N;i++){
            token = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                arr[i][j] = Integer.parseInt(token.nextToken());
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                pre_sum[i][j] = arr[i-1][j-1] + pre_sum[i - 1][j] + pre_sum[i][j - 1] - pre_sum[i - 1][j - 1];
                System.out.print(pre_sum[i][j] + " ");
            }System.out.println();
        }

        token = new StringTokenizer(br.readLine());
        K = Integer.parseInt(token.nextToken());

        for(int a=1;a<=K;a++){
            token = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(token.nextToken()) - 1;
            int j = Integer.parseInt(token.nextToken()) - 1;

            int x = Integer.parseInt(token.nextToken()) - 1;
            int y = Integer.parseInt(token.nextToken()) - 1;


            long sum = pre_sum[x + 1][y + 1] - pre_sum[x + 1][j] - pre_sum[i][y + 1] + pre_sum[i][j];

            System.out.println(sum);
        }
    }
}
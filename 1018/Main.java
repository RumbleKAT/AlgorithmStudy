import java.util.*;
import java.io.*;

class Main{

    static boolean [][] arr;
    static int min = 64;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(token.nextToken());
        int M = Integer.parseInt(token.nextToken());
        arr = new boolean[N][M];
        
        for(int i=0;i<N;i++){
            String str = br.readLine();
            for(int j=0;j<M;j++){
                if(str.charAt(j) == 'W'){
                    arr[i][j] = true;
                }else{
                    arr[i][j] = false;
                }
            }
        }

        int n_row = N-7;
        int m_row = M-7;

        for(int i=0;i<n_row;i++){
            for(int j=0;j<m_row;j++){
                find(i,j);
            }
        }
        System.out.println(min);
    }
    static void find(int y, int x){
        int end_y = y + 8;
        int end_x = x + 8;
        int count = 0;

        boolean TF = arr[y][x];
        for(int i=y;i<end_y;i++){
            for(int j=x;j<end_x;j++){
                if(arr[i][j] != TF){
                    count++;
                }
                TF = !TF;
            }
            TF = !TF;
        }
        count = Math.min(count, 64-count);
        min = Math.min(min,count);
    }
}
import java.util.*;
import java.io.*;

class Main{

    static int [][] map;
    static int N;
    static int M;
    static int [] count = new int [3];

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token;
        N = Integer.parseInt(br.readLine());
        
        map = new int [N+1][N+1];

        for(int i =0;i<N;i++){
            token = new StringTokenizer(br.readLine());
            int idx = 0;
            while(token.hasMoreTokens()){
                String temp = token.nextToken();
                map[i][idx] = Integer.parseInt(temp);
                idx++;
            }
        }

        divdeConquer(0, 0, N);

        for(int i =0;i<3;i++){
            System.out.println(count[i]);
        }
    }
    static boolean check(int row, int col, int n){
        int std = map[row][col];
        for(int i = row;i<row+n;i++){
            for(int j=col;j<col+n;j++){
                if(std != map[i][j]){
                    return false;
                }
            }
        }
        M = std;
        return true;
    }

    static void divdeConquer(int row, int col, int n){
        boolean check = true;
        int std = map[row][col];
        
        for(int i = row;i<row+n;i++){
            for(int j=col;j<col+n;j++){
                if(std != map[i][j]){
                    check = false;
                }
            }
        }
        
        if(check){
            count[map[row][col]+1]++;
        }else{
            int s = n / 3;
            for(int i =0;i<3;i++){
                for(int j=0;j<3;j++){
                    divdeConquer(row+i*s, col+j*s, s);
                }
            }
        }
    }
}
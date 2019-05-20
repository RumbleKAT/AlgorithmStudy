import java.util.*;
import java.io.*;

class Main{

    static int [][] map;
    static int N;
    static int M;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        map = new int [N+1][N+1];

        for(int i =1;i<=N;i++){
            String temp = br.readLine();
            for(int j=0;j<temp.length();j++){
                map[i][j+1] = temp.charAt(j)- 48;
            }
        }

        divdeConquer(1, 1, N);
        System.out.println();
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
            System.out.print(std);
        }else{
            System.out.print("(");
            divdeConquer(row,col,n/2);
            divdeConquer(row,col+n/2,n/2);
            divdeConquer(row+n/2,col,n/2);
            divdeConquer(row+n/2,col+n/2,n/2);
            System.out.print(")");
        }
    }
}
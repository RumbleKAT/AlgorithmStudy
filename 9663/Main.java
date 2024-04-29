import java.util.*;
import java.io.*;

class Main{
    
    static int N;
    static boolean [][] map;
    static int count;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        map = new boolean[N+1][N+1];
        solveNQueen(1);
        System.out.println(count);
    }
    static void solveNQueen(int row){
        if(row > N){
            count++;
        }
        for(int i=1;i<=N;i++){
            if(isSafe(row, i)){
                map[row][i] = true;
                solveNQueen(row + 1);
                map[row][i] = false;
            }
        }
    }
    private static boolean isSafe(int row, int col) {
        for(int i =1;i<row;i++){
            if(map[i][col]) return false;
        }

        for(int i=row-1, j=col-1; i>=1 && j>=1; i--,j--){
            if(map[i][j]) return false;
        }

        for(int i=row-1, j=col+1; i>=1 && j <=N;i--,j++){
            if(map[i][j]) return false;
        }
        return true;
    }
}
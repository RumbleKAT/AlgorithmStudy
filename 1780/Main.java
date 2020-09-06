import java.util.*;
import java.io.*;

public class Main {

    static int [][] map = new int [3001][3001];
    static int [] arr = new int [4];
    static int N;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());

        for(int i=0;i<N;i++){
            token = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(token.nextToken());
            }
        }

        cnt(0,0,N);

        System.out.println(arr[2]);
        System.out.println(arr[0]);
        System.out.println(arr[1]);

    }   

    static void cnt(int sx, int sy, int n){
        boolean check = true;
        int chk = map[sy][sx];

        for(int i=sy;i<sy+n;i++){
            for(int j=sx;j<sx+n;j++){
                if(chk != map[i][j]){
                    check = false;
                }
            }
        }

        if(check){
            if(chk == -1){
                arr[2]++;
            }else{
                arr[chk]++;
            }
        }else{
            int s = n / 3;
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    cnt(sx+i*s, sy+j*s, s);
                }
            }
        }
        
        return;
    }
}

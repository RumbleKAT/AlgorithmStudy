import java.util.*;
import java.io.*;

class Main{

    static final int MAX = 1025;
    static int [][] arr = new int [MAX][MAX];
    static int [][] seg = new int [MAX*4][MAX*4];
    static int N,M;
    static int H;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        H = 1;
        while(H < N){
            H *= 2;
        }

        for(int i = 1;i<=N;i++){
            token = new StringTokenizer(br.readLine());
            for(int j = 1;j<=N;j++){
                arr[i][j] = Integer.parseInt(token.nextToken());
                // System.out.println(arr[i][j]);
                //update
                update(j,i, arr[i][j]);
            }
        }

        for(int i = 1;i<=M;i++){
            token = new StringTokenizer(br.readLine());
            int s1 = Integer.parseInt(token.nextToken());
            int e1 = Integer.parseInt(token.nextToken());

            int s2 = Integer.parseInt(token.nextToken());
            int e2 = Integer.parseInt(token.nextToken());

            if(s1 > s2){
                int tmp = s1;
                s1 = s2;
                s2 = tmp;
            }

            if(e1 > e2){
                int tmp = e1;
                e1 = e2;
                e2 = tmp;
            }

            System.out.println(queryY(1, 1, H, s1, s2, e1, e2));

        }
    }
    static void update(int x, int y, int val){
        int i = y + H -1;
        int j = x + H -1;
        seg[i][j] = val; //먼저 값을 바꿔줌

        while(j > 1){
            j/=2;
            seg[i][j] = seg[i][j*2] + seg[i][j*2+1];
        }

        while(i > 1){
            j = x + H - 1;
            i/=2;
            seg[i][j] = seg[i*2][j] + seg[i*2+1][j];
            while(j>1){
                j/=2;
                seg[i][j] = seg[i][j*2] + seg[i][j*2+1];
            }
        }
        return;
    }

    static int queryX (int y, int node, int start, int end, int left, int right){
        if(start > right || end < left) return 0;
        if(left <= start && end <= right) return seg[y][node];
        int mid = (start+end)/2;
        return queryX(y, node*2, start, mid, left, right) + queryX(y, node*2+1,mid+1,end,left, right);
    }

    static int queryY(int node, int start, int end, int left, int right, int x1, int x2){
        if(start > right || end < left ) return 0;
        if(left <= start && end <= right) return queryX(node, 1, 1, H, x1, x2);
        int mid = (start+end)/2;
        return queryY(node*2, start, mid, left, right, x1, x2) + queryY(node*2+1, mid+1, end, left, right, x1, x2);
    }


}